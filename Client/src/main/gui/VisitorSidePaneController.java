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
import main.gui.visitor.MakeReservationFrameController;
import utilities.SceneController;
import javafx.scene.input.MouseEvent;


public class VisitorSidePaneController extends Application implements Initializable{
	
	@FXML
	private  Label visitorLabel;
	@FXML
	private  Label visitorIdLabel;
	@FXML
	private Button makeReservation;
	@FXML
	private Button showReservation;
	@FXML
	private Button waitingList;
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
			if(!(ClientController.connectedVisitor.isFoundInDB())) {
				showReservation.setDisable(true);
				//makeReservation.setDisable(true);
			}
		}
	
	}
	
	//function to makeReservation
	public void makeReservation(ActionEvent e) {
		MakeReservationFrameController.o=null;
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/visitor/MakeReservationFrame.fxml");
	}
	
	//function to showReservation
	public void showReservation(ActionEvent e) {
		MakeReservationFrameController.o=null;
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/visitor/ShowReservationFrame.fxml");
	}
	//function to show waiting list
	public void waitingList(ActionEvent e) {
		MakeReservationFrameController.o=null;
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/visitor/WaitingListFrame.fxml");
	}
	public static void main(String[] args) {
		launch(args);
	}
	@FXML
	public void homePage(MouseEvent event) {
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/visitor/HomePage.fxml");
	}
}
