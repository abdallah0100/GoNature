package main.gui.park_manager;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utilities.SceneController;
public class PrepEditController extends Application {

	@FXML
	private Button preparereport;
	
	@FXML
	private Button editparkvarible;
	
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
		sceneController.changeScene("GoNature - Park Manager", primaryStage,
									"/main/gui/park_manager/PrepEdit.fxml");
	}
		
		
	public void preparereport() {	
		
	}
	
	public void editparkvarible() {
		
	}
}
