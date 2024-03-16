//only missing the check with the database and insertion to database
//it checks the fields that are not empty 
//  14/3/2024
package main.gui.service_agent;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.controllers.UserRequestController;
import utilities.SceneController;


public class RegisterInstructorFrameController extends Application{
	public static int result;
	  	@FXML
	    private Button btnRegister;
	  	
	  	@FXML
	    private TextField instructorEmail;
	    @FXML
	    private TextField instructorID;
	    @FXML
	    private TextField instructorTelephone;
	    @FXML
	    private Label label;
	    
	    ///////////////////define variables////////////////////////////////////////////////////
     private String instructor_id;//retrieve data from id field
     private String instructor_email;//retrieve data from email field
     private String instructor_tel;//retrieve data from telephone field
     ///////////////////////////////////////////////////////////////////////////
	 
	 public static void main(String args[]) {
		 launch(args);
	 }
	
	
	/**
	* @param primaryStage the primary stage for the application
	* @throws Exception if an error occurs during initialization
    */
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature - Service Agent", primaryStage,
					    "/main/gui/service_agent/RegisterInstructorFrame.fxml");
		
	}
	
	public void register(ActionEvent e) {//we will check all the fields are not empty and not in database to add
		
       try {
	    	  
			if(!instructorID.getText().isEmpty()&&!instructorEmail.getText().isEmpty()
					&&!instructorTelephone.getText().isEmpty())
			{
				instructorID.setStyle("-fx-text-box-border: #008000; -fx-focus-color: #008000;");
				instructorEmail.setStyle("-fx-text-box-border: #008000; -fx-focus-color: #008000;");
				instructorTelephone.setStyle("-fx-text-box-border: #008000; -fx-focus-color: #008000;");
				instructor_id=instructorID.getText();
				instructor_email=instructorEmail.getText();
				instructor_tel=instructorTelephone.getText();
			  UserRequestController.insertInstructor(instructor_id,instructor_email,instructor_tel);
			  if (RegisterInstructorFrameController.result==1) {
					label.setText("Registered");
			  }
			  	else if(RegisterInstructorFrameController.result==0){
					label.setText("Allready Registered ");
				}
			}else {
				if(instructorID.getText().isEmpty())
					instructorID.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
				
				if(instructorEmail.getText().isEmpty())
					instructorEmail.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
				
				if(instructorTelephone.getText().isEmpty())
					instructorTelephone.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
				label.setText("Fill Empty Fields !");
			 }
				
		}catch(Exception ex){
			label.setText("error");
		 }	
	}

}
