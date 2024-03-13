package main.gui.park_manager;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.ClientController;

public class ParkManagerSidePaneController implements Initializable{
	
	@FXML
	private Label name;
	
	@FXML
	public void showPrepareReportWindow(ActionEvent event) {	
		
	}
	
	@FXML
	public void showVariableEditWindow(ActionEvent event) {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		name.setText(ClientController.connectedUser.getFirstName());
		
	}
}
