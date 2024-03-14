package main.gui.dep_manager;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utilities.SceneController;

public class DepManagerGenerateReportFrameController extends Application{
		
	
	@FXML
    private Button btn_cancellation_report;

    @FXML
    private Button btn_show_report_data;

    @FXML
    private Button btn_visitor_report;

    @FXML
    private TextField feild_name;

    @FXML
    private TextField field_date;

    @FXML
    private TextField field_park;

    @FXML
    private Button generateBtn;

	/**
	* @param primaryStage the primary stage for the application
	* @throws Exception if an error occurs during initialization
	*/
	@Override
	public void start(Stage primaryStage) throws Exception {
	SceneController sceneController = new SceneController();
	sceneController.changeScene("GoNature - Department Manager", primaryStage,
	"/main/gui/dep_manager/DepManagerGenerateReportFrame.fxml");
	}
			
			
	public static void main(String[] args) {
		launch(args);
	}

}


