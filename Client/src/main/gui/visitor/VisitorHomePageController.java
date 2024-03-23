package main.gui.visitor;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import main.ClientController;
import main.controllers.ReservationController;
import main.threads.VisitorReminder;

public class VisitorHomePageController implements Initializable{
	@FXML
	private ImageView inboxImg;
	@FXML
	private Label msgAmount;
	@FXML
	private Label msgAlert;
	@FXML
	private Button admitBtn;
	@FXML
	private Button cancelBtn;
	@FXML
	private TableView<Order> messageTable;
	@FXML
	private TableColumn<Order,String> message;
	@FXML
	private Pane newMsgPane;
	public static Pane publicNewMsgPane;
	public static ObservableList<Order> ordersToConfirm = FXCollections.observableArrayList();
	int selectedRowIndex;
	private Order selectedOrder;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		publicNewMsgPane = newMsgPane;
		ordersToConfirm.addAll(VisitorReminder.getOrdersToConfirm());
		messageTable.setItems(ordersToConfirm);
		message.setCellValueFactory(new PropertyValueFactory<Order, String>("messageTitle"));
		Thread visitorReminder = new Thread(new VisitorReminder());
		visitorReminder.start();
		
		messageTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	        if (newSelection != null) {
	            selectedOrder = newSelection;
	            System.out.println("Selected order: " + selectedOrder);
	        }
	    });
		
		messageTable.setOnMouseClicked(event -> {
            if (!messageTable.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.getDialogPane().setPrefWidth(300);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Reservation Confirmation");
                alert.setContentText("You have a reservation in "+ selectedOrder.getParkName()+" at " + selectedOrder.getTime());

                ButtonType confirmButton = new ButtonType("Confirm");
                ButtonType cancelButton = new ButtonType("Cancel");

                alert.getButtonTypes().setAll(confirmButton, cancelButton);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == confirmButton) {
                   admit();
                } else {
                	cancel();
                }
            }
        });
		if(VisitorReminder.getMsgcount() >0 ) {
		msgAmount.setText(VisitorReminder.getMsgcount() + "");}
	}
	@FXML
	public void clickInboxIcon(MouseEvent event) {
		if(VisitorReminder.getMsgcount() >0 ) {
			msgAmount.setText(VisitorReminder.getMsgcount() + "");}
		messageTable.setVisible(!messageTable.isVisible());
		if (VisitorReminder.getMsgcount() > 1) {
			 messageTable.setItems(ordersToConfirm);
		}

	}
	public void admit() {
		if (selectedOrder != null) {
				ReservationController.sendConfirmReservation(selectedOrder);//
				for (Order o : ClientController.reservationshowed) {
					if (o.getOrderID().equals(selectedOrder.getOrderID())){	
							msgAmount.setText(VisitorReminder.getMsgcount() + "");
							if (VisitorReminder.getMsgcount() == 0)
								newMsgPane.setVisible(false);
							break;
						}
					}
			}
			else
				System.out.println("[VisitorHomePageController] - Order was null");
	}

	public void cancel() {
		if (selectedOrder != null) {
			selectedOrder.setCancelRequest(true);
			ReservationController.sendCancelReservation(selectedOrder);		
			msgAmount.setText(VisitorReminder.getMsgcount() + "");
			if (VisitorReminder.getMsgcount() == 0)
					newMsgPane.setVisible(false);
		}else
			System.out.println("[VisitorHomePageController] - Order was null");
		}
}