package main.gui;


import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ValidationFrameController extends Application implements Initializable {
	@FXML
	private TextField idTxt;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = new Pane();
		try {//trying to load the first gui (startUpGui)
			root = FXMLLoader.load(getClass().getResource("ValidationFrame.fxml"));
		}catch(Exception e) {
			System.out.println("[ValidationFrameController] - Error loading ValidationFrame.fxml");
			e.printStackTrace();
			System.exit(1);
		}
		//setting the root to the loaded fxml file and showing the gui
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub	
	}
	//function to Enter the user 
	public void Enter(ActionEvent e) {
		//TODO
		System.out.println("Enter");
	}
	
	public void Exit(ActionEvent e) {
		//TODO
		System.out.println("exit");
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
