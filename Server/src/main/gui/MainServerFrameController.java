package main.gui;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Constants;
import utilities.SceneController;

public class MainServerFrameController extends Application implements Initializable{
	
	@FXML
	private Label serverStatusLabel;	
	@FXML
	private Label serverIPLabel;
	@FXML
	private Label DBStatusLabel;
	@FXML
	private TextField serverPortField;
	@FXML
	private TextField dbNameField;
	@FXML
	private TextField dbUserField;
	@FXML
	private TextField dbPassField;
	@FXML
	private Label msgLabel;
	@FXML
	private Button stopServerBtn;


	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController scene = new SceneController();
		scene.changeScene("GoNature - Server", primaryStage, "/main/gui/MainServer.fxml");
	}
	/*
	 * a function to set up the labels of the server data
	 */
	public void setUpUI() {
		try {
			serverIPLabel.setText(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			System.out.println("[MainServerFrameController] - error getting local host address");
			e.printStackTrace();
		}
		serverPortField.setText(Constants.DEFAULT_PORT);
		dbNameField.setText(Constants.DB_NAME);
		dbUserField.setText(Constants.DB_USERNAME);
	}
	
	public void exitServer(ActionEvent e) {
		System.out.println("[MainServerFrameController] - Exiting server");
		System.exit(0);
	}
	
	//a function that is called when the class is loaded up (from the fxml file in our case)
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//we add the setup here, because the labels are not initiliazed yet untill this function is called
		setUpUI();
	}
	
	public void startServer(ActionEvent event) {
		
	}
	
	public void stopServer(ActionEvent event) {
		
	}

}
