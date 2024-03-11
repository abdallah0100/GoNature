package main.gui.entry_worker;
import entities.AvailablePlace;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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

    
    @Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = new Pane();
		try {//trying to load the first gui (startUpGui)
			root = FXMLLoader.load(getClass().getResource("AvailablePlacesFrame.fxml"));
		}catch(Exception e) {
			System.out.println("[AvailablePlacesFrameController] - Error loading AvailablePlacesFrame.fxml");
			e.printStackTrace();
			System.exit(1);
		}
		//setting the root to the loaded fxml file and showing the gui
		Scene scene = new Scene(root);
		primaryStage.setTitle("GoNature Client startup - demo");
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
	}
}