package main.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.controllers.ParkRequestHandler;
import main.controllers.UserRequestController;
import utilities.SceneController;


/**
* The LogInFrameController class controls the user interface for user login.
* It handles user input for username and password, and initiates the login process
* by communicating with the server. It also provides functionality to display error messages,
* navigate back to the previous frame, and close the application.
*/
public class LogInFrameController {
	
	@FXML
	private TextField userNameTxt;
	@FXML
	private PasswordField passwordTxt;
	@FXML
	private Button loginBtn;
	@FXML
	private Label msgLabel;

	public static boolean alreadyIn = false;
	
	
	
	/**
    * Handles the action event when the login button is clicked.
    * Initiates the user login process by sending user credentials to the server.
    * Displays appropriate error messages based on the login status.
    * @param e The action event triggered by clicking the login button.
    */
	public void userLogIn(ActionEvent e) {
		if (userNameTxt.getText().length() <= 0 && passwordTxt.getText().length() <= 0){
			displayError("Please enter your username and password");
			return;
		}
		if (userNameTxt.getText().length() <= 0){
			displayError("Please enter userName");
			return;
		}
		if (passwordTxt.getText().length() <= 0){
			displayError("Please enter password");
			return;
		}
		ParkRequestHandler.requestAllParks();
		UserRequestController.sendUserLogIn(userNameTxt.getText(), passwordTxt.getText());
		
		if (alreadyIn)
			displayError("User already logged in");
		else if (UserRequestController.LogedIn)
			SceneController.switchFrame("GoNature",e,new MainFrameController());
		else {
			System.out.println("[LogInFrameController] - did not finished LogIn");
			displayError("User Not Found");
		}
	}
	
	
    /**
    * Displays the given error message on the message label.
    * @param txt The error message to be displayed.
    */
	public void displayError(String txt) {
		msgLabel.setText(txt);
		msgLabel.setVisible(true);
	}
	

}