package main;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.gui.ClientStartUpController;

/**
* The ClientUI class serves as the entry point for the GoNature client application.
* It launches the JavaFX application and initializes the client startup frame.
*/
public class ClientUI extends Application{
	
	public static Pane contentPane;
	public static Pane leftNavPane;
	public static Pane headerPane;
	
	/**
    * The main method of the GoNature client application.
    * Launches the JavaFX application.
    * @param args Command-line arguments (not used).
    */
	public static void main(String[] args) {
		launch(args);
	}

	
	/**
    * Initializes and starts the JavaFX application.
    * Loads the client startup frame.
    * @param primaryStage The primary stage of the JavaFX application.
    * @throws Exception If an error occurs during initialization.
    */
	@Override
	public void start(Stage primaryStage) throws Exception {
		//load the client startup frame
		ClientStartUpController startupC = new ClientStartUpController();
		startupC.start(primaryStage);
	}

}