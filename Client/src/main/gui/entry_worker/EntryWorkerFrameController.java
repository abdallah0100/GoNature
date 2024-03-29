package main.gui.entry_worker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.ClientUI;
import utilities.SceneController;

/**
 * This class serves as the controller for the EntryWorkerFrame.fxml file.
 * It manages the actions and events related to the Entry Worker's interface.
*/

public class EntryWorkerFrameController {
	
	 @FXML
	 private Button createReservations;
	 
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
}