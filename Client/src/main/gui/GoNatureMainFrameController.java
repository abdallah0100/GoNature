package main.gui;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import main.gui.visitor.ValidationFrameController;
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
		String s="LogInFrameController";
		LogInFrameController LogInFrame = new LogInFrameController();
		SceneController.switchFrame(s, e, LogInFrame);
	}
	
	public void visitorLogin(ActionEvent e) {
		String s="ValidationFrameController";
		ValidationFrameController ValidationFrame = new ValidationFrameController();
		SceneController.switchFrame(s, e, ValidationFrame);
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
