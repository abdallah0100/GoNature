package main.gui.entry_worker;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BillCakFrameController   extends Application {
	@FXML
	private TextField visitorid;
	
	@FXML
	private Button viewbill;
	

	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = new Pane();
		try {//trying to load the first gui (startUpGui)
			root = FXMLLoader.load(getClass().getResource("BillCakFrame.fxml"));
		}catch(Exception e) {
			System.out.println("[BillCakFrameController] - Error loading BillCakFrame.fxml");
			e.printStackTrace();
			System.exit(1);
		}
		//setting the root to the loaded fxml file and showing the gui
		Scene scene = new Scene(root);
		primaryStage.setTitle("GoNature Client startup - demo");
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
