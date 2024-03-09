package main.gui.parkmanager;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
	private TextField parkNameField;


	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = new Pane();
		try {//trying to load the first gui (startUpGui)
			root = FXMLLoader.load(getClass().getResource("prepareReportFrame.fxml"));
		}catch(Exception e) {
			System.out.println("[prepareReportFrameController] - Error loading prepareReportFrame.fxml");
			e.printStackTrace();
			System.exit(1);
		}
		//setting the root to the loaded fxml file and showing the gui
		Scene scene = new Scene(root);
		primaryStage.setTitle("GoNature Client");
		primaryStage.setScene(scene);
		
		primaryStage.show();			
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
		parkNameField.setText("");
	}

}
