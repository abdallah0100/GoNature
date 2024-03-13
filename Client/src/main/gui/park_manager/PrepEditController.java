package main.gui.park_manager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utilities.SceneController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent; // Import the ActionEvent class
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
public class PrepEditController extends Application {

	@FXML
	private Button preparereport;
	
	@FXML
	private Button editparkvarible;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	/**
    * @param primaryStage the primary stage for the application
	* @throws Exception if an error occurs during initialization
	*/
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature - Park Manager", primaryStage,
									"/main/gui/park_manager/PrepEdit.fxml");
	}
		
		
	public void preparereport() 
	{	
		
	}
	
	public  void editparkvarible(ActionEvent e) throws Exception {
		{
			System.out.println("entered the code ");
			try {
		        // Load the FXML file for EditParkVariables interface
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/gui/park_manager/EditParkVariables.fxml"));
		        Parent root = loader.load();
		        System.out.println("FXML loaded successfully!"); // Check if FXML is loaded successfully

		        // Create a scene and set it to the stage
		        Stage editStage = new Stage();
		        Scene scene = new Scene(root);
		        editStage.setScene(scene);

		        // Show the stage
		        editStage.show();
	        } catch (Exception ex) {
	        	 System.err.println("Error loading FXML: " + ex.getMessage());
	            ex.printStackTrace(); // Handle the exception appropriately
	        }
		
	}
	}
}
