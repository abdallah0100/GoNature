package main.gui.visitor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import utilities.SceneController;
//mayar test 1 2 3 4
public class ShowReservationFrameController extends Application implements Initializable{
	@SuppressWarnings("rawtypes")
	@FXML
	private TableColumn siteColumn;
	@SuppressWarnings("rawtypes")
	@FXML
	private TableColumn dateColumn;
	@SuppressWarnings("rawtypes")
	@FXML
	private TableColumn timeColumn;
	@SuppressWarnings("rawtypes")
	@FXML
	private TableColumn typeColumn;
	@SuppressWarnings("rawtypes")
	@FXML
	private TableColumn visitorNumberColumn;
	@FXML
	private Button updateBtn;

	public static void main(String[] args) {
		launch(args);
	}
	

	/**
	* @param primaryStage the primary stage for the application
	* @throws Exception if an error occurs during initialization
	*/
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature - Visitor/Instructor", primaryStage,
									"/main/gui/visitor/ShowReservationFrame.fxml");
	}
	
	
	
	//function shows reservations 
	public void showReservation(ActionEvent e) throws Exception{
			
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
