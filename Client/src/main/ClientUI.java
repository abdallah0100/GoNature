package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.gui.ClientFrameController;

public class ClientUI extends Application{
	
	public static ClientController clientController;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//setting up a connection with the server
		clientController = new ClientController("localhost", 5555);
		//load the client frame
		ClientFrameController frameController = new ClientFrameController();
		frameController.start(primaryStage);
		
	}

}
