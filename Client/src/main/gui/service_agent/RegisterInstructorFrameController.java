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

/**
 * Controller class for the registration of instructors that is made by the service agent
 * This class manages the registration process by validating input field
 */
public class RegisterInstructorFrameController{
	public static String regist;
	  	@FXML
	    private Button btnRegister;
	    @FXML
	    private TextField idTxt;
	    @FXML
	    private Label label;
	
	
	/**
	* Registers an instructor when the register button is clicked.
	* Checks if input is valid and if the instructor is not already registered in the database.
	* Displays appropriate messages based on the registration result.
	* @param e The ActionEvent triggered by clicking the register button.
	*/
	public void register(ActionEvent e) {//we will check all the fields are not empty and not in database to add
		if(validInput()) {
			UserRequestController.registInstructor(idTxt.getText());
			if(regist.contentEquals("regest")){
				displayMsg("regist succesfully");
				return;
			}
			else {
				displayMsg("Instructor was not registered.");
				return;
			}
		}
	}
	
	/**
    * Displays a label message.
    * @param txt The text to be displayed.
    */
	public void displayMsg(String txt) {
		label.setText(txt);
		label.setVisible(true);
	}
	
	 /**
     * Validates the input fields.
     * Checks if the ID field is not empty, not equal to zero, and contains a valid number.
     * @return true if input is valid, false otherwise.
     */
	public boolean validInput() {
		if (idTxt.getText() == null || idTxt.getText().length() != 9) {
			displayMsg("Enter a valid id");
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