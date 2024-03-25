package main.gui.visitor;

import java.net.URL;
import java.util.ResourceBundle;
import entities.Order;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utilities.SceneController;

public class WaitingListFrameController extends Application	implements Initializable{
	
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
	private TableColumn<Order, String> minuteColumn;
	@FXML
	private Button deleteBtn;
	@FXML
    private TableView<Order> tableView;
	Order selectedOrder;
	
	public static void main(String args[]){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature - Visitor/Instructor", primaryStage,
							"/main/gui/visitor/WaitingListFrame.fxml");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		siteColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("parkName"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("hour"));
		typeColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("orderType"));
		visitorNumberColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("numOfVisitors"));		
		minuteColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("minute"));
		deleteBtn.setDisable(true);
		tableView.setRowFactory(tv -> {
	        TableRow<Order> row = new TableRow<>();
	        row.setOnMouseClicked(event -> {
	            if (!row.isEmpty() && event.getClickCount() == 1) {
	                deleteBtn.setDisable(false);
	            } else {
	                deleteBtn.setDisable(true);

	            }
	        });
	        return row;
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
	                        delete(event);
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                }
	            });
	        }
	    });
	}
	//delete button deletes a reservation from the waiting list
	public void delete(ActionEvent e) {
		System.out.println("Hi");
	}

	
}
