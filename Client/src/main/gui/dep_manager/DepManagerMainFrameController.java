package main.gui.dep_manager;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.ClientUI;
import utilities.SceneController;

public class DepManagerMainFrameController extends Application{
	
	
	@FXML
	 private Button viewReportsBtn;
	
	
	@FXML
	 private Button confirmChangeRequestBtn;
	
	
	@FXML
	 private Button generateReportBtn;
	
	
	
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
		sceneController.changeScene("GoNature - Department Manager", primaryStage,
							    "/main/gui/dep_manager/DepManagerMainFrame.fxml");
	}
	
	
	/**
	 * This method is responsible for opening new window after
	 * clicking on View Reports button
	* @param event 
	*/
	@FXML
	public void showviewReportsWindow(ActionEvent event) {	
		SceneController sc = new SceneController();
		sc.setPane(ClientUI.contentPane, "/main/gui/dep_manager/SelectReportFrame.fxml");
	}
	
	
	/**
	 * This method is responsible for opening new window after
	 * clicking on Generate Report button
	* @param event 
	*/
	@FXML
	public void showExportReportWindow(ActionEvent event) {	
		SceneController sc = new SceneController();
		sc.setPane(ClientUI.contentPane, "/main/gui/dep_manager/ExportReportFrame.fxml");
	}
	
	
	/**
	 * This method is responsible for opening new window after
	 * clicking on Confirm Change Request button
	* @param event 
	*/
	@FXML
	public void confirmChange(ActionEvent event) {
		
	}

}
