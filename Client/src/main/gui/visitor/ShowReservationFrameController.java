package main.gui.visitor;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ShowReservationFrameController extends Application implements Initializable{
	@SuppressWarnings("rawtypes")
	@FXML
	private TableColumn siteColumn;
	@SuppressWarnings("rawtypes")
	@FXML
	private TableColumn dateColumn;
	@SuppressWarnings("rawtypes")
	@FXML
	private TableColumn timeColumn;
	@SuppressWarnings("rawtypes")
	@FXML
	private TableColumn typeColumn;
	@SuppressWarnings("rawtypes")
	@FXML
	private TableColumn visitorNumberColumn;
	@FXML
	private Button updateBtn;

	public static void main(String[] args) {
		launch(args);
	}
	

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Parent root = new Pane();
		try {//trying to load the first gui (makeReservation.fxml)
			root = FXMLLoader.load(getClass().getResource("ShowReservationFrame.fxml"));
		}catch(Exception e) {
			System.out.println("[ShowReservationFrameController] - Error loading ShowReservationFrame.fxml");
			e.printStackTrace();
			System.exit(1);
		}
		//setting the root to the loaded fxml file and showing the gui
		Scene scene = new Scene(root);
		arg0.setTitle("GoNature Visitor Show Reservation");
		arg0.setScene(scene);
		arg0.show();
	}
	//function shows reservations 
	public void showReservation(ActionEvent e) throws Exception{
			
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
