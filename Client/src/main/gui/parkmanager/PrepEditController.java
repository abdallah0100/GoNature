package main.gui.parkmanager;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class PrepEditController extends Application {

	@FXML
	private Button preparereport;
	
	@FXML
	private Button editparkvarible;
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = new Pane();
		try {//trying to load the first gui (startUpGui)
			root = FXMLLoader.load(getClass().getResource("PrepEdit.fxml"));
		}catch(Exception e) {
			System.out.println("[PrepEdit] - Error loading ClientStartUpFrame.fxml");
			e.printStackTrace();
			System.exit(1);
		}
		//setting the root to the loaded fxml file and showing the gui
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		///////amni
		primaryStage.show();
		
	}
	
	public void preparereport()
	{
		
	}
	
	public void editparkvarible()
	{
		
	}
}
