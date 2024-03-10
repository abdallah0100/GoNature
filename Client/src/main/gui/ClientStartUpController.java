package main.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ClientController;
import main.ClientUI;

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

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = new Pane();
		try {//trying to load the first gui (startUpGui)
			root = FXMLLoader.load(getClass().getResource("ClientStartUpFrame.fxml"));
		}catch(Exception e) {
			System.out.println("[ClientStartUpController] - Error loading ClientStartUpFrame.fxml");
			e.printStackTrace();
			System.exit(1);
		}
		//setting the root to the loaded fxml file and showing the gui
		Scene scene = new Scene(root);
		primaryStage.setTitle("GoNature Client startup - demo");
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
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
		//setting up a connection with the server
		ClientUI.clientController = new ClientController(ip, port);
		((Node)event.getSource()).getScene().getWindow().hide(); //hide port selection window
		ValidationFrameController validation = new ValidationFrameController();
		try {
			validation.start(new Stage());
		} catch (Exception e) {
			System.out.println("[ClientStartUpController] - Error starting validation client");
			e.printStackTrace();
		}
		
	}

}
