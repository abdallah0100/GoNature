//shady built this class 10/3/2024 0:01
// view report frame controller class
package main.gui.dep_manager;

import javafx.scene.control.Button;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;
import utilities.SceneController;


public class DepManagerViewReportFrameController extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	
	 @FXML
	    private MenuButton btn_date;

	    @FXML
	    private MenuButton btn_type;

	    @FXML
	    private Button btn_view;

	
	    /**
		* @param primaryStage the primary stage for the application
	    * @throws Exception if an error occurs during initialization
	    */
	    @Override
		public void start(Stage primaryStage) throws Exception {
			SceneController sceneController = new SceneController();
			sceneController.changeScene("GoNature - Department Manager", primaryStage,
					          "/main/gui/dep_manager/DepManagerViewReportFrame.fxml");
		}
	
}
