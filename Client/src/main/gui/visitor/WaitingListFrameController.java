package main.gui.visitor;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import main.ClientController;
import main.controllers.VisitorRequestController;


/**
* Controller class for managing the Waiting List Frame,
* which displays reservations on the waiting list.
*/
public class WaitingListFrameController implements Initializable{
	
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
	
	public static boolean deleteResult = false;
	private ObservableList<Order> list;
	
	/**
	    * Sets up the TableView to display reservations on the waiting list.
	    * Enables or disables the delete button based on row selection in the TableView.
	    * Handles the action event when the delete button is clicked to confirm and delete a reservation.
	    * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
	    * @param resources The resources used to localize the root object, or null if the root object was not localized.
	    */
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		VisitorRequestController.fetchWaitingList(ClientController.connectedVisitor.getId());
		list = FXCollections.observableArrayList();
		
		if (ClientController.connectedVisitor.getWaitingList() != null)
			list.setAll(ClientController.connectedVisitor.getWaitingList());
		
		tableView.setItems(list);
		
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

	/**
	    * Deletes the selected reservation from the waiting list.
	    * @param e The ActionEvent triggered when the delete button is clicked.
	    */
	public void delete(ActionEvent e) {
		if (selectedOrder != null) {
       		VisitorRequestController.deleteOrderFromWaitingList(selectedOrder.getOrderID());
    		if (deleteResult)
    			list.remove(selectedOrder);
    		deleteResult = false;
		}
	}
}
