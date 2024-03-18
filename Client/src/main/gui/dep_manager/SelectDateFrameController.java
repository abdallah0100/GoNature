package main.gui.dep_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import main.ClientUI;
import utilities.SceneController;

public class SelectDateFrameController {
	
	@FXML
	private DatePicker dateField;
	
	@FXML
	public void showNumberOfVisitorsWindow(ActionEvent event) {	
		
		
		
		
		
		
		
		
		SceneController sc = new SceneController();
		sc.setPane(ClientUI.contentPane, "/main/gui/dep_manager/NumberOfVisitorsReportFrame.fxml");
	}
}
