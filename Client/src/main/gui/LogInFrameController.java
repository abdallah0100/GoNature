package main.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.ClientController;
import main.controllers.ParkRequestHandler;
import main.controllers.UserRequestController;
import utilities.SceneController;


/**
* The LogInFrameController class controls the user interface for user login.
* It handles user input for username and password, and initiates the login process
* by communicating with the server. It also provides functionality to display error messages,
* navigate back to the previous frame, and close the application.
*/
public class LogInFrameController extends Application implements Initializable{
	
	@FXML
	private TextField userNameTxt;
	@FXML
	private PasswordField passwordTxt;
	@FXML
	private Button loginBtn;
	@FXML
	private Label msgLabel;
	@FXML
	private Pane headerPane;

	public static boolean alreadyIn = false;
	
	
	
	/**
    * Initializes and launches the JavaFX application.
    * @param primaryStage The primary stage of the JavaFX application.
    * @throws Exception If an error occurs during initialization.
    */
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController.stage = primaryStage;
		primaryStage.initStyle(StageStyle.UNDECORATED);
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature - User", primaryStage,
									   "/main/gui/LogInFrame.fxml");
	}
	
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
	
	
	/**
    * Navigates back to the previous frame when the arrow icon is clicked.
    * @param event The mouse event triggered by clicking the header pane.
    */
	@FXML
	public void goBack(MouseEvent event) {
		SceneController.switchFrame("GoNature - Client", event, new LoginOptionController());
	}
	
	
	/**
    * Closes the application when the close button is clicked.
    * @param event The mouse event triggered by clicking the close button.
    */
	@FXML
	public void closeApp(MouseEvent event) {
		ClientController.getController().getClient().quit();
		System.exit(0);
	}

	/**
    * Initializes the controller after its root element
    * has been completely processed,and let the header
    * be able to touch to move the GUI window.
    * @param location The location used to resolve relative paths for the root object.
    * @param resources The resources used to localize the root object.
    */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SceneController.headerPane = headerPane;
		SceneController.setUpHeaderDrag();
		
	}
}