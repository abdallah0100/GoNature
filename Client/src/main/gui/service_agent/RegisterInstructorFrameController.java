//only missing the check with the database and insertion to database
//it checks the fields that are not empty 
//  14/3/2024
package main.gui.service_agent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.controllers.UserRequestController;
import utilities.SceneController;


public class RegisterInstructorFrameController extends Application{
	public static String regist;
	  	@FXML
	    private Button btnRegister;
	    @FXML
	    private TextField idTxt;
	    @FXML
	    private Label label;

	 public static void main(String args[]) {
		 launch(args);
	 }
	
	
	/**
	* @param primaryStage the primary stage for the application
	* @throws Exception if an error occurs during initialization
    */
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature - Service Agent", primaryStage,
					    "/main/gui/service_agent/RegisterInstructorFrame.fxml");
		
	}
	
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
