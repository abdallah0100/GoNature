package main.gui.park_manager;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.ClientController;
import main.ClientUI;
import utilities.SceneController;

public class ParkManagerSidePaneController implements Initializable{
	
	@FXML
	private Label name;
	
	@FXML
	public void showPrepareReportWindow(ActionEvent event) {	
		SceneController sc = new SceneController();
		sc.setPane(ClientUI.contentPane, "/main/gui/park_manager/PrepareReportFrame.fxml");
	}
	
	@FXML
	public void showVariableEditWindow(ActionEvent event) {
		SceneController sc = new SceneController();
		sc.setPane(ClientUI.contentPane, "/main/gui/park_manager/EditParkVariables.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		name.setText(ClientController.connectedUser.getFirstName());
		
	}
}
