package main.gui.entry_worker;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.ClientUI;
import utilities.SceneController;

/**
 * This class serves as the controller for the EntryWorkerFrame.fxml file.
 * It manages the actions and events related to the Entry Worker's interface.
*/

public class EntryWorkerFrameController extends Application{
	
	 @FXML
	 private Button createReservations;
	 
	 @FXML
	 private Button viewBillBtn;
	 
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
		sceneController.changeScene("GoNature - Entry Worker", primaryStage,
							"/main/gui/entry_worker/EntryWorkerFrame.fxml");
	}
	
	
	/***
	 * This method responsible for hiding EntryWorker window
	 * after clicking on View Bill button and
	 * new window is opened to check the Bill of the visitor 
	 * that the EntryWorker can check.
	 * @param event
	 */
	public void viewBill(ActionEvent event) {
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/entry_worker/BillCakFrame.fxml");
	}
	
	public void createReservations(ActionEvent event) {
		SceneController scene = new SceneController();
		scene.setPane(ClientUI.contentPane, "/main/gui/visitor/MakeReservationFrame.fxml");
	}

}
