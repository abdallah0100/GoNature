package main.gui.park_manager;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import main.ClientController;

public class EditParkVariablesController implements Initializable{
	 	
	@FXML
	private Button editEstimatedTimeBtn;
	@FXML
	private Button editVisitorBtn;
	@FXML
	private Button editGapBtn;
	@FXML
	private Pane rightPane;
	@FXML
	private Label editingVariable;
	@FXML
	private Label parkName;
	@FXML
	private Label msgLabel;
	@FXML
	private Label currentValue;
	@FXML
	private Button update;
	@FXML
	private Button cancel;
	
	@FXML
	private TextField newValue;
		    
	@FXML
	public void selectVariableToEdit(ActionEvent e ) {
	    Button clickedBtn = (Button)e.getSource();
	    editingVariable.setText(clickedBtn.getText());
	    if (parkName.getText().contains("Gap"))
	    	currentValue.setText(ClientController.connectedUser.getPark().getGap() + "");
	    else if (parkName.getText().contains("Estimated"))
	    	currentValue.setText(ClientController.connectedUser.getPark().getEstimatedTime() + "");
	    else 
	    	currentValue.setText(ClientController.connectedUser.getPark().getMaxCapacity() + "");
	    rightPane.setVisible(true);
	}
	@FXML
	public void cancel(ActionEvent e ) {
		newValue.setText("");
		rightPane.setVisible(false);
	}
	@FXML
	public void update(ActionEvent e ) {
	    
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		parkName.setText(ClientController.connectedUser.getPark().getParkName());
		
	}
		    

}
