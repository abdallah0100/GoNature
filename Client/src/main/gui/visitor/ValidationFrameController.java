package main.gui.visitor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.ClientController;
import main.controllers.ParkRequestHandler;
import main.controllers.VisitorRequestController;
import main.gui.MainFrameController;
import utilities.SceneController;

/**
 * This class represents the controller for the validation frame of the visitor interface.
 * It allows visitors to enter their ID to validate.
 */
public class ValidationFrameController{
	@FXML
	private TextField idField;
	@FXML
	private Label msgLabel;
	@FXML
    private Button loginBtn;
	@FXML
	private Button  exitbtn;
	
	 public static boolean alreadyIn = false;
	
	 
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
     * Closes the application.
     * @param event The mouse event.
     */
	@FXML
	public void closeApp(MouseEvent event) {
		ClientController.getController().getClient().quit();
		System.exit(0);
	}

}
