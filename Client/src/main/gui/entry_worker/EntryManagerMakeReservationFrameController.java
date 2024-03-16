package main.gui.entry_worker;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utilities.SceneController;

public class EntryManagerMakeReservationFrameController extends Application implements Initializable{
	
	@FXML
	private ComboBox<String> orderType;
	@FXML
	private TextField numOfVisitorsField;
	@FXML
	private DatePicker dateField;
	@FXML
	private ComboBox<String> parkNameField;
	@FXML
	private TextField phoneField;
	@FXML
	private TextField emailField;
	@FXML
	private Button bookBtn;
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController sceneController = new SceneController();
 		sceneController.changeScene("GoNature - Entry Worker", primaryStage,
 					    "/main/gui/entry_worker/EntryManagerMakeReservationFrameController.fxml");
		// TODO Auto-generated method stub
		
	}
	

	private void setOrderTypeComboBox() {
		ArrayList<String> al = new ArrayList<String>();	
		al.add("Small Group");
		al.add("Individual");
		al.add("Orgnized Group");

		ObservableList<String> list = FXCollections.observableArrayList(al);
		orderType.setItems(list);
	}


	private void setParkNameFieldComboBox() {
		ArrayList<String> al = new ArrayList<String>();	
		al.add("Park 1");
		al.add("Park 2");
		al.add("Park 3");

		ObservableList<String> list = FXCollections.observableArrayList(al);
		parkNameField.setItems(list);
	}
	
	public void book(ActionEvent e) {
		System.out.println("Booked");
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setOrderTypeComboBox();

		setParkNameFieldComboBox();		
	}

}
