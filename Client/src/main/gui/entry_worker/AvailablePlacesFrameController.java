package main.gui.entry_worker;
import entities.AvailablePlace;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import utilities.SceneController;

public class AvailablePlacesFrameController  extends Application  {
    
    @FXML
    private TableView<AvailablePlace> table ;
	@FXML
    private TableColumn<AvailablePlace,String> time;   
    @FXML
    private TableColumn<AvailablePlace,String> date;
    @FXML
    private TableColumn<AvailablePlace,String> parkname;
    
    ObservableList<AvailablePlace> list =  FXCollections.observableArrayList(
    		new AvailablePlace("Park K","10:30","05,08,2024")
    		);

	public static void main(String[] args) {
		launch(args);
	}

  
    /**
     * @param primaryStage the primary stage for the application
 	* @throws Exception if an error occurs during initialization
     */
 	@Override
 	public void start(Stage primaryStage) throws Exception {
 		SceneController sceneController = new SceneController();
 		sceneController.changeScene("GoNature - Entry Worker", primaryStage,
 					    "/main/gui/entry_worker/AvailablePlacesFrame.fxml");
 	}
 	
}
