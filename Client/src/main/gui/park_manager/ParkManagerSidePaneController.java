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

/**
 * Controller class for the side pane of the park manager interface.
 * This class handles the actions related to the side pane, such as showing the windows for preparing reports and editing park variables.
 * It uses a SceneController to manage the switching of panes within the main GUI.
 */
public class ParkManagerSidePaneController implements Initializable{
	
	@FXML
	private Label name;
	
	
	/**
     * Shows the window for preparing a report when the Prepare Report button is clicked.
     * Uses a SceneController to set the content pane to the PrepareReportFrame.
     * @param event The ActionEvent triggered by clicking the button.
     */
	@FXML
	public void showPrepareReportWindow(ActionEvent event) {	
		SceneController sc = new SceneController();
		sc.setPane(ClientUI.contentPane, "/main/gui/park_manager/PrepareReportFrame.fxml");
	}
	
	 /**
     * Shows the window for editing park variables when the edit Park Variables button is clicked.
     * Uses a SceneController to set the content pane to the EditParkVariables frame.
     * @param event The ActionEvent triggered by clicking the button.
     */
	@FXML
	public void showVariableEditWindow(ActionEvent event) {
		SceneController sc = new SceneController();
		sc.setPane(ClientUI.contentPane, "/main/gui/park_manager/EditParkVariables.fxml");
	}

	 /**
     * Sets the name label to display the first name of the logged-in park manager.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		name.setText(ClientController.connectedUser.getFirstName());
		
	}
}