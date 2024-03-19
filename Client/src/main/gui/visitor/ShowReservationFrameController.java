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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.ClientController;
import main.ClientUI;
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
	private Button updateBtn;
	@FXML
    private TableView<Order> tableView;
	String id;
	Order selectedOrder;
	public static boolean updatedBtn = false;



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

	public void updateReservation(ActionEvent e) throws Exception {
		updatedBtn = true;
	    selectedOrder = tableView.getSelectionModel().getSelectedItem();
	    if (selectedOrder != null) {	  
	    	//
	    	SceneController scene = new SceneController();
	        scene.setPane(ClientUI.contentPane, "/main/gui/visitor/MakeReservationFrame.fxml");
	    } else {
	        System.out.println("No row selected. Update button should be disabled.");
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
				//SceneController.switchFrame("GoNature",e,new MainFrameController());
			}
			else {
				System.out.println("[ShowReservationFrameController] - did not finished Showing Reservations");		
			}
		}	
	}	
}
