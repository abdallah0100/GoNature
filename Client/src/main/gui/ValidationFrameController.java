package main.gui;

import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import utilities.SceneController;

public class ValidationFrameController extends Application implements Initializable {
	@FXML
	private TextField idTxt;
	
	/**
	* @param primaryStage the primary stage for the application
	* @throws Exception if an error occurs during initialization
	*/
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature - Validation", primaryStage,
										"/main/gui/ValidationFrame.fxml");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub	
	}
	//function to Enter the user 
	public void Enter(ActionEvent e) {
		//TODO
	}
	
	public void Exit(ActionEvent e) {
		//TODO
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
