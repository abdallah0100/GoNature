package main.gui.visitor;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import entities.InboxMessage;
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
import main.controllers.InboxRequestController;
import main.controllers.ReservationController;
import main.entities.ConfirmationMessage;
import main.threads.VisitorReminder;


/**
* This class represents the controller for the home page of the visitor interface.
* Displaying inbox messages,and managing reservations for the visitor.
*/
public class VisitorHomePageController implements Initializable{
	@FXML
	private ImageView inboxImg;
	@FXML
	private Label msgAlert;
	@FXML
	private Button admitBtn;
	@FXML
	private Button cancelBtn;
	@FXML
	private TableView<InboxMessage> messageTable;
	@FXML
	private TableColumn<InboxMessage,String> message;
	@FXML
	private Pane newMsgPane;
	public static Pane publicNewMsgPane;
	public static ObservableList<InboxMessage> inboxMessages = FXCollections.observableArrayList();
	int selectedRowIndex;
	private Order selectedOrder;
	private InboxMessage selectedMsg;
	
	
	/**
     * Sets up initial state of UI elements and handles displaying inbox messages.
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		MakeReservationFrameController.o=null;
		publicNewMsgPane = newMsgPane;
		inboxMessages.addAll(VisitorReminder.updateAndGetMessages());
		messageTable.setItems(inboxMessages);
		message.setCellValueFactory(new PropertyValueFactory<InboxMessage, String>("title"));
		Thread visitorReminder = new Thread(new VisitorReminder());
		visitorReminder.start();
		handleTableSelection();
	}
	
	/**
    * Handles selection of items in the message table.
    * Displays appropriate alerts based on the type of selected message.
    */
	private void handleTableSelection() {
		messageTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	        if (newSelection != null) {
	        	selectedMsg = (InboxMessage)newSelection;
	        	if (newSelection instanceof ConfirmationMessage) {
	        		ConfirmationMessage msg = (ConfirmationMessage)newSelection;
		            selectedOrder = msg.getOrder();
		            System.out.println("Selected order: " + selectedOrder);
	            }
	        }
	    });
		
		messageTable.setOnMouseClicked(event -> {
            if (!messageTable.getSelectionModel().isEmpty()) {
            	Alert alert;
            	if (selectedMsg instanceof ConfirmationMessage)
            		alert = new Alert(Alert.AlertType.CONFIRMATION);
            	else
            		alert = new Alert(Alert.AlertType.INFORMATION);
            	
                alert.getDialogPane().setPrefWidth(300);
                alert.setTitle(selectedMsg.getTitle());
                alert.setContentText(selectedMsg.getContent());
            	if (selectedMsg instanceof ConfirmationMessage) {
                    alert.setHeaderText("Reservation Confirmation");
                    ButtonType confirmButton = new ButtonType("Confirm");
                    ButtonType cancelButton = new ButtonType("Cancel");
                    alert.getButtonTypes().setAll(confirmButton, cancelButton);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == confirmButton) {
                       admit();
                    } else if (result.isPresent() && result.get() == cancelButton){
                    	cancel();
                    }
            	}else {
            		//inbox message
                    ButtonType okButton = new ButtonType("OK");
                    alert.getButtonTypes().setAll(okButton);


                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == okButton) {
                        InboxRequestController.deleteMessage(selectedMsg);
                        if (VisitorReminder.getMsgcount() < 1)
                        	newMsgPane.setVisible(false);
                    }
            	}
       
            }
        });
	}
	
	 /**
     * Handles click event on the inbox icon, and shows
     * the message table after clicking.
     * @param event The mouse event.
     */
	@FXML
	public void clickInboxIcon(MouseEvent event) {
		messageTable.setVisible(!messageTable.isVisible());
		if (VisitorReminder.getMsgcount() > 1) {
			 messageTable.setItems(inboxMessages);
		}

	}
	
	/**
     * Handles the action event for confirming a reservation.
     */
	public void admit() {
		if (selectedOrder != null) {
				ReservationController.sendConfirmReservation(selectedOrder);
				if (ClientController.reservationshowed != null)
					for (Order o : ClientController.reservationshowed) {
						if (o.getOrderID().equals(selectedOrder.getOrderID())){	
								if (VisitorReminder.getMsgcount() == 0)
									newMsgPane.setVisible(false);
								break;
							}
						}
			}
			else
				System.out.println("[VisitorHomePageController] - Order was null");
	}

	/**
     * Handles the action event for cancelling a reservation.
     */
	public void cancel() {
		if (selectedOrder != null) {
			selectedOrder.setCancelRequest(true);
			ReservationController.sendCancelReservation(selectedOrder);		
			if (VisitorReminder.getMsgcount() == 0)
					newMsgPane.setVisible(false);
		}else
			System.out.println("[VisitorHomePageController] - Order was null");
		}
}