package main.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utilities.SceneController;

public class LogInFrameController extends Application implements Initializable {
	
	@FXML
	private TextField userNameTxt;
	@FXML
	private TextField passwordTxt;
	

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
		//TODO
	}
	public static void main(String[] args) {
		launch(args);
	}

}
