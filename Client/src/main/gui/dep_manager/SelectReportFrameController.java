package main.gui.dep_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.ClientUI;
import utilities.SceneController;

public class SelectReportFrameController {
	
	
	@FXML
	private Button numOfVisitorsReportsBtn;
	
	@FXML
	private Button cancellationsReportsBtn;
	
	
	/**
	 * This method is responsible for opening new window after
	 * clicking on Usage Report button
	* @param event 
	*/
	@FXML
	public void showUsageWindow(ActionEvent event) {	
		SceneController sc = new SceneController();
		sc.setPane(ClientUI.contentPane, "/main/gui/dep_manager/UsageReportFrame.fxml");
	}

	/**
	 * This method is responsible for opening new window after
	 * clicking on Usage Report button
	* @param event 
	*/
	@FXML
	public void showNumOfVisitorsWindow(ActionEvent event) {	
		SceneController sc = new SceneController();
		sc.setPane(ClientUI.contentPane, "/main/gui/dep_manager/SelectDateFrame.fxml");
	}

}
