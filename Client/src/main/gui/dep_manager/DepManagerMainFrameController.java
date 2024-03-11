package main.gui.dep_manager;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import utilities.SceneController;

public class DepManagerMainFrameController extends Application{
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
	
	public void viewReports(ActionEvent event) {
		
	}
	public void generateReport(ActionEvent event) {
		
	}
	public void confirmChange(ActionEvent event) {
		
	}

}
