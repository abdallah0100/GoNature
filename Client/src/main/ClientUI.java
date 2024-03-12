package main;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.gui.ClientStartUpController;

public class ClientUI extends Application{
	
	public static Pane contentPane;
	public static Pane leftNavPane;
	public static Pane headerPane;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//load the client startup frame
		ClientStartUpController startupC = new ClientStartUpController();
		startupC.start(primaryStage);
	}

}
