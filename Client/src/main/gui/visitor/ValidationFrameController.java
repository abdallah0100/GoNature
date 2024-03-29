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
import main.controllers.ParkRequestHandler;
import main.controllers.VisitorRequestController;
import main.gui.LoginOptionController;
import main.gui.MainFrameController;
import utilities.SceneController;

/**
 * This class represents the controller for the validation frame of the visitor interface.
 * It allows visitors to enter their ID to validate.
 */
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
	
	 public static boolean alreadyIn = false;
	 
	 /**
	     * Starts the application by initializing the primary stage.
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
	
	
	 /**
     * Validates the entered visitor ID and initiates the login process.
     * @param e The action event.
     */
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
		
		if (alreadyIn) {
			displayError("This id is already logged in");
			return;
		}
		
		if (VisitorRequestController.finishedValidating) {
			ParkRequestHandler.requestAllParks();
			SceneController.switchFrame("GoNature", e, new MainFrameController());
		}else {
			System.out.println("[ValidationFrameController] - did not finished validating");
		}
	}
	
	
	/**
     * Displays an error message.
     * @param txt The error message to display.
     */
	public void displayError(String txt) {
		msgLabel.setText(txt);
		msgLabel.setVisible(true);
	}
	
	 /**
     * Launches the application.
     * @param args Command line arguments.
     */
	public static void main(String[] args) {
		launch(args);
	}
	
	 /**
     * return back to the login option frame after clicking on the arrow.
     * @param event The mouse event.
     */
	@FXML
	public void goBack(MouseEvent event) {
		SceneController.switchFrame("GoNature - Client", event, new LoginOptionController());
	}
	
	 /**
     * Closes the application.
     * @param event The mouse event.
     */
	@FXML
	public void closeApp(MouseEvent event) {
		ClientController.getController().getClient().quit();
		System.exit(0);
	}

	/**
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SceneController.headerPane = headerPane;
		SceneController.setUpHeaderDrag();
		
	}
}
