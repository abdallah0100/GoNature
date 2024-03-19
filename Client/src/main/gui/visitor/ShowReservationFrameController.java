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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.ClientController;
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
	private Button updateBtn;
	@FXML
    private TableView<Order> tableView;
	String id;

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
	
	
	
	//function shows reservations 
	public void showReservation(ActionEvent e) throws Exception{
		/*if(ClientController.connectedVisitor.isFoundInDB()) {	
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
		}*/
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		siteColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("parkName"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("time"));
		typeColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("orderType"));
		visitorNumberColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("numOfVisitors"));
		
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
