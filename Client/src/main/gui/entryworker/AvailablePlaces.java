package main.gui.entryworker;
import java.net.URL;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Group;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.ClientController;
import main.ClientUI;
import main.client_requests.RequestHandler;
import main.commons.Order;
import main.commons.requests.RequestType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.geometry.Insets;

public class AvailablePlaces  extends Application  {
    
    @FXML
    private TableView<AvailablePlaceCurrent> table ;
	@FXML
    private TableColumn<AvailablePlaceCurrent,String> time;   
    @FXML
    private TableColumn<AvailablePlaceCurrent,String> date;
    @FXML
    private TableColumn<AvailablePlaceCurrent,String> parkname;
    
    ObservableList<AvailablePlaceCurrent> list =  FXCollections.observableArrayList(
    		new AvailablePlaceCurrent("Park K","10:30","05,08,2024")
    		);

	public static void main(String[] args) {
		launch(args);
	}

    
    @Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = new Pane();
		try {//trying to load the first gui (startUpGui)
			root = FXMLLoader.load(getClass().getResource("AvailablePlaces.fxml"));
		}catch(Exception e) {
			System.out.println("[ClientStartUpController] - Error loading ClientStartUpFrame.fxml");
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
