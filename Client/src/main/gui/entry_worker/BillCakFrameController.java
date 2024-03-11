package main.gui.entry_worker;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utilities.SceneController;

public class BillCakFrameController   extends Application {
	@FXML
	private TextField visitorid;
	
	@FXML
	private Button viewbill;
	

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
			sceneController.changeScene("GoNature - Entry Worker", primaryStage,
								    "/main/gui/entry_worker/BillCakFrame.fxml");
		}
	
}
