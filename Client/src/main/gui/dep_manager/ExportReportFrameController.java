package main.gui.dep_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.ClientUI;
import utilities.SceneController;

/**
 * Controller class for managing the Export Report Frame.
 */
public class ExportReportFrameController {

	
	@FXML
	private Button visitsReportsBtn;
	
	@FXML
	private Button cancellationsReportsBtn;
	

	/**
	 * This method is responsible for opening Select Visits Details 
	 * Details window after clicking on visits report button
	* @param event clicking on button
	*/
	@FXML
	public void showvisitsReportWindow(ActionEvent event) {	
		SceneController sc = new SceneController();
		sc.setPane(ClientUI.contentPane, "/main/gui/dep_manager/SelectVisitsDetailsFrame.fxml");
	}

	/**
	 * This method is responsible for opening Cancellations
	 * Report window after clicking on cancellations Report button
	* @param event clicking on button
	*/
	@FXML
	public void showcancellationsReportsWindow(ActionEvent event) {	
		SceneController sc = new SceneController();
		sc.setPane(ClientUI.contentPane, "/main/gui/dep_manager/CancellationsReportFrame.fxml");
	}	
}