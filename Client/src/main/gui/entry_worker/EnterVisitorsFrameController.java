package main.gui.entry_worker;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entities.Order;
import entities.Park;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
	private Label insLabel;
	@FXML
	private Label typeLabel;
	Order o;
	String str;
	LocalDate date;
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
		dateField.setValue(LocalDate.now());
		minuteField.setText(Integer.toString(LocalTime.now().getMinute()));
		hourField.setText(Integer.toString(LocalTime.now().getHour()));
		dateField.setEditable(false);
		minuteField.setEditable(false);
		hourField.setEditable(false);
		//parkNameField.setEditable(false);
		//parkNameField.setText(ClientController.connectedUser.getParkName());
	}
	
	 public static void main(String args[]) {
		 launch(args);
	 }
	 
	 public void enter() {
		 Park p=new Park(null);
		 if(numOfVisitorsField.getText().equals(p.getGap())) {
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
				}//send to server to insert to reservation table
	 }
	 
	 
	 
	private void setOrderTypeComboBox() {
		    ArrayList<String> al = new ArrayList<String>();	
			al.add("Private");
			al.add("Organized Group");

			ObservableList<String> list = FXCollections.observableArrayList(al);
			orderType.setItems(list);
	}
	
	private void setValue() {
		orderType.setOnAction(event -> {
		    String selectedType = orderType.getValue();
		    if (selectedType != null && selectedType.equals("Organized Group")) {
		    	InstructoIdField.setLayoutX(182);
		    	InstructoIdField.setLayoutY(79);
		    	
		    	insLabel.setLayoutX(38);
		    	insLabel.setLayoutY(81);
		    	
		    	orderType.setLayoutX(182);
		    	orderType.setLayoutY(40);
		    	
		    	typeLabel.setLayoutX(38);
		    	typeLabel.setLayoutY(43);
		    	
		    	InstructoIdField.setVisible(true);
		    	insLabel.setVisible(true);
		    } else {
		    	typeLabel.setLayoutX(38);
		    	typeLabel.setLayoutY(59);
		    	
		    	orderType.setLayoutX(182);
		    	orderType.setLayoutY(56);
		    	InstructoIdField.setVisible(false);
		    	insLabel.setVisible(false);
		    }
		});	
		dateField.setValue(LocalDate.now());
		minuteField.setText(Integer.toString(LocalTime.now().getMinute()));
		hourField.setText(Integer.toString(LocalTime.now().getHour()));
		dateField.setEditable(false);
		minuteField.setEditable(false);
		hourField.setEditable(false);
	}

}
