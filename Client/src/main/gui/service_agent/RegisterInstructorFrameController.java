//only missing the check with the database and insertion to database
//it checks the fields that are not empty 
//  14/3/2024
package main.gui.service_agent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.controllers.UserRequestController;


public class RegisterInstructorFrameController{
	public static String regist;
	  	@FXML
	    private Button btnRegister;
	    @FXML
	    private TextField idTxt;
	    @FXML
	    private Label label;
	
	public void register(ActionEvent e) {//we will check all the fields are not empty and not in database to add
		if(validInput()) {
			UserRequestController.registInstructor(idTxt.getText());
			if(regist.contentEquals("regest")){
				displayMsg("regist succesfully");
				return;
			}
			else {
				displayMsg(regist);
				return;
			}
		}
	}
	
	public void displayMsg(String txt) {
		label.setText(txt);
		label.setVisible(true);
	}
	
	public boolean validInput() {
		if (idTxt.getText() == null || idTxt.getText().length() < 9|| idTxt.getText().equals("0")) {
			displayMsg("Enter a new value to update");
			return false;
		}
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(idTxt.getText());
		if (!m.matches()) {
			displayMsg("Enter a valid number");
			return false;
		}
		return true;
	}
}
