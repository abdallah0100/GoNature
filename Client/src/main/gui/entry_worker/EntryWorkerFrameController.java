package main.gui.entry_worker;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class EntryWorkerFrameController extends Application{
	
	 @FXML
	 private Button checkAvailablePlacesBtn;
	 
	 @FXML
	 private Button viewBillBtn;
	 
	
	 public static void main(String args[]) {
		 launch(args);
	 }
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = new Pane();
		
		try {
			root = FXMLLoader.load(getClass().getResource("EntryWorkerFrame.fxml"));
		}
		catch(Exception e) {
			System.out.println("[EntryWorkerFrameController] -  loading fxml");
			e.printStackTrace();
			System.exit(1);
		}
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("GoNature Client");
		primaryStage.show();
	}
	
	
	
	public void checkAvailablePlaces(ActionEvent e) {
		//TODO
	}
	
	public void viewBill(ActionEvent e) {
		//TODO
	}

}
