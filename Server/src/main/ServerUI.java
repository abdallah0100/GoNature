package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.databse.DatabaseConnector;
import main.gui.MainServerFrameController;
import main.gui.ServerPortFrameController;

public class ServerUI extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ServerPortFrameController c = new ServerPortFrameController();
		c.start(primaryStage);
		
	}
	
	public static void startServer(String p) {
		int port;
		try {
			port = Integer.parseInt(p);
		}catch(Exception e) {
			System.out.println("[ServerUI] - received invalid port: " + p);
			return;
		}
		//initiating database connection
		DatabaseConnector dbConnector = new DatabaseConnector();
		MainServer.dbConnection = dbConnector.getConnection();
		
		MainServer.PORT = p; // saving the connected port
		MainServer ms = new MainServer(port);
		MainServerFrameController serverController = new MainServerFrameController();
		
		try {
			// starting the main server gui
			serverController.start(new Stage()); 
		}catch(Exception e) {
			System.out.println("Error starting the main server");
			e.printStackTrace();
		}
		try {
			ms.listen();
		}catch(Exception ex) {
			System.out.println("[ServerUI] - Error listening to clients");
			ex.printStackTrace();
		}
		
	}

}
