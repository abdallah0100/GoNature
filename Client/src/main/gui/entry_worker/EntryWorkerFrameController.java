package main.gui.entry_worker;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This class serves as the controller for the EntryWorkerFrame.fxml file.
 * It manages the actions and events related to the Entry Worker's interface.
*/

public class EntryWorkerFrameController extends Application{
	
	 @FXML
	 private Button checkAvailablePlacesBtn;
	 
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
		Parent root = new Pane();
		
		try {
			root = FXMLLoader.load(getClass().getResource("EntryWorkerFrame.fxml"));
		}
		catch(Exception e) {
			System.out.println("[EntryWorkerFrameController] -  loading  EntryWorkerFrame.fxml");
			e.printStackTrace();
			System.exit(1);
		}
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("GoNature Client");
		primaryStage.show();
	}
	
	
	
	/***
	 * This method responsible for hiding EntryWorker window
	 * after clicking on checkAvailablePlaces button and
	 * new window is opened for the Available Places that the 
	 * EntryWorker can check.
	 * @param event
	 */
	public void checkAvailablePlaces(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide(); //hide EntryWorker window
		AvailablePlacesFrameController availablePlaceFrame = new AvailablePlacesFrameController();
		try {
			availablePlaceFrame.start(new Stage());;
		}
		catch (Exception ex) {
		System.out.println("[availablePlaceFrameController] - Error starting availablePlaceFrame");
		ex.printStackTrace();
		}
	}
	
	
	/***
	 * This method responsible for hiding EntryWorker window
	 * after clicking on View Bill button and
	 * new window is opened to check the Bill of the visitor 
	 * that the EntryWorker can check.
	 * @param event
	 */
	public void viewBill(ActionEvent event) {
		
		((Node)event.getSource()).getScene().getWindow().hide(); //hide EntryWorker window
		BillCakFrameController BillCakFrame = new BillCakFrameController();
		try {
			BillCakFrame.start(new Stage());
		}
		catch (Exception ex) {
		System.out.println("[BillCakFrameController] - Error starting BillCakFrame");
		ex.printStackTrace();
		}
	}

}
