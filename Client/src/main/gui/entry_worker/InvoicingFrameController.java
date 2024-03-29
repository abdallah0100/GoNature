package main.gui.entry_worker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.ClientController;
import main.controllers.UserRequestController;


/**
* This class initializes the Invoicing Frame and displays the reservation ID and bill information.
*/
public class InvoicingFrameController implements Initializable {
	
	@FXML
	private Label reservationId;
	@FXML
	private Label msgLabel;

	
	/**
	* show the bill for the specific ID
    * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
    * @param resources The resources used to localize the root object, or null if the root object was not localized.
    */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		reservationId.setText(MonitoringFrameControlleroring.id);
		reservationId.setVisible(true);
		UserRequestController.sendShowBill(reservationId.getText());
		if (ClientController.showBill!=null) 
		{
			displayMSG(MonitoringFrameControlleroring.price + "");
		}
		else {//if null
				System.out.println("[BillCakFrameController] - did no bill");
				displayMSG("reservation Not Found");
			 }
		}

	/**
    * Displays a message on the GUI.
    * @param txt The text to be displayed.
    */
	public void displayMSG(String txt) {
		msgLabel.setText(txt);
		msgLabel.setVisible(true);
	}
}