package main.gui.entry_worker;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.ClientController;
import main.ClientUI;
import main.controllers.UserRequestController;
import utilities.SceneController;
 
/**
* Controller class for managing the Monitoring Frame,
* which allows entry workers to monitor visitors and
* handle entry/exit operations.
*/
public class MonitoringFrameControlleroring {
	
	@FXML
	private TextField visitorIdTxt;
	@FXML
	private Label msgLabel;
	public static String id;
	public static double price;
	
	
	/**
    * Handles the action event when the exit button is clicked.
    * Checks for valid input, then calls the exit method in the UserRequestController.
    * Displays appropriate messages based on the operation result.
    * @param event The ActionEvent triggered when the exit button is clicked.
    */
	public void exit(ActionEvent event) {
		if(validInput()) {
			UserRequestController.exit(fill());
			if(ClientController.monitoring){
				displayMSG("exit and delete");
				ClientController.monitoring=false;
			}
			else {
				displayMSG("reservation not found ");
			}
		}

	}
	
	
	 /**
     * Handles the action event when the enter button is clicked.
     * Checks for valid input, then calls the enter method in the UserRequestController.
     * If monitoring is successful, retrieves the bill amount and redirects to the Invoicing Frame.
     * Displays appropriate messages based on the operation result.
     * @param event The ActionEvent triggered when the enter button is clicked.
     */ 
	public void enter(ActionEvent event) {
		if(validInput()) 
		{
			UserRequestController.enter(fill());	
			if(ClientController.monitoring)
			{
				UserRequestController.sendShowBill(visitorIdTxt.getText());
				price=ClientController.showBill.returnPrice();
				displayMSG("enterd");
				ClientController.monitoring=false;
				SceneController scene = new SceneController();
				scene.setPane(ClientUI.contentPane, "/main/gui/entry_worker/InvoicingFrame.fxml");
				return;
			}
		}
		displayMSG("reservation not found ");
		return;	
	}

	
    /**
    * Checks whether the input in the visitor ID TextField is valid.
    * Displays a message if the input is invalid.
    * @return true if the input is valid, false otherwise.
    */
	public boolean validInput() {
		if (visitorIdTxt.getText().length() <= 0){
			displayMSG("Please enter the  Bill Id");
			return false;
		}
		try {
			Integer.parseInt(visitorIdTxt.getText());
			return true;
		//	displayMSG("reservationId has to be Number");
		}catch(Exception ex) {
			displayMSG("Invalid id was inputted");
			return false;
		}
	
	}
	
	/**
    * Displays a message on the message Label.
    * @param txt The message to be displayed.
    */
	public void displayMSG(String txt) {
		msgLabel.setText(txt);
		msgLabel.setVisible(true);
	}
	
	
	/**
    * Creates an array with park name and visitor ID to
    * fill in the UserRequestController methods.
    * @return An array containing park name and visitor ID.
    */
	public String[] fill()
	{
		String[] s = new String[2];
		s[0] = ClientController.connectedUser.getParkName();
		s[1] = visitorIdTxt.getText();
		id=s[1];
		return s;
	}
}