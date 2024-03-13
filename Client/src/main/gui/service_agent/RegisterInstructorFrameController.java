package main.gui.service_agent;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utilities.SceneController;


public class RegisterInstructorFrameController extends Application{
	
	 @FXML
	 private TextField nameField;
	 
	 @FXML
	 private TextField IDField;
	 @FXML
	 private TextField EmailField;

	 @FXML
	 private TextField TelephoneField;
	 @FXML
	 private Button registerBtn;
	 
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
	
	public void register(ActionEvent e) {
		//TODO
	}

}
