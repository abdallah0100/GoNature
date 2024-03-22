package main.gui.visitor;

import entities.AvailablePlace;
import entities.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.ClientUI;
import utilities.SceneController;

public class DeclinedReservationOptions {

	@FXML
	private Label msgLabel;
	@FXML
	private Button cancelBtn;
	@FXML
	private Button editReservation;
	@FXML
	private TableView<AvailablePlace> availablePlaces;
	@FXML
	private TableColumn<AvailablePlace, String> dateCol;
	@FXML
	private TableColumn<AvailablePlace, String> timeCol;
	
	private static Order order;
	private static AvailablePlace[] avbl;
	
	@FXML
	public void enterWaitingList(ActionEvent event) {
	//	ReservationController.enterWaitingList(order);
	}
	@FXML
	public void showAvailableTimes(ActionEvent event) {
		
		dateCol.setCellValueFactory(new PropertyValueFactory<AvailablePlace, String>("date"));
		timeCol.setCellValueFactory(new PropertyValueFactory<AvailablePlace, String>("time"));
		availablePlaces.setVisible(true);
	}
	@FXML
	public void cancel(ActionEvent event) {
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/visitor/HomePage.fxml");
	}
	
	@FXML
	public void editReservation(ActionEvent event) {
		System.out.println("Todo - go back to make reservation and have all data inputted");
	}
	
	public static Order getOrder() {
		return order;
	}
	public static void setOrder(Order order) {
		DeclinedReservationOptions.order = order;
	}
	public static AvailablePlace[] getAvbl() {
		return avbl;
	}
	public static void setAvbl(AvailablePlace[] avbl) {
		DeclinedReservationOptions.avbl = avbl;
	}
	
}
