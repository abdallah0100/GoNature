package main.gui;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.ClientController;
import main.gui.visitor.ValidationFrameController;
import utilities.SceneController;

public class LoginOptionController extends Application implements Initializable{
	
	@FXML
	private Pane headerPane;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.initStyle(StageStyle.UNDECORATED);
		SceneController.stage = primaryStage;
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature Client", primaryStage,
							               "/main/gui/LoginOptionFrame.fxml");
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
	
	@FXML
	public void closeApp(MouseEvent event) {
		ClientController.getController().getClient().quit();
		System.exit(0);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SceneController.headerPane = headerPane;
		SceneController.setUpHeaderDrag();
		
	}
	
}
