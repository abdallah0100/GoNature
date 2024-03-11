package main.gui.visitor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import utilities.SceneController;

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
	
	
	/**
    * @param primaryStage the primary stage for the application
	* @throws Exception if an error occurs during initialization
	*/
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature - Visitor/Instructor", primaryStage,
							"/main/gui/visitor/WaitingListFrame.fxml");
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
