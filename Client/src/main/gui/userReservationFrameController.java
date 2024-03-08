package main.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.client_requests.RequestHandler;

public class userReservationFrameController extends Application {
	
	@FXML
	private Pane visitorPane;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = new Pane();
		try {//trying to load the first gui (startUpGui)
			root = FXMLLoader.load(getClass().getResource("userReservationFrame.fxml"));
		}catch(Exception e) {
			System.out.println("[userReservationFrameController] - Error loading userReservationFrame.fxml");
			e.printStackTrace();
			System.exit(1);
		}
		//setting the root to the loaded fxml file and showing the gui
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	//function to makeReservation
	public void makeReservation(ActionEvent e) {
		//TODO
	}
	//function to showReservation
	public void showReservation(ActionEvent e) {
		//TODO
	}
	//function to updateReservation
	public void updateReservation(ActionEvent e) {
		//TODO
	}
	//function to backToValidation
	public void backToValidation(ActionEvent e) {
		//TODO
	}
	
}
