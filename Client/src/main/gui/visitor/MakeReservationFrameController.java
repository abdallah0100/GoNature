package main.gui.visitor;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import entities.Order;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.controllers.UserRequestController;
import main.controllers.VisitorRequestController;
import main.gui.MainFrameController;
import utilities.SceneController;

public class MakeReservationFrameController extends Application implements Initializable{
 
	@FXML
	private ComboBox<String> orderType;
	@FXML
	private TextField numOfVisitorsField;
	@FXML 
	private DatePicker dateField;
	@FXML
	private TextField hourField;
	@FXML
	private TextField minuteField;
	@FXML
	private ComboBox<String> parkNameField;
	@FXML
	private TextField phoneField;
	@FXML
	private TextField emailField;
	@FXML
	private Button bookBtn;
	@FXML
	private Label msgLabel;
	Order o;
	String str;
	LocalDate date;
	LocalDate today = LocalDate.now();
	
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
									"/main/gui/visitor/MakeReservationFrame.fxml");
	}	
	
	
	//function makes reservation 
	public void makeReservation(ActionEvent e) throws Exception{
		if(isValid()){
			loadData();
			VisitorRequestController.sendReservation(o);
			if (VisitorRequestController.finishedMakingReservation) {
				SceneController.switchFrame("GoNature",e,new MainFrameController());
			}
			else {
				System.out.println("[MakeReservationFrameController] - did not finished Making Reservation");		
			}	
		}
	}

	private boolean isValid() {
		//null input
		if(orderType.getValue()==null || numOfVisitorsField.getText().length() <= 0 || dateField.getValue()==null || hourField.getText().length() <= 0 || minuteField.getText().length()<= 0 || phoneField.getText().length() <= 0 || emailField.getText().length() <= 0 || parkNameField.getValue()==null) {
        	displayError("Please enter all fields");
			return false;}
		
		// should add a test to check if the organized group reservation is done by an instructor!!!!!
		
		//illegal input
		try {
			int numVisitors = Integer.parseInt(numOfVisitorsField.getText());
			if(15 < numVisitors || numVisitors <= 0  || ("Organized Group".equals(orderType.getValue()) && numVisitors == 1)) {
			displayError("Please enter a valid number of visitors");
			return false;}
		}catch(Exception e) {
			displayError("Please enter a valid number of visitors");
		    return false;
		}
		date = dateField.getValue();
		if(date != null && date.isBefore(today)) {
			displayError("Please enter a legal date");
			return false;}
		String hourPattern = "^([01]?[0-9]|2[0-3])$";
	    String text1 = hourField.getText();
	    // Check if the text matches the hour pattern
	    if(!(Pattern.matches(hourPattern, text1))) {
	    	displayError("Please enter a legal hour");
			return false;}
	    String minutesPattern = "^[0-5][0-9]$";
        String text2 = minuteField.getText();
        // Check if the text matches the minutes pattern
        if(!(Pattern.matches(minutesPattern, text2))) {
        	displayError("Please enter a legal minutes");
			return false;}
        String phonePattern = "^05\\d{8}$";
        String text3 = phoneField.getText();
        // Check if the text matches the phone number pattern
        if(!(Pattern.matches(phonePattern, text3))) {
        	displayError("Please enter a legal phone number");
			return false;}
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String text4 = emailField.getText();
        // Check if the text matches the email address pattern
        if(!(Pattern.matches(emailPattern, text4))) {
        	displayError("Please enter a legal email");
			return false;}
		return true;
    
	}
	private void loadData() {
		str = orderType.getValue();
		o.setOrderType(str);
		str = numOfVisitorsField.getText();
		o.setNumOfVisitors(str);		 
		date = dateField.getValue();
		str = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		o.setDate(str);
		str = hourField.getText();
		o.setHour(str);
		str = minuteField.getText();
		o.setMinute(str);
		str = parkNameField.getValue();
		o.setParkName(str);
		str = phoneField.getText();
		o.setphone(str);
		str = emailField.getText();
		o.setEmail(str);
	}
	private void setParkNameFieldComboBox() {
		ArrayList<String> al = new ArrayList<String>();	
		al.add("Park 1");
		al.add("Park 2");
		al.add("Park 3");

		ObservableList<String> list = FXCollections.observableArrayList(al);
		parkNameField.setItems(list);
	}
	
	private void setOrderTypeComboBox() {
	    ArrayList<String> al = new ArrayList<String>();	
		al.add("Private");
		al.add("Organized Group");

		ObservableList<String> list = FXCollections.observableArrayList(al);
		orderType.setItems(list);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setOrderTypeComboBox();
		setParkNameFieldComboBox();
		o = new Order();
		
	}
	public void displayError(String txt) {
		msgLabel.setText(txt);
		msgLabel.setVisible(true);
	}


}
