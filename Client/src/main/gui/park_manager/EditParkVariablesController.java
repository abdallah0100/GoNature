package main.gui.park_manager;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entities.Park;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import main.ClientController;
import main.controllers.ParkRequestHandler;

public class EditParkVariablesController implements Initializable{
	 	
	@FXML
	private Button editEstimatedTimeBtn;
	@FXML
	private Button editVisitorBtn;
	@FXML
	private Button editGapBtn;
	@FXML
	private Pane rightPane;
	@FXML
	private Label editingVariable;
	@FXML
	private Label parkName;
	@FXML
	private Label msgLabel;
	@FXML
	private Label currentValue;
	@FXML
	private Button update;
	@FXML
	private Button cancel;
	
	@FXML
	private TextField newValue;
	public static String updateResult;
	@FXML
	public void selectVariableToEdit(ActionEvent e ) {
		newValue.setText("");
		msgLabel.setVisible(false);
	    Button clickedBtn = (Button)e.getSource();
	    editingVariable.setText(clickedBtn.getText());   
	    setValues();
	    rightPane.setVisible(true);
	}
	@FXML
	public void cancel(ActionEvent e ) {
		newValue.setText("");
		rightPane.setVisible(false);
		msgLabel.setVisible(false);
	}
	
	public void setValues() {
	    if (editingVariable.getText().contains("Gap"))
	    	currentValue.setText(ClientController.connectedUser.getPark().getGap() + "");
	    else if (editingVariable.getText().contains("Estimated"))
	    	currentValue.setText(ClientController.connectedUser.getPark().getEstimatedTime() + "");
	    else 
	    	currentValue.setText(ClientController.connectedUser.getPark().getMaxCapacity() + "");
	}
	
	@FXML
	public void update(ActionEvent e ) {
	    if (validInput()) {
	    	int newVal;
	    	Park mainPark = ClientController.getParks().get(parkName.getText());
	    	Park tempPark = new Park(mainPark.getParkName(), mainPark.getGap(), mainPark.getEstimatedTime(), mainPark.getMaxCapacity());
	    	if (editingVariable.getText().contains("Gap"))
	    		tempPark.setVarbToUpdate("gap");
	    	else if (editingVariable.getText().contains("Estimated"))
	    		tempPark.setVarbToUpdate("EstimatedTime");
	    	else
	    		tempPark.setVarbToUpdate("capacity");
	    	newVal = Integer.parseInt(newValue.getText());
	    	tempPark.setNewValue(newVal);
	    	ParkRequestHandler.updateVariable(tempPark);
	    	
	    	setValues();
	    	
	    	displayMsg(updateResult, 106, "white");
	    }
	}
	
	public boolean validInput() {
		if (newValue.getText() == null || newValue.getText().length() == 0) {
			displayMsg("Enter a new value to update", 106, "red");
			return false;
		}
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(newValue.getText());
		if (!m.matches()) {
			displayMsg("Enter a valid number", 129, "red");
			return false;
		}
		return true;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		parkName.setText(ClientController.connectedUser.getPark().getParkName());
		
	}
	
	public void displayMsg(String str, int layoutx, String color) {
		msgLabel.setText(str);
		msgLabel.setLayoutX(layoutx);
		msgLabel.setTextFill(Color.valueOf(color));
		msgLabel.setVisible(true);
	}
		    

}
