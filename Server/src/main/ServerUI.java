package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.gui.MainServerFrameController;

/**
 * Entry point for the server application with a graphical user interface.
 * This class extends from Application to leverage JavaFX for creating
 * and managing the GUI elements.
 */
public class ServerUI extends Application{
	
	/**
	 * The main method that serves as the entry point of the application.
	 * It invokes the JavaFX application launch process.
	 * 
	 * @param args Command-line arguments passed during application startup.
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Starts the primary stage of the application, setting up the main server window.
	 * This method is called after the application has been initialized.
	 * 
	 * @param primaryStage The primary stage for this application.
	 * @throws Exception if there is an error initializing the stage.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		MainServerFrameController frame = new MainServerFrameController();
		frame.start(primaryStage);
		
	}

}
