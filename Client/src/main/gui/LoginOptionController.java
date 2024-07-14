package main.gui;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.ClientController;
import utilities.SceneController;

/**
* The LoginOptionController class controls the user interface for login options.
* It provides functionality to switch between user login and visitor login frames,
* as well as closing the application.
*/
public class LoginOptionController extends Application implements Initializable{
	
	@FXML
	private Pane headerPane;
	
	@FXML
	private AnchorPane visitorPane;
	@FXML
	private AnchorPane userPane;
	

	
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
		
		try {
			Parent visitorPane2 = FXMLLoader.load(getClass().getResource("/main/gui/visitor/ValidationFrame.fxml"));
			visitorPane.getChildren().clear();
			visitorPane.getChildren().setAll(visitorPane2);
		} catch (IOException e) {
			System.out.println("[LoginOptionController] - Error loading visitor pane.");
			e.printStackTrace();
		}
		
		try {
			Parent visitorPane3 = FXMLLoader.load(getClass().getResource("/main/gui/LogInFrame.fxml"));
			userPane.getChildren().clear();
			userPane.getChildren().setAll(visitorPane3);
		} catch (IOException e) {
			System.out.println("[LoginOptionController] - Error loading user login pane.");
			e.printStackTrace();
		}
		
		
		
	}
	
}