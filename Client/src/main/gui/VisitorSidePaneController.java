package main.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.ClientController;
import main.ClientUI;
import main.controllers.VisitorRequestController;
import utilities.SceneController;


public class VisitorSidePaneController extends Application implements Initializable{
	
	@FXML
	private  Label visitorIdLabel;
	@FXML
	private  Label existingVisitor;
	@FXML
	private Button makeReservation;
	@FXML
	private Button showReservation;
	@FXML
	private Button updateReservation;
	String id;
	
	
	/**
	* @param primaryStage the primary stage for the application
	* @throws Exception if an error occurs during initialization
	*/
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature - Visitor/Instructor", primaryStage,
							               "/main/gui/VisitorSidePane.fxml");
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (ClientController.connectedVisitor != null) {
			visitorIdLabel.setText(ClientController.connectedVisitor.getId());
			existingVisitor.setText(ClientController.connectedVisitor.isFoundInDB() + "");
			if(!(ClientController.connectedVisitor.isFoundInDB())) {
				updateReservation.setDisable(true);
				showReservation.setDisable(true);
				makeReservation.setDisable(true);
			}
		}
		
	}
	
	//function to makeReservation
	public void makeReservation(ActionEvent e) {
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/visitor/MakeReservationFrame.fxml");
	}
	
	//function to showReservation
	public void showReservation(ActionEvent e) {
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/visitor/ShowReservationFrame.fxml");
	}
	
	//function to updateReservation
	public void updateReservation(ActionEvent e) {
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/visitor/UpdateReservationFrame.fxml");
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
