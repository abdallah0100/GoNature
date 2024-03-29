package main.gui;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.ClientController;
import main.gui.visitor.ValidationFrameController;
import utilities.SceneController;

/**
* The LoginOptionController class controls the user interface for login options.
* It provides functionality to switch between user login and visitor login frames,
* as well as closing the application.
*/
public class LoginOptionController extends Application implements Initializable{
	
	@FXML
	private Pane headerPane;

	
	/**
    * Initializes and launches the JavaFX application.
    * @param primaryStage The primary stage of the JavaFX application.
    * @throws Exception If an error occurs during initialization.
    */
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.initStyle(StageStyle.UNDECORATED);
		SceneController.stage = primaryStage;
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature Client", primaryStage,
							               "/main/gui/LoginOptionFrame.fxml");
	}
	
	
	/**
    * Switches to the user login frame when the user login button is clicked.
    * @param e The action event triggered by clicking the user login button.
    */
	public void userLogin(ActionEvent e) {
		String s="LogInFrameController";
		LogInFrameController LogInFrame = new LogInFrameController();
		SceneController.switchFrame(s, e, LogInFrame);
	}
	
	
	/**
    * Switches to the visitor login frame when the visitor login button is clicked.
    * @param e The action event triggered by clicking the visitor login button.
    */
	public void visitorLogin(ActionEvent e) {
		String s="ValidationFrameController";
		ValidationFrameController ValidationFrame = new ValidationFrameController();
		SceneController.switchFrame(s, e, ValidationFrame);
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