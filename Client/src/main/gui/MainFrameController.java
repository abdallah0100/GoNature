package main.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ClientUI;
import utilities.SceneController;

public class MainFrameController extends Application implements Initializable{
	
	@FXML
	private Pane contentPane;
	@FXML
	private Pane leftNavBar;
	@FXML
	private ImageView closeIcon;
	

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature - Visitor/Instructor", primaryStage,
							               "/main/gui/MainFrame.fxml");
		
	}
	
	@FXML
	public void logout(ActionEvent event) {
		System.out.println("Logging out...");
	}
	
	@FXML
	public void closeApp(MouseEvent event) {
		System.out.println("Closing app..");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (contentPane != null && leftNavBar != null) {
			ClientUI.contentPane = contentPane;
			ClientUI.leftNavBar = leftNavBar;
		}else {
			System.out.println("[MainFrameController] - error setting up panes");
		}
		
	}

}
