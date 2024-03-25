package main.gui.visitor;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Order;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import main.ClientController;
import main.controllers.ReservationController;
import main.controllers.VisitorRequestController;
import utilities.SceneController;
public class ShowReservationFrameController extends Application implements Initializable{
	@FXML
	private TableColumn<Order, String> siteColumn;
	@FXML
	private TableColumn<Order, String> dateColumn;
	@FXML
	private TableColumn<Order, String> timeColumn;
	@FXML
	private TableColumn<Order, String> typeColumn;
	@FXML
	private TableColumn<Order, String> visitorNumberColumn;
	@FXML
	private TableColumn<Order, String> phoneColumn;
	@FXML
	private TableColumn<Order, String> emailColumn;
	@FXML
	private TableColumn<Order, String> minuteColumn;
	@FXML
	private TableColumn<Order, String> payedColumn;
	@FXML
	private TableColumn<Order, String> orderIDColumn;
	@FXML
	private Button updateBtn;
	@FXML
	private Button deleteBtn;
	@FXML
    private TableView<Order> tableView;
	@FXML
	private Label msgLabel;
	String id;
	Order order;
	Order selectedOrder;

	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	* @param primaryStage the primary stage for the application
	* @throws Exception if an error occurs during initialization
	*/
	@Override
	public void start(Stage primaryStage) throws Exception { 
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature - Visitor/Instructor", primaryStage,
									"/main/gui/visitor/ShowReservationFrame.fxml");
	}
	//function deletes reservation (removes it from DB) on action to deleteBtn
	public void deleteReservation(ActionEvent e) throws Exception {
		//ReservationController.sendCancelReservation(o);
		selectedOrder = tableView.getSelectionModel().getSelectedItem();
	    if (selectedOrder != null) {
	    	ReservationController.sendCancelReservation(selectedOrder);
	            tableView.getItems().remove(selectedOrder);
	            System.out.println("[ShowReservationFrameController] - Reservation deleted successfully");
	    } else {
	        displayError("Please select a reservation to delete");
	    }
	}
	public void updateReservation(ActionEvent e) throws Exception {
	    if (order != null) {	  
	    	 VisitorRequestController.updateReservation(order);
	    	 if (VisitorRequestController.finishedUpdatingReservation) {
	    		 System.out.println("[UpdateReservationFrameController] - finished Making Reservation");
				}
				else {
					System.out.println("[UpdateReservationFrameController] - did not finished Making Reservation");}
	    } else {
	    	displayError("Please select and edit to update a row");
	    }
	    tableView.getSelectionModel().clearSelection();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		siteColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("parkName"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("hour"));
		typeColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("orderType"));
		visitorNumberColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("numOfVisitors"));		
		phoneColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("phone"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("email"));
		minuteColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("minute"));
		payedColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("isPayed"));
		orderIDColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("orderID"));
		updateBtn.setDisable(true);
		deleteBtn.setDisable(true);
	    tableView.setRowFactory(tv -> {
	        TableRow<Order> row = new TableRow<>();
	        row.setOnMouseClicked(event -> {
	            if (!row.isEmpty() && event.getClickCount() == 1) {
	                updateBtn.setDisable(false);
	                deleteBtn.setDisable(false);
	            } else {
	                updateBtn.setDisable(true);
	                deleteBtn.setDisable(true);

	            }
	        });
	        return row;
	    });
		if(ClientController.connectedVisitor.isFoundInDB()) {	
			id = ClientController.connectedVisitor.getId();
			VisitorRequestController.showReservations(id);		 
			if (VisitorRequestController.finishedShowingReservations) {
				ObservableList<Order> orderList = FXCollections.observableArrayList(ClientController.reservationshowed);
				if(tableView.getItems()!= null) {
					tableView.getItems().clear();
				}
				tableView.getItems().addAll(orderList);
			}
			else {
				System.out.println("[ShowReservationFrameController] - did not finished Showing Reservations");		
			}	
		} 
		editData();	 
	}
	public void editData() {
	    phoneColumn.setCellFactory(TextFieldTableCell.<Order>forTableColumn());
	    phoneColumn.setOnEditCommit(event ->{
	    	order = event.getTableView().getItems().get(event.getTablePosition().getRow());
	        String oldValue = order.getPhone();
	        String newValue = event.getNewValue();
	        
	        String phonePattern = "^05\\d{8}$";
	        if (!newValue.matches(phonePattern)) {
	            displayError("Please enter a valid phone number");
	            return;
	        }
	        
	        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
	        confirmationAlert.setHeaderText("Confirm Phone Number Change");
	        confirmationAlert.setContentText("Do you want to change the phone number from '" + oldValue + "' to '" + newValue + "'?");
	        confirmationAlert.getDialogPane().setPrefWidth(500);
	        confirmationAlert.showAndWait().ifPresent(response -> {
	            if (response == ButtonType.OK) {
	                order.setphone(newValue);
	                msgLabel.setVisible(false);
	            } else {
	                // Revert changes if canceled
	                event.getTableView().refresh();
	            }
	        });
	    });

	    emailColumn.setCellFactory(TextFieldTableCell.<Order>forTableColumn());
	    emailColumn.setOnEditCommit(event ->{
	        order = event.getTableView().getItems().get(event.getTablePosition().getRow());
	        String oldValue = order.getEmail();
	        String newValue = event.getNewValue();

	        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	        if (!newValue.matches(emailPattern)) {
	            displayError("Please enter a valid email address");
	            return;
	        }
	        
	        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
	        confirmationAlert.setHeaderText("Confirm Email Address Change");
	        confirmationAlert.setContentText("Do you want to change the email address from '" + oldValue + "' to '" + newValue + "'?");
	        confirmationAlert.getDialogPane().setPrefWidth(500);
	        confirmationAlert.showAndWait().ifPresent(response -> {
	            if (response == ButtonType.OK) {
	                order.setEmail(newValue);
	                msgLabel.setVisible(false);
	            } else {
	                // Revert changes if canceled
	                event.getTableView().refresh();
	            }
	        });
	    });
	    deleteBtn.setOnAction(event -> {
	        selectedOrder = tableView.getSelectionModel().getSelectedItem();
	        if (selectedOrder != null) {
	            Alert confirmDeleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
	            confirmDeleteAlert.setHeaderText("Confirm Delete");
	            confirmDeleteAlert.setContentText("Are you sure you want to delete this reservation?");
	            confirmDeleteAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

	            confirmDeleteAlert.showAndWait().ifPresent(response -> {
	                if (response == ButtonType.YES) {
	                    try {
	                        deleteReservation(event);
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                }
	            });
	        }
	    });
	}
	public void displayError(String txt) {
		msgLabel.setText(txt);
		msgLabel.setVisible(true);
	}

}
