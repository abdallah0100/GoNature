package utilities;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Utility class for controlling navigation between scenes in a JavaFX application.
 * It provides methods to change the current scene, switch the frame of an application,
 * dynamically set content in a Pane, and enable dragging of the application window.
 */
public class SceneController {
	
	public static Pane headerPane; // Pane representing the draggable header area of the application window.
	public static Stage stage; // The primary stage of the application.
	private static double xOffset = 0; // X offset for window dragging.
    private static double yOffset = 0; // Y offset for window dragging.

	/**
	 * Changes the current scene of the specified stage to a new scene loaded from an FXML file.
	 * 
	 * @param title The title for the new scene's stage.
	 * @param stage The stage in which the scene will be set.
	 * @param fxmlPath The path to the FXML file to load the scene from.
	 */
	public void changeScene(String title, Stage stage, String fxmlPath) {
		Parent root = new Pane();
		try {
			root = FXMLLoader.load(getClass().getResource(fxmlPath));
		} catch (IOException e) {
			System.out.println("[SceneController] - Error loading fxml file: " + fxmlPath);
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setTitle(title);
		stage.setScene(scene);		
		stage.show();
	}
	
	
	/**
	 * Switches the application to a new frame by hiding the current window and starting a 
	 * new instance of the specified Application.
	 * 
	 * @param title The title for the new frame.
	 * @param e The event that triggered the frame switch.
	 * @param o The Application instance to start.
	 */
	public static void switchFrame(String title, Event e, Application o) {
		((Node)e.getSource()).getScene().getWindow().hide(); 
		try {
			o.start(new Stage());;
		}
		catch (Exception ex) {
		System.out.println("[SceneController] - Error loading fxml file: " + title);
		ex.printStackTrace();
		}
	}
	
	/**
	 * Sets the content of the specified pane by loading it from an FXML file.
	 * 
	 * @param pane The pane to set the content for.
	 * @param fxmlPath The path to the FXML file to load the content from.
	 */
	public void setPane(Pane pane, String fxmlPath) {
		try {
			Parent visitorSidePane = FXMLLoader.load(getClass().getResource(fxmlPath));
			pane.getChildren().removeAll();
			pane.getChildren().setAll(visitorSidePane);
		}catch(Exception ex) {
			System.out.println("[SceneController] - failed to update pane");
			ex.printStackTrace();
		}
	}
	
	/**
	 * Configures the headerPane for dragging, allowing the user to click and drag 
	 * the pane to move the application window.
	 * This method must be called after headerPane and stage are initialized.
	 */
	public static void setUpHeaderDrag() {
		// making the client draggable from the header
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
