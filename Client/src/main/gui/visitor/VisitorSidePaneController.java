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

/**
 * This class represents the controller for the side pane of the visitor interface.
 * It handles the visitor actions that he can do in the system.
 */
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
	
	
	
	 /**
     * Sets up initial state of UI elements based on visitor connection status.
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
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
	
	/**
     * Handles the action event for making a reservation  
     * after clicking on Make Reservation Button.
     * @param e The action event.
     */
	public void makeReservation(ActionEvent e) {
		MakeReservationFrameController.o=null;
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/visitor/MakeReservationFrame.fxml");
	}
	
	 /**
     * Handles the action event for showing reservations
     * after clicking on Show Reservation Button.
     * @param e The action event.
     */
	public void showReservation(ActionEvent e) {
		MakeReservationFrameController.o=null;
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/visitor/ShowReservationFrame.fxml");
	}
	
	
	/**
     * Handles the action event for showing the waiting list
     * after clicking on Waiting List Button
     * @param e The action event.
     */
	public void waitingList(ActionEvent e) {
		MakeReservationFrameController.o=null;
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/visitor/WaitingListFrame.fxml");
	}
	
	/**
     * Handles the mouse click event for navigating to the home page
     * after clicking on the Black House icon.
     * @param event The mouse event.
     */
	@FXML
	public void homePage(MouseEvent event) {
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/visitor/HomePage.fxml");
	}
}
