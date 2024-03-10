//shady built this class 10/3/2024 10:54
// Change Request Table frame controller class
package main.gui.dep_manager;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DepManagerChangeRequistTableFrameController extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	@FXML
    private TableColumn<?, ?> table_view;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = new Pane();
		try {//trying to load the first gui (startUpGui)
			root = FXMLLoader.load(getClass().getResource("DepManagerChangeRequistTableFrame.fxml"));
		}catch(Exception e) {
			System.out.println("[DepManagerChangeRequistTableFrameController] - Error loading DepManagerMainFrame.fxml");
			e.printStackTrace();
			System.exit(1);
		}
		//setting the root to the loaded fxml file and showing the gui
		Scene scene = new Scene(root);
		primaryStage.setTitle("GoNature Client");
		primaryStage.setScene(scene);
		
		primaryStage.show();			
	}
}
