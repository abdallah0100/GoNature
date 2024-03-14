package main.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.ClientController;
import main.ClientUI;
import main.controllers.UserRequestController;
import utilities.SceneController;

public class MainFrameController extends Application implements Initializable{
	
	@FXML
	private Pane contentPane;
	@FXML
	private Pane leftNavPane;
	@FXML
	private Pane headerPane;
	@FXML
	private ImageView closeIcon;
	
	
	//
	//Saving offsets and the stage to apply drag functionality on the header
	private static double xOffset = 0;
    private static double yOffset = 0;
    private static Stage stage;

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		primaryStage.initStyle(StageStyle.UNDECORATED);
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature - Visitor/Instructor", primaryStage,
							               "/main/gui/MainFrame.fxml");
	}
	
	@FXML
	public void logout(ActionEvent event) {
		ClientController.connectedVisitor = null;
		ClientController.connectedUser=null;
		UserRequestController.LogedIn = false;
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
		
		ClientUI.contentPane = contentPane;
		ClientUI.leftNavPane = leftNavPane;
		ClientUI.headerPane = headerPane;
		setUpHeaderDrag();
		SceneController scene = new SceneController();
		if (ClientController.connectedVisitor != null) {
			scene.setPane(leftNavPane, "/main/gui/VisitorSidePane.fxml");
		}
		else if(ClientController.connectedUser != null) {
			 switch (ClientController.connectedUser.getRole()) {
	            case "depManager":
	            	scene.setPane(leftNavPane, "/main/gui/dep_manager/DepManagerMainFrame.fxml");
	                break;
	            case "entryManager":     	
	            	scene.setPane(leftNavPane, "/main/gui/entry_worker/EntryWorkerFrame.fxml");	     
	            	break;
	            case "parkManager":
	            	scene.setPane(leftNavPane,"/main/gui/park_manager/parkManagerSidePane.fxml");	
	                break;     
	            case "serviceAgent":
	            	scene.setPane(contentPane,"/main/gui/service_agent/RegisterInstructorFrame.fxml");		
	                break;
	            default:
	            	System.out.println("connectedUser.getRole wrong");
	                break;		
			 }		
		}
	}
	
	public void setUpHeaderDrag() {
		// making the client dragable from the header
		headerPane.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() + xOffset);
                stage.setY(event.getScreenY() + yOffset);			
			}
			
		});
		
		headerPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = stage.getX() - event.getScreenX();
                yOffset = stage.getY() - event.getScreenY();
            }
        });
	}
}
