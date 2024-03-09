package main.gui.visitor;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WaitingListFrameController extends Application	implements Initializable{
	
	public static void main(String args[]){
		launch(args);
	}
	
	@FXML
	private ListView<String> userWaitingList;
	@FXML
	private Button cancelBtn;
	@FXML
	private Button enterBtn;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = new Pane();
		try {//trying to load the first gui (startUpGui)
			root = FXMLLoader.load(getClass().getResource("WaitingListFrame.fxml"));
		}catch(Exception e) {
			System.out.println("[WaitingListFrameController] - Error loading WaitingListFrame.fxml");
			e.printStackTrace();
			System.exit(1);
		}
		//setting the root to the loaded fxml file and showing the gui
		Scene scene = new Scene(root);
		primaryStage.setTitle("GoNature Client");
		primaryStage.setScene(scene);
		
		primaryStage.show();	
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[] listElements = { "waiting list item 1", "waiting list item 2", "waiting list item 3" };
		ObservableList<String> items =FXCollections.observableArrayList(listElements);
		userWaitingList.setItems(items);
		
	}
	
	/*
	 * a function to cancel entering the waiting list
	 * */
	public void cancelEnteringWaitingList(ActionEvent event) {
		
	}
	
	
	public void enterWaitingList(ActionEvent event) {
		userWaitingList.setVisible(true);
		cancelBtn.setVisible(false);
		enterBtn.setVisible(false);
	}
}
