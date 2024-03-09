package main.gui.dep_manager;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DepManagerMainFrameController extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = new Pane();
		try {//trying to load the first gui (startUpGui)
			root = FXMLLoader.load(getClass().getResource("DepManagerMainFrame.fxml"));
		}catch(Exception e) {
			System.out.println("[DepManagerMainFrameController] - Error loading DepManagerMainFrame.fxml");
			e.printStackTrace();
			System.exit(1);
		}
		//setting the root to the loaded fxml file and showing the gui
		Scene scene = new Scene(root);
		primaryStage.setTitle("GoNature Client");
		primaryStage.setScene(scene);
		
		primaryStage.show();			
	}
	public void viewReports(ActionEvent event) {
		
	}
	public void generateReport(ActionEvent event) {
		
	}
	public void confirmChange(ActionEvent event) {
		
	}

}
