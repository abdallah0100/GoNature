package main.gui.dep_manager;

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
 * Main side frame for department manager to choose 
 * the specific action.
 */
public class DepManagerMainFrameController implements Initializable{
	
	
	@FXML
	 private Button viewReportsBtn;
	@FXML
	private Label username;
	
	
	@FXML
	 private Button confirmChangeRequestBtn;
	
	
	@FXML
	 private Button generateReportBtn;
	
	
	/**
	 * This method is responsible for opening Selecting Report
	 * window after clicking on View Reports button
	* @param event clicking on View Reports button
	*/
	@FXML
	public void showviewReportsWindow(ActionEvent event) {	
		SceneController sc = new SceneController();
		sc.setPane(ClientUI.contentPane, "/main/gui/dep_manager/SelectReportFrame.fxml");
	}
	
	/**
	 * This method is responsible for opening new window after
	 * clicking on Confirm Change Request button
	* @param event clicking on confirm change button
	*/
	@FXML
	public void confirmChange(ActionEvent event) {
		SceneController sc = new SceneController();
		sc.setPane(ClientUI.contentPane, "/main/gui/dep_manager/DecideVarEditFrame.fxml");
	}
	
	/**
	 * This method is responsible for opening Export Report
	 * window after clicking on Generate Report button
	* @param event clicking on export button
	*/
	@FXML
	public void showExportReportWindow(ActionEvent event) {	
		SceneController sc = new SceneController();
		sc.setPane(ClientUI.contentPane, "/main/gui/dep_manager/ExportReportFrame.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		username.setText(ClientController.connectedUser.getFirstName());
		
	}

}