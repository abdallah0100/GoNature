//shady built this class 10/3/2024 10:54
// Change Request Table frame controller class
package main.gui.dep_manager;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utilities.SceneController;

public class DepManagerChangeRequestTableFrameController extends Application {
	
	@FXML
	private AnchorPane sp;
	
	
		
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@FXML
    private TableColumn<?, ?> table_view;

	
	/**
	    * @param primaryStage the primary stage for the application
		* @throws Exception if an error occurs during initialization
	    */
		@Override
		public void start(Stage primaryStage) throws Exception {
			SceneController sceneController = new SceneController();
			sceneController.changeScene("GoNature - Department Manager", primaryStage,
					  "/main/gui/dep_manager/DepManagerChangeRequestTableFrame.fxml");
		}
}
