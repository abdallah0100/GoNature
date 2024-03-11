package main.gui.visitor;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utilities.SceneController;

public class MakeReservationFrameController extends Application implements Initializable{

	@FXML
	private ComboBox<String> orderType;
	@FXML
	private TextField numOfVisitorsField;
	@FXML
	private DatePicker dateField;
	@FXML
	private ComboBox<String> timeField;
	@FXML
	private ComboBox<String> parkNameField;
	@FXML
	private TextField phoneField;
	@FXML
	private TextField emailField;
	@FXML
	private Button bookBtn;
	
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
									"/main/gui/visitor/MakeReservationFrame.fxml");
	}
	
	
	//function makes reservation 
	public void makeReservation(ActionEvent e) throws Exception{
		
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
