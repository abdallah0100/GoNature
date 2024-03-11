package main.gui;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import main.ClientController;
import main.ClientUI;
import main.gui.entry_worker.AvailablePlacesFrameController;
import utilities.SceneController;
import utilities.SwitchFrame;

public class GoNatureMainFrameController extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNatureMain - demo", primaryStage,
							               "/main/gui/GoNatureMainFrame.fxml");
	}
	
	public void userLogin(ActionEvent e) {
		String s="LogInFrameController";
		LogInFrameController LogInFrame = new LogInFrameController();
		SwitchFrame.switchFrame(s, e, LogInFrame);
	}
	
	public void visitorLogin(ActionEvent e) {
		String s="ValidationFrameController";
		ValidationFrameController ValidationFrame = new ValidationFrameController();
		SwitchFrame.switchFrame(s, e, ValidationFrame);
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
