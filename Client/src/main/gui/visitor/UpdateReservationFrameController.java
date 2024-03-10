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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UpdateReservationFrameController extends Application implements Initializable{
	@FXML
	private ComboBox<String> orderType;
	@FXML
	private TextField numOfVisitorsField;
	@FXML
	private DatePicker dateField;
	@FXML
	private ComboBox<String> timeField;
	@FXML
	private ComboBox<String> parkNameField;
	@FXML
	private TextField phoneField;
	@FXML
	private TextField emailField;
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
			root = FXMLLoader.load(getClass().getResource("UpdateReservationFrame.fxml"));
		}catch(Exception e) {
			System.out.println("[UpdateReservationFrameController] - Error loading UpdateReservationFrame.fxml");
			e.printStackTrace();
			System.exit(1);
		}
		//setting the root to the loaded fxml file and showing the gui
		Scene scene = new Scene(root);
		arg0.setTitle("GoNature Visitor Update Reservation");
		arg0.setScene(scene);
		arg0.show();
	}
	
	//function updates reservation 
	public void updateReservation(ActionEvent e) throws Exception{
		
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	


}
