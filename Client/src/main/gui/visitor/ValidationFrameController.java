package main.gui.visitor;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.ClientController;
import main.controllers.VisitorRequestController;
import main.gui.LoginOptionController;
import main.gui.MainFrameController;
import utilities.SceneController;

public class ValidationFrameController extends Application implements Initializable	{
	@FXML
	private TextField idField;
	@FXML
	private Label msgLabel;
	 @FXML
	 private Button loginBtn;
	 @FXML
	 private Button  exitbtn;
	 @FXML
	private Pane headerPane;
	
	/**
	* @param primaryStage the primary stage for the application
	* @throws Exception if an error occurs during initialization
	*/
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController.stage = primaryStage;
		primaryStage.initStyle(StageStyle.UNDECORATED);
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature - Validation", primaryStage,
										"/main/gui/visitor/ValidationFrame.fxml");
	}
	
	//function to Enter the user 
	public void validate(ActionEvent e) {
		if (idField.getText().length() <= 0) {
			displayError("Please enter an Id to continue");
			return;
		}
		else if(idField.getText().length() < 9 ) {
			displayError("Please enter valid Id with 9 digits");
			return;
		}
		try {
			Integer.parseInt(idField.getText());
		}catch(Exception ex) {
			displayError("Invalid id was inputted");
			return;
		}
		VisitorRequestController.sendVisitorValidation(idField.getText());
		if (VisitorRequestController.finishedValidating) {
			SceneController.switchFrame("GoNature", e, new MainFrameController());
		}else {
			System.out.println("[ValidationFrameController] - did not finished validating");
		}
	}
	
	public void displayError(String txt) {
		msgLabel.setText(txt);
		msgLabel.setVisible(true);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@FXML
	public void goBack(MouseEvent event) {
		SceneController.switchFrame("GoNature - Client", event, new LoginOptionController());
	}
	@FXML
	public void closeApp(MouseEvent event) {
		ClientController.getController().getClient().quit();
		System.exit(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SceneController.headerPane = headerPane;
		SceneController.setUpHeaderDrag();
		
	}
}
