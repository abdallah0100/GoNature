package main.gui.visitor;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import main.ClientController;
import main.ClientUI;
import utilities.SceneController;


public class VisitorSidePaneController implements Initializable{
	
	@FXML
	private  Label visitorLabel;
	@FXML
	private  Label visitorIdLabel;
	@FXML
	private Button makeReservation;
	@FXML
	private Button showReservation;
	@FXML
	private Button waitingList;
	String id;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (ClientController.connectedVisitor != null) {
			visitorIdLabel.setText(ClientController.connectedVisitor.getId());
			if(!(ClientController.connectedVisitor.isFoundInDB())) {
				showReservation.setDisable(true);
				//makeReservation.setDisable(true);
			}
		}
	
	}
	
	//function to makeReservation
	public void makeReservation(ActionEvent e) {
		MakeReservationFrameController.o=null;
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/visitor/MakeReservationFrame.fxml");
	}
	
	//function to showReservation
	public void showReservation(ActionEvent e) {
		MakeReservationFrameController.o=null;
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/visitor/ShowReservationFrame.fxml");
	}
	//function to show waiting list
	public void waitingList(ActionEvent e) {
		MakeReservationFrameController.o=null;
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/visitor/WaitingListFrame.fxml");
	}
	
	@FXML
	public void homePage(MouseEvent event) {
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/visitor/HomePage.fxml");
	}
}
