package main.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.MainServer;
import main.ServerUI;

public class ServerPortFrameController extends Application{
	
	@FXML
	private TextField portField;
	@FXML
	private Button startServerBtn;
	@FXML
	private Button exitBtn;
	@FXML
	private Label errorLabel;
	@FXML
	private RadioButton radioDefault;
	@FXML
	private RadioButton radioEnter;
	@FXML
	private Pane dbPassPane;
	@FXML
	private PasswordField dbPassField;	
	@FXML
	private TextField visPass;
	@FXML
	private CheckBox showPassword;	
	

	//The initial function that starts on application start
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = new Pane();
		try {//trying to load the first gui (startUpGui)
			root = FXMLLoader.load(getClass().getResource("startUpGui.fxml"));
		}catch(Exception e) {
			System.out.println("[ServerPortFrameController] - Error loading startUpGui.fxml");
			e.printStackTrace();
			System.exit(1);
		}
		//setting the root to the loaded fxml file and showing the gui
		Scene scene = new Scene(root);
		primaryStage.setTitle("Server StartUp");
		primaryStage.setScene(scene);
		primaryStage.show();	
		
	}
	//The function that is called on Exit press, Exits the server application
	public void exitStartUp(ActionEvent event) throws Exception{
		System.out.println("[ServerFrameController] - Exiting system");
		System.exit(0);
	}
	
	//The function that starts on clicking on "Start" server, starts the server
	public void startConnection(ActionEvent event) throws Exception{
		String port = portField.getText();
		if (radioDefault.isSelected()) {
			MainServer.dbPassword = "Aa123456";
		}else {
			if (dbPassField.getText().length() > 0)
			{
				if(showPassword.isSelected())
					MainServer.dbPassword = visPass.getText();
				else
				{
					MainServer.dbPassword = dbPassField.getText();
				}
				
			}
			else {
				errorLabel.setText("Enter a database password");
				errorLabel.setLayoutX(78);
				errorLabel.setVisible(true);
				return;
			}
		}
		
		
		if (port.trim().isEmpty()) {
			System.out.println("[ServerFrameController] - No port was entered.");
			errorLabel.setText("Enter a port");
			errorLabel.setLayoutX(119);
			errorLabel.setVisible(true);
			return;
		}else {
			((Node)event.getSource()).getScene().getWindow().hide(); //hide port selection window
			ServerUI.startServer(port);
		}
	}
	
	public void showPassPane(ActionEvent e) {
		dbPassPane.setVisible(true);
	}
	
	public void hidePassPane(ActionEvent e) {
		dbPassPane.setVisible(false);
	}
	
	public void showPassword(ActionEvent e) {
		if(showPassword.isSelected())
		{
			visPass.setText(dbPassField.getText());
			visPass.setVisible(true);
			dbPassField.setVisible(false);
		}
		else {
			dbPassField.setText(visPass.getText());
			visPass.setVisible(false);
			dbPassField.setVisible(true);
			
		}
	}
	
}
