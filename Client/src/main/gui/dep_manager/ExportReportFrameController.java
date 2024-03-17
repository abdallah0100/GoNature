package main.gui.dep_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.ClientUI;
import utilities.SceneController;

public class ExportReportFrameController {

	
	@FXML
	private Button visitsReportsBtn;
	
	@FXML
	private Button cancellationsReportsBtn;
	
	
	
	
	/**
	 * This method is responsible for opening new window after
	 * clicking on visits report button
	* @param event 
	*/
	@FXML
	public void showvisitsReportWindow(ActionEvent event) {	
		SceneController sc = new SceneController();
		sc.setPane(ClientUI.contentPane, "/main/gui/dep_manager/VisitsReportFrame.fxml");
	}

	/**
	 * This method is responsible for opening new window after
	 * clicking on cancellations Report button
	* @param event 
	*/
	@FXML
	public void showcancellationsReportsWindow(ActionEvent event) {	
		SceneController sc = new SceneController();
		sc.setPane(ClientUI.contentPane, "/main/gui/dep_manager/CancellationsReportFrame.fxml");
	}
	
}
