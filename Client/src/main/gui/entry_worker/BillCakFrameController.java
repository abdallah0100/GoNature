package main.gui.entry_worker;
import entities.Park;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.ClientController;
import main.controllers.UserRequestController;
import utilities.SceneController;

public class BillCakFrameController  extends Application {
	@FXML
	private TextField reservatiomId;
	
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
		if (reservatiomId.getText().length() <= 0){
			displayMSG("Please enter the  Bill Id");
			return;
		}
		try {
			Integer.parseInt(reservatiomId.getText());
			displayMSG("reservationId has to be Number");
		}catch(Exception ex) {
			displayMSG("Invalid id was inputted");
			return;
		}
		
		UserRequestController.sendShowBill(reservatiomId.getText());
		msgLabel.setVisible(false);
		billLabel.setVisible(false);
		if (ClientController.showBill!= null) {
			//1 מראש  ביקור אישי 
			if(ClientController.showBill.getType()=="Individual") {
				///משפחתי – מוזמן
				if(ClientController.showBill.getinvited()) {
					int numberOfVisitors = Integer.parseInt(ClientController.showBill.getNumberOfVisitor());
					int x =  (int) (numberOfVisitors* 0.85);
					displayMSG(String.valueOf(x));
					return;
				}
				else {//מזדמן
					int numberOfVisitors = Integer.parseInt(ClientController.showBill.getNumberOfVisitor());
					displayMSG(String.valueOf(numberOfVisitors));
					}
			}
			else {
				//ביקור קבוצתי 
				if(ClientController.showBill.getinvited()) {
					//ביקור קבוצתי בהזמנה
					int numberOfVisitors = Integer.parseInt(ClientController.showBill.getNumberOfVisitor());
					numberOfVisitors--;
					int x =  (int) (numberOfVisitors* 0.75);
					x=(int) (x*0.88);
					displayMSG(String.valueOf(x));
					return;
				}
				else {
					int numberOfVisitors = Integer.parseInt(ClientController.showBill.getNumberOfVisitor());
					int x =  (int) (numberOfVisitors* 0.90);
					displayMSG(String.valueOf(x));
					return;
				}
				
			}			
		}
			else {//if null
				System.out.println("[BillCakFrameController] - did no bill");
				displayMSG("reservation Not Found");
			 }
		}
	
	public void displayMSG(String txt) {
		msgLabel.setText(txt);
		msgLabel.setVisible(true);
	}
	
	
}
