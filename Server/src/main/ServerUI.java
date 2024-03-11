package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.gui.MainServerFrameController;

public class ServerUI extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainServerFrameController frame = new MainServerFrameController();
		frame.start(primaryStage);
		
	}

}
