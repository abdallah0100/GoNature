package main.gui;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import main.ClientController;
import main.ClientUI;
import main.gui.entry_worker.AvailablePlacesFrameController;
import utilities.SceneController;

public class GoNatureMainFrameController extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNatureMain - demo", primaryStage,
							               "/main/gui/GoNatureMainFrame.fxml");
	}
	
	public void userLogin(ActionEvent e) {
		((Node)e.getSource()).getScene().getWindow().hide(); //hide EntryWorker window
		LogInFrameController LogInFrame = new LogInFrameController();
		try {
			LogInFrame.start(new Stage());;
		}
		catch (Exception ex) {
		System.out.println("[LogInFrameController] - Error starting LogInFrame");
		ex.printStackTrace();
		}
	}
	
	public void visitorLogin(ActionEvent e) {
		//ValidationFrame
		((Node)e.getSource()).getScene().getWindow().hide(); //hide EntryWorker window
		ValidationFrameController ValidationFrame = new ValidationFrameController();
		try {
			ValidationFrame.start(new Stage());;
		}
		catch (Exception ex) {
		System.out.println("[ValidationFrameController] - Error starting ValidationFrame");
		ex.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
