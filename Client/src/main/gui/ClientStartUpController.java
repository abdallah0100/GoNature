package main.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.ClientController;
import requests.Message;
import requests.RequestType;
import utilities.SceneController;

public class ClientStartUpController extends Application implements Initializable{
	
	@FXML
	private TextField serverIpTxt;
	@FXML
	private TextField portTxt;
	@FXML
	private Label errorMsg;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	
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
			errorMsg.setText("Fill all fields to continue.");
			errorMsg.setVisible(true);
			return;
		}
		try {
			port = Integer.parseInt(portString);
		}catch(Exception ex) {
			errorMsg.setText("Invalid port was entered.");
			errorMsg.setVisible(true);
			System.out.println("[ClientStartUpController] - entered port was not a number");
			return;
		}
		
		ClientController.HOST = ip;
		ClientController.PORT = port;
		
		ClientController.getController().accept(new Message(RequestType.CONNECT_TO_SERVER));
		
		//setting up a connection with the server
		ValidationFrameController validation = new ValidationFrameController();
		SceneController.switchFrame("GoNature", event, validation);	
	}
	public static void main(String[] args) {
		launch(args);
	}

}
