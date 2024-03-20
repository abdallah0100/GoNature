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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import main.ClientController;
import main.ClientUI;
import main.controllers.VisitorRequestController;
import utilities.SceneController;
import javafx.util.converter.IntegerStringConverter;
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
	private Button cancelBtn;
	@FXML
    private TableView<Order> tableView;
	@FXML
	private Label msgLabel;
	String id;
	Order order;

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
	//function cancels reservation (removes it from DB) on action to cancelBtn
	public void cancelReservation(ActionEvent e) throws Exception {
		
	}
	public void updateReservation(ActionEvent e) throws Exception {
	    if (order != null) {	  
	    	 VisitorRequestController.updateReservation(order);
	    } else {
	    	displayError("Please select a row");
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
	    tableView.setRowFactory(tv -> {
	        TableRow<Order> row = new TableRow<>();
	        row.setOnMouseClicked(event -> {
	            if (!row.isEmpty() && event.getClickCount() == 1) {
	                updateBtn.setDisable(false);
	            } else {
	                updateBtn.setDisable(true);
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
	        order.setphone(event.getNewValue()); 
	        System.out.println(order.getPhone() + "'s Name was updated to "+ event.getNewValue() +" at row "+ (event.getTablePosition().getRow() + 1));
	      });
		emailColumn.setCellFactory(TextFieldTableCell.<Order>forTableColumn());
		emailColumn.setOnEditCommit(event ->{
			
	        order = event.getTableView().getItems().get(event.getTablePosition().getRow());
	        order.setEmail(event.getNewValue()); 
	        System.out.println(order.getEmail() + "'s Name was updated to "+ event.getNewValue() +" at row "+ (event.getTablePosition().getRow() + 1));
	      });
	
	}
	public void displayError(String txt) {
		msgLabel.setText(txt);
		msgLabel.setVisible(true);
	}

}
