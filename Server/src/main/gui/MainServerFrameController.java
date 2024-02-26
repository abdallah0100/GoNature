package main.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import OCSF.src.ocsf.server.ConnectionToClient;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.MainServer;
import main.controllers.ServerController;

public class MainServerFrameController extends Application implements Initializable{
	
	@FXML
	private Label serverStatusLabel;	
	@FXML
	private Label serverPortLabel;
	@FXML
	private Label DBStatusLabel;
	@FXML
	private Button exitBtn;
	@FXML
	private Button showConnectionsBtn;
	@FXML
	private Pane connectionsPane;
	@FXML
	private ListView<String> connectionsList;


	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = new Pane();
		try {//trying to load the main gui (startUpGui)
			root = FXMLLoader.load(getClass().getResource("MainServer.fxml"));
		}catch(Exception e) {
			System.out.println("[MainServerFrameController] - Error loading MainServer.fxml");
			e.printStackTrace();
			System.exit(1);
		}
		
		//setting the root to the loaded fxml file and showing the gui
		Scene scene = new Scene(root);
		primaryStage.setTitle("GoNature Server");
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
	}
	/*
	 * a function to set up the labels of the server data
	 */
	public void setUpUI() {
		try {
			serverStatusLabel.setVisible(true);
			serverPortLabel.setText(MainServer.PORT);
			if (MainServer.dbConnection != null) {
				DBStatusLabel.setText("Connected");
			}else {
				DBStatusLabel.setText("Failed");
				DBStatusLabel.setTextFill(Color.valueOf("red"));
			}
			
		}catch(Exception e) {
			System.out.println("[MainServerFrameController.setUpUI] - error setting up the UI");
			e.printStackTrace();
		}
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
	
	public void loadClients() {
		if (ServerController.getInstance().getClients().size() == 0) {
			connectionsList.getItems().setAll("None");
		}else {
			ArrayList<ConnectionToClient> clients = ServerController.getInstance().getClients();
			ArrayList<String> clientNames = new ArrayList<>();
			for (int i = 0; i < clients.size(); i++)
				clientNames.add(clients.get(i).toString());
			connectionsList.getItems().setAll(clientNames);

		}
	}
	
	public void showConnectionsClick(ActionEvent event) {
		loadClients();
		connectionsPane.setVisible(true);
		showConnectionsBtn.setVisible(false);
	}
	
	public void refreshConnectionClick(ActionEvent event) {
		loadClients();
	}
	
	public void closeConnectionPaneClick(ActionEvent event) {
		connectionsPane.setVisible(false);
		showConnectionsBtn.setVisible(true);
	}

}
