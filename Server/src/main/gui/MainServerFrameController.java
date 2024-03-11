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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Constants;
import main.MainServer;
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
	@FXML
	private Button startServerBtn;


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
		if (!(serverPortField.getText().length() > 0 || dbNameField.getText().length() > 0 ||
			dbUserField.getText().length() > 0 || dbPassField.getText().trim().length() > 0)) {
			msgLabel.setText("Please fill all fields to start the server.");
			msgLabel.setLayoutX(195);
			msgLabel.setTextFill(Color.valueOf("red"));
			msgLabel.setVisible(true);
			return;
		}
		try {
			Integer.parseInt(serverPortField.getText());
		}catch(Exception ex) {
			System.out.println("[MainServerFrameController] - inputted port is not an integer");
			return;
		}
		
		MainServer.startServer(serverPortField.getText(), dbNameField.getText(), dbUserField.getText(), dbPassField.getText());
		lockFieldsAfterStart();
	}
	
	public void lockFieldsAfterStart() {
		if (MainServer.serverStarted) {
			serverPortField.setDisable(true);
			dbNameField.setDisable(true);
			dbUserField.setDisable(true);
			dbPassField.setDisable(true);
			
			serverStatusLabel.setText("Online");
			serverStatusLabel.setTextFill(Color.valueOf("green"));
			if (MainServer.dbConnection != null) {
				DBStatusLabel.setText("Connected");
				DBStatusLabel.setTextFill(Color.valueOf("green"));
			}else
				DBStatusLabel.setTextFill(Color.valueOf("red"));
			
			startServerBtn.setDisable(true);
			msgLabel.setVisible(false);
			
		}
	}
	
	public void stopServer(ActionEvent event) {
		if (!MainServer.serverStarted) {
			msgLabel.setText("You can not stop the server when it is offline");
			msgLabel.setTextFill(Color.valueOf("red"));
			msgLabel.setLayoutX(175);
			msgLabel.setVisible(true);
			return;
		}
		serverPortField.setDisable(false);
		dbNameField.setDisable(false);
		dbUserField.setDisable(false);
		dbPassField.setDisable(false);
		serverStatusLabel.setText("Offline");
		serverStatusLabel.setTextFill(Color.valueOf("red"));
		DBStatusLabel.setText("Offline");
		DBStatusLabel.setTextFill(Color.valueOf("red"));
		MainServer.getInstance().closeConnection();
		
		startServerBtn.setDisable(false);
	}

}
