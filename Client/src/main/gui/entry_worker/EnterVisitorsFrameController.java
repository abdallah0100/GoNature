package main.gui.entry_worker;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import entities.Order;
import entities.Park;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.ClientController;
import main.ClientUI;
import main.controllers.ParkRequestHandler;
import main.controllers.UserRequestController;
import main.controllers.VisitorRequestController;
import utilities.SceneController;

public class EnterVisitorsFrameController extends Application implements Initializable{
	
	@FXML
	private  ComboBox<String> orderType;
	@FXML
	private  TextField numOfVisitorsField;
	@FXML 
	private  DatePicker dateField;
	@FXML
	private  TextField hourField;
	@FXML
	private  TextField minuteField;
	@FXML
	private  TextField InstructoIdField;
	@FXML
	private  TextField parkNameField;
	@FXML
	private  TextField phoneField;
	@FXML
	private  TextField emailField;
	@FXML
	private Button enterBtn;
	@FXML
	private Label msgLabel;
	Order o;
	String str;
	LocalDate date;
	public static Park p;
	public static int orderID;
	public static boolean isInstructor;
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature - Entry Worker", primaryStage,
							"/main/gui/entry_worker/EnterVisitorsFrame.fxml");		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setOrderTypeComboBox();
		setValue();
		
	}
	
	 public static void main(String args[]) {
		 launch(args);
	 }
	 
	 public void enter(ActionEvent event) {
		// System.out.println("15265");
	 }
	 @FXML
	 public void enter1(ActionEvent event) {
		 System.out.println("15265");
		 o=new Order();
		 if(isValid()) {
			 if(checkIfHasPlace()) {
					o.setVisitorID(InstructoIdField.getText());
					o.setOrderType(orderType.getValue());
					o.setNumOfVisitors(numOfVisitorsField.getText());		 
					date = dateField.getValue();
					str = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					o.setDate(str);
					o.setHour(hourField.getText());
					o.setMinute(minuteField.getText());
					o.setParkName(parkNameField.getText());
					o.setphone(phoneField.getText());
					o.setEmail(emailField.getText());
					UserRequestController.walkInvist(o);//create a new function
					if(VisitorRequestController.finishedMakingReservation) {
						//get id order
					UserRequestController.getOrderId(parkNameField.getText());
					if(orderID!=0) {
						Alert infoAlert = new Alert(AlertType.INFORMATION);
				        infoAlert.setTitle("OrderID");
				        infoAlert.setHeaderText(null);
				        //infoAlert.setContentText("This is an information message. Order ID: " + ClientController.reservationMade.getOrderID());
				        infoAlert.setContentText("This is an information message. Order ID: " + orderID);					        
				        infoAlert.showAndWait();
						SceneController scene = new SceneController();
						scene.setPane(ClientUI.contentPane, "/main/gui/entry_worker/MonitoringFrame.fxml");
					}	
						else {
							System.out.println("error id");
							return;}
				}
					else {
						System.out.println("fail");
						return;
					}
					}
					else {
						displayError("No place");
						return;
					}
			 
		 }
		 	VisitorRequestController.finishedMakingReservation = false;	
			return;
	 }
	 
	 
	 
	private void setOrderTypeComboBox() {
		    ArrayList<String> al = new ArrayList<String>();	
			al.add("Private");
			al.add("Organized Group");

			ObservableList<String> list = FXCollections.observableArrayList(al);
			orderType.setItems(list);
	}
	
	private void setValue() {
		dateField.setValue(LocalDate.now());
		minuteField.setText(Integer.toString(LocalTime.now().getMinute()));
		hourField.setText(Integer.toString(LocalTime.now().getHour()));
		dateField.setEditable(false);
		minuteField.setEditable(false);
		hourField.setEditable(false);
		parkNameField.setEditable(false);
		parkNameField.setText(ClientController.connectedUser.getParkName());
	}
	 
	public boolean checkIfHasPlace(){
		ParkRequestHandler.requestPark(ClientController.connectedUser.getParkName());
		int gap=p.getGap();
		int number=p.getVisitorsWithoutOrder();

		return(Integer.parseInt(numOfVisitorsField.getText())+number<=gap);
	}
	
	private boolean isValid() {
		UserRequestController.checkInstructor(InstructoIdField.getText());
		if("Organized Group".equals(orderType.getValue()) && !isInstructor){
			displayError("the instructor not active");
			return false;
		}
		//null input
		if(orderType.getValue()==null || numOfVisitorsField.getText().length() <= 0 ) {
        	displayError("Please enter all fields");
			return false;}
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
	
	public void displayError(String txt) {
		msgLabel.setText(txt);
		msgLabel.setVisible(true);
	}

}
