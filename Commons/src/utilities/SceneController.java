package utilities;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneController {

	public void changeScene(String title, Stage stage, String fxmlPath) {
		Parent root = new Pane();
		try {
			root = FXMLLoader.load(getClass().getResource(fxmlPath));
		} catch (IOException e) {
			System.out.println("[SceneController] - Error loading fxml file: " + fxmlPath);
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setTitle(title);
		stage.setScene(scene);		
		stage.show();
	}
	
	
	public static void switchFrame(String title, ActionEvent e, Application o) {
		((Node)e.getSource()).getScene().getWindow().hide(); 
		try {
			o.start(new Stage());;
		}
		catch (Exception ex) {
		System.out.println("[SceneController] - Error loading fxml file: " + title);
		ex.printStackTrace();
		}
	}
	
	public void setPane(Pane pane, String fxmlPath) {
		try {
			Parent visitorSidePane = FXMLLoader.load(getClass().getResource(fxmlPath));
			pane.getChildren().removeAll();
			pane.getChildren().setAll(visitorSidePane);
		}catch(Exception ex) {
			System.out.println("[SceneController] - failed to update pane");
			return;
		}
	}
	
}
