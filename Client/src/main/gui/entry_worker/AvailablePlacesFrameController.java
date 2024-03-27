package main.gui.entry_worker;
import entities.AvailablePlace;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AvailablePlacesFrameController {
    
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
 	
}
