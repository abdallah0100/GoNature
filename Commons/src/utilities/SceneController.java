package utilities;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
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
	
}
