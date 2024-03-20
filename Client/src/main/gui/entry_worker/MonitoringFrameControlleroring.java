package main.gui.entry_worker;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.ClientController;
import main.controllers.UserRequestController;
import utilities.SceneController;
 
public class MonitoringFrameControlleroring extends Application{
	
	@FXML
	private TextField visitorIdTxt;
	@FXML
	private Label msgLabel;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature - Entry Worker", primaryStage,
							"/main/gui/entry_worker/MonitoringFrame.fxml");
		
	}
	public void exit(ActionEvent event) {
		if(validInput()) {
			UserRequestController.exit(visitorIdTxt.getText());
			if(ClientController.monitoring){
				displayMSG("exit and delete");
				ClientController.monitoring=false;
			}
			else {
				displayMSG("reservation not found 1");
			}
		}

	}
	public void enter(ActionEvent event) {
		if(validInput()) {
			UserRequestController.enter(visitorIdTxt.getText());
			if(ClientController.monitoring)
			{
				displayMSG("enterd");
				ClientController.monitoring=false;
			}
			else {
				displayMSG("reservation not found ");
			}
		}
	}
	
	public boolean validInput() {
		if (visitorIdTxt.getText().length() <= 0){
			displayMSG("Please enter the  Bill Id");
			return false;
		}
		try {
			Integer.parseInt(visitorIdTxt.getText());
			displayMSG("reservationId has to be Number");
		}catch(Exception ex) {
			displayMSG("Invalid id was inputted");
			return false;
		}
		return true;
	}
	
	public void displayMSG(String txt) {
		msgLabel.setText(txt);
		msgLabel.setVisible(true);
	}
}
