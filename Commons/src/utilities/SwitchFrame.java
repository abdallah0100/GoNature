package utilities;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class SwitchFrame {
	
	public static void switchFrame(String title,ActionEvent e , Application o) {
		((Node)e.getSource()).getScene().getWindow().hide(); 
		try {
			o.start(new Stage());;
		}
		catch (Exception ex) {
		System.out.println("[SceneController] - Error loading fxml file: " + title);
		ex.printStackTrace();
		}
	}

}
