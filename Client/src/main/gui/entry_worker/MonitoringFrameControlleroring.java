package main.gui.entry_worker;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.ClientController;
import main.ClientUI;
import main.controllers.UserRequestController;
import utilities.SceneController;
 
public class MonitoringFrameControlleroring extends Application{
	
	@FXML
	private TextField visitorIdTxt;
	@FXML
	private Label msgLabel;
	public static String id;
	public static double price;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature - Entry Worker", primaryStage,
							"/main/gui/entry_worker/MonitoringFrame.fxml");
		
	}
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
	
	//enter id reservation and update and the amount visitor with order if 
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
	
	public void displayMSG(String txt) {
		msgLabel.setText(txt);
		msgLabel.setVisible(true);
	}
	
	//put the park and the reservation id 
	public String[] fill()
	{
		String[] s = new String[2];
		s[0] = ClientController.connectedUser.getParkName();
		s[1] = visitorIdTxt.getText();
		id=s[1];
		return s;
	}
}
