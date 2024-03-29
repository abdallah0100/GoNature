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


/**
* The ClientStartUpController class is responsible for controlling the initial startup interface
* for the GoNature client application. It handles user input for server IP address and port number,
* and initiates the connection to the server upon clicking the start button.
*/
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
     * Initializes and launches the JavaFX application.
     * @param primaryStage The primary stage of the JavaFX application.
     * @throws Exception If an error occurs during initialization.
     */
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature Client startup - demo", primaryStage,
							               "/main/gui/ClientStartUpFrame.fxml");
	}
	
	/**
    * Handles the action event when the exit button is clicked.
    * Exits the client application.
    * @param e The action event triggered by clicking the exit button.
    */
	public void exitClient(ActionEvent e) {
		System.out.println("[ClientStartUpController]Exiting startup frame");
		System.exit(0);
	}
	
	
	/**
    * Handles the action event when the start button is clicked.
    * Initiates the connection to the server based on user input for server IP and port.
    * @param event The action event triggered by clicking the start button.
    */
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
			errorMsg.setText("Failed to connect to the server");
			errorMsg.setVisible(true);
		}
	}
}