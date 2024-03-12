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
import main.ClientController;
import utilities.SceneController;

public class MainFrameController extends Application implements Initializable{
	
	@FXML
	private Pane contentPane;
	@FXML
	private Pane leftNavPane;
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
		ClientController.getController().getClient().quit();
		LoginOptionController landingFrame = new LoginOptionController();
		SceneController.switchFrame("GoNature", event, landingFrame);
	}
	
	@FXML
	public void closeApp(MouseEvent event) {
		ClientController.getController().getClient().quit();
		System.exit(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SceneController scene = new SceneController();
		if (ClientController.connectedVisitor != null) {
			scene.setPane(leftNavPane, "/main/gui/VisitorSidePane.fxml");
		}
		
	}

}
