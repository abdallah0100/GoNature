package main.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.ClientController;
import requests.Message;
import requests.RequestType;
import utilities.SceneController;

public class ClientStartUpController extends Application{
	
	@FXML
	private TextField serverIpTxt;
	@FXML
	private TextField portTxt;
	@FXML
	private Label errorMsg;

	@FXML
	private Button startBtn;
	
	@FXML
	private Button exitBtn;
	
	/**
	* @param primaryStage the primary stage for the application
	* @throws Exception if an error occurs during initialization
	*/
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature Client startup - demo", primaryStage,
							               "/main/gui/ClientStartUpFrame.fxml");
	}
	
	
	public void exitClient(ActionEvent e) {
		System.out.println("[ClientStartUpController]Exiting startup frame");
		System.exit(0);
	}
	
	public void startClient(ActionEvent event) {
		String ip = serverIpTxt.getText();
		String portString = portTxt.getText();
		int port;
		if (ip == null || portString == null || ip.length() == 0 || portString.length() == 0) {
			errorMsg.setText("Fill all fields");
			errorMsg.setVisible(true);
			return;
		}
		try {
			port = Integer.parseInt(portString);
		}catch(Exception ex) {
			errorMsg.setText("Invalid port was entered");
			errorMsg.setVisible(true);
			System.out.println("[ClientStartUpController] - entered port was not a number");
			return;
		}
		
		//getController() automatically creates a connection to the server, and then sending a connect request to notify the server
		ClientController.createInstance(ip, port);
		ClientController.getController().accept(new Message(RequestType.CONNECT_TO_SERVER));
		
		if (ClientController.connectedToServer) {
			LoginOptionController landingFrame = new LoginOptionController();
			SceneController.switchFrame("GoNature", event, landingFrame);	
		}else {
			errorMsg.setText("Failed to connect to the server.");
			errorMsg.setVisible(true);
		}
	}
	public static void main(String[] args) {
		launch(args);
	}

}
