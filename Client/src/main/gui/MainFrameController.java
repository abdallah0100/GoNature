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
import javafx.stage.StageStyle;
import main.ClientController;
import main.ClientUI;
import main.controllers.UserRequestController;
import main.gui.visitor.MakeReservationFrameController;
import utilities.SceneController;

/**
* The MainFrameController class controls the main user interface of the GoNature application.
* It manages user logout, closing the application, and initializes the interface based on
* the role of the connected user or visitor.
*/
public class MainFrameController extends Application implements Initializable{
	
	@FXML
	private Pane contentPane;
	@FXML
	private Pane leftNavPane;
	@FXML
	private Pane headerPane;
	@FXML
	private ImageView closeIcon;
	
	 
	/**
    * Initializes and launches the JavaFX application.
    * @param primaryStage The primary stage of the JavaFX application.
    * @throws Exception If an error occurs during initialization.
    */
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController.stage = primaryStage;
		primaryStage.initStyle(StageStyle.UNDECORATED);
		SceneController sceneController = new SceneController();
		sceneController.changeScene("GoNature", primaryStage,
							               "/main/gui/MainFrame.fxml");
	}
	
	
	/**
    * Handles the action event when the logout button is clicked.
    * Initiates the logout process by sending a logout request to the server.
    * Clears the connected user or visitor data and switches to the login option frame.
    * @param event The action event triggered by clicking the logout button.
    */
	@FXML
	public void logout(ActionEvent event) {
		String toLogout;
		if (ClientController.connectedUser != null)
			toLogout = ClientController.connectedUser.getUsername();
		else 
			toLogout = ClientController.connectedVisitor.getId();
		UserRequestController.sendLogoutRequest(toLogout);
		ClientController.connectedVisitor = null;
		ClientController.connectedUser=null;
		UserRequestController.LogedIn = false;
		MakeReservationFrameController.o = null;
		LoginOptionController landingFrame = new LoginOptionController();
		SceneController.switchFrame("GoNature", event, landingFrame);
	}
	
	/**
    * Closes the application when the close icon is clicked.
    * @param event The mouse event triggered by clicking the close icon.
    */
	@FXML
	public void closeApp(MouseEvent event) {
		ClientController.getController().getClient().quit();
		System.exit(0);
	}

	
	/**
    * Initializes the controller after its root element has been completely processed.
    * It sets up the UI components based on the connected user role or if its a visitor.
    * @param location The location used to resolve relative paths for the root object.
    * @param resources The resources used to localize the root object.
    */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ClientUI.contentPane = contentPane;
		ClientUI.leftNavPane = leftNavPane;
		ClientUI.headerPane = headerPane;
		
		SceneController.headerPane = headerPane;
		SceneController.setUpHeaderDrag();
		
		SceneController scene = new SceneController();
		if (ClientController.connectedVisitor != null) {
			scene.setPane(leftNavPane, "/main/gui/visitor/VisitorSidePane.fxml");
			if(!(ClientController.connectedVisitor.isFoundInDB()))
			{
				scene.setPane(ClientUI.contentPane, "/main/gui/visitor/MakeReservationFrame.fxml");
			}else {
				scene.setPane(ClientUI.contentPane, "/main/gui/visitor/HomePage.fxml");//mayar needs changing
			}
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
	            	scene.setPane(leftNavPane,"/main/gui/service_agent/ServiceAgentSidePane.fxml");
	            	scene.setPane(contentPane,"/main/gui/service_agent/RegisterInstructorFrame.fxml");		
	                break;
	            default:
	            	System.out.println("connectedUser.getRole wrong");
	                break;		
			 }		
		}
	}
}
