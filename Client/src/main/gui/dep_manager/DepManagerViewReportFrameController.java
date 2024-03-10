//shady built this class 10/3/2024 0:01
// view report frame controller class
package main.gui.dep_manager;

import javafx.scene.control.Button;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class DepManagerViewReportFrameController extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	
	 @FXML
	    private MenuButton btn_date;

	    @FXML
	    private MenuButton btn_type;

	    @FXML
	    private Button btn_view;

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = new Pane();
		try {//trying to load the first gui (startUpGui)
			root = FXMLLoader.load(getClass().getResource("DepManagerViewReportFrame.fxml"));
		}catch(Exception e) {
			System.out.println("[DepManagerViewReportFrameController] - Error loading DepManagerMainFrame.fxml");
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
