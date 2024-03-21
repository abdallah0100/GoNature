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
	* @param primaryStage the primary stage for the application
	* @throws Exception if an error occurs during initialization
	*/
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController.stage = primaryStage;
		primaryStage.initStyle(StageStyle.UNDECORATED);
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature - User", primaryStage,
									   "/main/gui/LogInFrame.fxml");
	}
	
	//function for userLogIn
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
	
	public void displayError(String txt) {
		msgLabel.setText(txt);
		msgLabel.setVisible(true);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@FXML
	public void goBack(MouseEvent event) {
		SceneController.switchFrame("GoNature - Client", event, new LoginOptionController());
	}
	@FXML
	public void closeApp(MouseEvent event) {
		ClientController.getController().getClient().quit();
		System.exit(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SceneController.headerPane = headerPane;
		SceneController.setUpHeaderDrag();
		
	}

}
