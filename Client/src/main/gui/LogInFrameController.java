package main.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.ClientController;
import main.controllers.UserRequestController;
import main.gui.dep_manager.DepManagerMainFrameController;
import main.gui.entry_worker.EntryWorkerFrameController;
import main.gui.park_manager.PrepEditController;
import main.gui.service_agent.RegisterInstructorFrameController;
import utilities.SceneController;

public class LogInFrameController extends Application implements Initializable {
	
	@FXML
	private TextField userNameTxt;
	@FXML
	private TextField passwordTxt;
	@FXML
	private Button loginBtn;
	@FXML
	private Label msgLabel;

	/**
	* @param primaryStage the primary stage for the application
	* @throws Exception if an error occurs during initialization
	*/
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature - User", primaryStage,
									   "/main/gui/LogInFrame.fxml");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
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
		UserRequestController.sendUserLogIn(userNameTxt.getText(),passwordTxt.getText());
		if (UserRequestController.LogedIn) {
			 switch (ClientController.connectedUser.getRole()) {
	            case "depManager":
	            	SceneController.switchFrame("GoNature",e,new DepManagerMainFrameController());
	                break;
	            case "entryManager":
	            	SceneController.switchFrame("GoNature",e,new EntryWorkerFrameController());
	                break;
	            case "parkManager":
	            	SceneController.switchFrame("GoNature",e,new PrepEditController());
	                break;
	            default:
	            	SceneController.switchFrame("GoNature",e,new RegisterInstructorFrameController());
	                break;		
		}
			 }else {
			System.out.println("[LogInFrameController] - did not finished LogIn");
		}
	}
	
	public void displayError(String txt) {
		msgLabel.setText(txt);
		msgLabel.setVisible(true);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
