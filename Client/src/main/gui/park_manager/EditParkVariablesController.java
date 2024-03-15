package main.gui.park_manager;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.ClientController;
import requests.Message;
import requests.RequestType;


public class EditParkVariablesController {

    @FXML
    private Button EditEstimatedTime;

    @FXML
    private Button EditVisitor;

    @FXML
    private Button edit;

    @FXML
    private TextField newValue;

    @FXML
    private Button editGap;

    @FXML
    private Text editTypeText; // Renamed to avoid conflict with the editType variable name

    @FXML
    private Pane rightPane;

    // Variable to store the type of edit
    private int currentEditType ;

    public void initialize() {
        // Initialize currentEditType to an empty string
        currentEditType = 0;
    }

    // Method called when EditEstimatedTime button is clicked
    @FXML
    public void editEstimatedTime(ActionEvent event) {
    	currentEditType = 1;
    }

    // Method called when EditVisitor button is clicked
    @FXML
    public void editVisitor(ActionEvent event) {
    	currentEditType = 2;
    }

    // Method called when editGap button is clicked
    @FXML
    public void editGap(ActionEvent event) {
    	currentEditType = 3;
    }
///
    // Method called when edit button is clicked
    @FXML
    public void edit(ActionEvent event) throws Exception{
        // Save the new values to the database
        String newVal = newValue.getText();
        try {
            if (newVal.isEmpty()) {
                throw new IllegalArgumentException("the value is empty");
            } else {
            	sendEditsToServer(currentEditType, newVal);
                System.out.println("Data saved to database successfully.");
            }
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
        }
    }


    // Method to save values to the database
    private void sendEditsToServer(int editType, String newValue) {
    	String editName = null;
    	if (editType ==3)
    	{
    		editName="Gap";
    	}
    	else if(editType == 1)
    	{
    		editName = "Estimated Time";
    	}
    	else if(editType == 2)
    	{
    		editName = "Visitor";
    	}
        // Your code to save values to the database goes here
        System.out.println("Edit Type: " + editType);
        System.out.println("New Value: " + newValue);
        // Add database saving logic here
        int NewValue = Integer.parseInt(newValue);
        Object data = new Object[] {editName, NewValue};

     // Create a Message object with the request type and data
     Message message = new Message(RequestType.INSERT_EDITS, data);
	//clientController.accept(message);
    }
}