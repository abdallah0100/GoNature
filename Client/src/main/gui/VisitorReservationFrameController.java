package main.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utilities.SceneController;


public class VisitorReservationFrameController extends Application {
	
	@FXML
	private Pane visitorPane;
	
	/**
	* @param primaryStage the primary stage for the application
	* @throws Exception if an error occurs during initialization
	*/
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature - Visitor/Instructor", primaryStage,
							               "/main/gui/userReservationFrame.fxml");
	}
	
	//function to makeReservation
	public void makeReservation(ActionEvent e) {
		//TODO

	}
	//function to showReservation
	public void showReservation(ActionEvent e) {
		//TODO
	
	}
	//function to updateReservation
	public void updateReservation(ActionEvent e) {
		//TODO
	
	}
	//function to backToValidation
	public void backToValidation(ActionEvent e) {
		//TODO
	
	}
	public static void main(String[] args) {
		launch(args);
	}
}
