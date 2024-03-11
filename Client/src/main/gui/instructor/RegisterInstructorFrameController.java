package main.gui.instructor;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class RegisterInstructorFrameController extends Application{
	
	 @FXML
	 private TextField nameField;
	 
	 @FXML
	 private TextField IDField;
	 @FXML
	 private TextField EmailField;

	 @FXML
	 private TextField TelephoneField;
	 
	 public static void main(String args[]) {
		 launch(args);
	 }
	
	 
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = new Pane();
		
		try {
			root = FXMLLoader.load(getClass().getResource("RegisterInstructorFrame.fxml"));
		}
		catch(Exception e) {
			System.out.println("[RegisterInstructorFrameController] -  loading RegisterInstructorFrame.fxml");
			e.printStackTrace();
			System.exit(1);
		}
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("GoNature Client");
		primaryStage.show();
	}
	
	
	
	public void register(ActionEvent e) {
		//TODO
	}

}
