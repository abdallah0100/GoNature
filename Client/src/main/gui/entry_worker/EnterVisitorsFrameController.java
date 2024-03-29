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
import main.ClientController;
import main.ClientUI;
import main.controllers.ParkRequestHandler;
import main.controllers.UserRequestController;
import main.controllers.VisitorRequestController;
import utilities.SceneController;


/**
* This class manages the actions and events related
* to entering visitor information by the entry worker.
*/
public class EnterVisitorsFrameController implements Initializable{
	
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

	
	/**
	 * filling the combo box with reservations types,
	 * date and park of the current entry worker that he work in.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setOrderTypeComboBox();
		setValue();
		
	}
	 
 
	 /**
	 * Handles the action event when the enter button is clicked.
	 * create a new reservation with the details inputed
	 * @param event The ActionEvent triggered when the button is clicked.
	 */
	 @FXML
	 public void enter1(ActionEvent event) {
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
	 
	 
	/**
	* Sets the items of the orderType ComboBox.
	* It populates the ComboBox with "Private" and "Organized Group" options.
	*/ 
	private void setOrderTypeComboBox() {
		    ArrayList<String> al = new ArrayList<String>();	
			al.add("Private");
			al.add("Organized Group");

			ObservableList<String> list = FXCollections.observableArrayList(al);
			orderType.setItems(list);
	}
	
	/**
    * Sets initial values for certain fields.
    */
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
	 
	
	/**
    * Checks if the park has enough places for the visitors.
    * @return true if there are enough places, otherwise false.
    */
	public boolean checkIfHasPlace(){
		ParkRequestHandler.requestPark(ClientController.connectedUser.getParkName());
		int gap=p.getGap();
		int number=p.getVisitorsWithoutOrder();

		return(Integer.parseInt(numOfVisitorsField.getText())+number<=gap);
	}
	
	
	/**
    * Validates the input entered by the entry worker.
    * @return true if the input is valid, otherwise false.
    */
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
	
	
	/**
    * Displays an error message.
    * @param txt The text of the error message.
    */
	public void displayError(String txt) {
		msgLabel.setText(txt);
		msgLabel.setVisible(true);
	}

}