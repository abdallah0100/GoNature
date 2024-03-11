package utilities;

import java.io.File;
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
	
	public void listAllFiles(File fil) {
		for (File f : fil.listFiles())
			System.out.print(f.getName() + " ");
	}
	
	
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
