package main.gui.visitor;

import java.net.URL;
import java.util.ResourceBundle;

import entities.AvailablePlace;
import entities.Order;
import entities.Park;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.ClientController;
import main.ClientUI;
import main.controllers.ParkRequestHandler;
import main.controllers.ReservationController;
import utilities.SceneController;

public class DeclinedReservationOptions implements Initializable{

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
	@FXML
	private Label maxCapacity;
	
	private static Order order;
	private static AvailablePlace[] avbl;
	public static boolean inserted=false;
	
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
	@FXML
	public void cancel(ActionEvent event) {
		MakeReservationFrameController.o=null;
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/visitor/HomePage.fxml");
	}
	
	@FXML
	public void editReservation(ActionEvent event) {
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/visitor/MakeReservationFrame.fxml");
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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ParkRequestHandler.requestAllParks();
		
		Park p = ClientController.getParks().get(order.getParkName());
		maxCapacity.setText((p.getMaxCapacity() - p.getGap())  + "");
	}
	
}
