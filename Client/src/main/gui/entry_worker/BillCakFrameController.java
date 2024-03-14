package main.gui.entry_worker;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.ClientController;
import main.controllers.UserRequestController;
import main.gui.MainFrameController;
import utilities.SceneController;

public class BillCakFrameController   extends Application {
	@FXML
	private TextField visitorid;
	
	@FXML
	private Button viewbill;
	
	@FXML
	private Label msgLabel;
	
	@FXML
	private Label billLabel;

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
		sceneController.changeScene("GoNature - Entry Worker", primaryStage,
									    "/main/gui/entry_worker/BillCakFrame.fxml");
	}
	
	public void showBill(ActionEvent e){
		if (visitorid.getText().length() <= 0){
			displayMSG("Please enter the  Bill Id");
			return;
		}
		try {
			Integer.parseInt(visitorid.getText());
		}catch(Exception ex) {
			displayMSG("Invalid id was inputted");
			return;
		}
		UserRequestController.sendShowBill(visitorid.getText());
		msgLabel.setVisible(false);
		billLabel.setVisible(false);
		if (ClientController.connectedUser.getRequestedBill()!= null) {
			billLabel.setVisible(true);
			billLabel.setText(ClientController.connectedUser.getRequestedBill());
			}
			 else {
				System.out.println("[BillCakFrameController] - did no bill");
				displayMSG("reservation Not Found");
		}
		
	}
	
	public void displayMSG(String txt) {
		msgLabel.setText(txt);
		msgLabel.setVisible(true);
	}
	
	
}
