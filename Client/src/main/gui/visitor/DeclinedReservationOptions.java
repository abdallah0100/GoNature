package main.gui.visitor;

import entities.AvailablePlace;
import entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.ClientUI;
import main.controllers.ReservationController;
import utilities.SceneController;

/**
* This class represents the controller for the frame
* where visitors can choose options(enter waiting list or alternative times)
* after a reservation has been declined.
*/
public class DeclinedReservationOptions {

	@FXML
	private Label msgLabel;
	@FXML
	private Label msg;
	@FXML
	private Button cancelBtn;
	@FXML
	private Button editReservation;
	@FXML
	private Button waitingBtn;
	@FXML
	private TableView<AvailablePlace> availablePlaces;
	@FXML
	private TableColumn<AvailablePlace, String> dateCol;
	@FXML
	private TableColumn<AvailablePlace, String> timeCol;
	
	private static Order order;
	private static AvailablePlace[] avbl;
	public static boolean inserted=false;
	
	
	
	/**
    * Handles the action of entering the waiting list
    * after click Enter waiting list button and so the
    * visitor enter for the same time.
    * @param event The action event.
    */
	@FXML
	public void enterWaitingList(ActionEvent event) {
		ReservationController.enterWaitingList(order);
		if(inserted) {
			MakeReservationFrameController.o=null;
			waitingBtn.setDisable(true);
			msg.setText("inserted to waitng list");
			msg.setVisible(true);
			editReservation.setDisable(true);
			inserted=false;
			return;
		}
		msg.setText("error insert to waitng list");
		msg.setVisible(true);
		return;
	}
	
	
	
	/**
    * Displays available times for reservation for a new date.
    * @param event The action event.
    */
	@FXML
	public void showAvailableTimes(ActionEvent event) {
		if (avbl != null) {
			ObservableList<AvailablePlace> list = FXCollections.observableArrayList();
			list.setAll(avbl);
			
			availablePlaces.setItems(list);
			dateCol.setCellValueFactory(new PropertyValueFactory<AvailablePlace, String>("date"));
			timeCol.setCellValueFactory(new PropertyValueFactory<AvailablePlace, String>("time"));
			availablePlaces.setVisible(true);
		}else
			System.out.println("null");
	}
	
	
	/**
    * Handles the action of canceling the reservation
    * after clicking on Cancel Button and return back
    * to the home page.
    * @param event The action event.
    */
	@FXML
	public void cancel(ActionEvent event) {
		MakeReservationFrameController.o=null;
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/visitor/HomePage.fxml");
	}
	
	
	 /**
     * return back to make reservation page.
     * @param event The action event.
     */
	@FXML
	public void editReservation(ActionEvent event) {
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/visitor/MakeReservationFrame.fxml");
	}

    /**
    * Gets the order associated with the declined reservation.
    * @return The order object.
    */
	public static Order getOrder() {
		return order;
	}
	
	
	/**
    * Sets the order associated with the declined reservation.
    * @param order The order object to set.
    */
	public static void setOrder(Order order) {
		DeclinedReservationOptions.order = order;
	}
	
	
	/**
    * Gets the array of available places for reservation.
    * @return The array of available places.
    */
	public static AvailablePlace[] getAvbl() {
		return avbl;
	}
	
	
	/**
    * Sets the array of available places for reservation.
    * @param avbl The array of available places to set.
    */
	public static void setAvbl(AvailablePlace[] avbl) {
		DeclinedReservationOptions.avbl = avbl;
	}
}