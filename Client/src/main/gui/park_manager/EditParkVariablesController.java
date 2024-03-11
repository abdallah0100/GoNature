package main.gui.park_manager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utilities.SceneController;

public class EditParkVariablesController  extends Application {
	 	
	@FXML
	private Button EditEstimatedTime;

	@FXML
	private Button EditVisitor;

	@FXML
	private Button edit;

	@FXML
	private TextField oldValue;

	@FXML
	private TextField newValue;

	@FXML
	private Button editGap;
	    
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
	"/main/gui/park_manager/EditParkVariables.fxml");
	}
		    
		    
	public void editEstimatedTime(ActionEvent e){
		
	}
		    
	public void editGap(ActionEvent e ) {   
		
	}
	
	public void edit(ActionEvent e ) {
	    	
	}
		    
	public void editVisitor(ActionEvent e) {
	    	
	}
}
