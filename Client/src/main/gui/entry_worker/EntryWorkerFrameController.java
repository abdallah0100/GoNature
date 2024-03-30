package main.gui.entry_worker;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.ClientController;
import main.ClientUI;
import utilities.SceneController;

/**
 * This class serves as the controller for the EntryWorkerFrame.fxml file.
 * It manages the actions and events related to the Entry Worker's interface.
*/

public class EntryWorkerFrameController implements Initializable{
	
	 @FXML
	 private Button createReservations;
	 
	 @FXML
	 private Label username;
	 @FXML
	 private Label park;
	 
	/**
	* This method is responsible for hiding the EntryWorker window
	* after clicking on the Create Reservations button and
	* opening a new window to enter visitor information.
    * @param event The action event triggered by clicking the button.
	*/
	public void createReservations(ActionEvent event) {
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/entry_worker/EnterVisitorsFrame.fxml");
	}
	
	
	/**
	 * This method is responsible for opening the Monitoring Frame
	 * window when the Monitoring button is clicked.
	 * @param event The action event triggered by clicking the button.
	 */
	public void monitoring(ActionEvent event) {
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/entry_worker/MonitoringFrame.fxml");
	}


	 /**
     * Sets the name label to display the first name of the logged-in entry worker.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		username.setText(ClientController.connectedUser.getFirstName());
		park.setText(ClientController.connectedUser.getParkName());
	}
}