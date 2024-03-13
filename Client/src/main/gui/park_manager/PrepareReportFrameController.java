package main.gui.park_manager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utilities.SceneController;
import javafx.scene.layout.AnchorPane;

public class PrepareReportFrameController extends Application implements Initializable{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@FXML
	private Label reportTypeHeader;
	@FXML
	private Pane createReportPane;
	@FXML
	private Label reportType;
	@FXML
	private DatePicker dateField;
	@FXML
	private AnchorPane leftPane;
	@FXML
	private AnchorPane rightPane;
	@FXML
	private TextField field_name;
	@FXML
	private TextField field_park;
	
	
	/**
    * @param primaryStage the primary stage for the application
    * @throws Exception if an error occurs during initialization
	*/
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature - Park Manager", primaryStage,
						  "/main/gui/park_manager/PrepareReportFrame.fxml");
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		reportTypeHeader.setText("Select report type that\n   you wish to create:");
		
	}
	
	public void createVisitorNumReport(ActionEvent event) {
		resetValues();
		reportType.setText("Type: Visitor Number Report");
		createReportPane.setVisible(true);
	}
	public void createEmptyParkReport(ActionEvent event) {
		resetValues();
		reportType.setText("Type: Empty Park Report");
		createReportPane.setVisible(true);
	}
	public void showReports(ActionEvent event) {
		
	}
	public void confirmReportCreation(ActionEvent event) {
		
	}
	public void cancelReportCreation(ActionEvent event) {
		resetValues();
		createReportPane.setVisible(false);
	}
	private void resetValues() {
		dateField.setValue(null);
		field_name.setText("");
	}

}
