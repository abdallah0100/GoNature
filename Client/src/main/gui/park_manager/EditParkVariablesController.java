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
        updateEditType("Estimated Time");
    }

    // Method called when EditVisitor button is clicked
    @FXML
    public void editVisitor(ActionEvent event) {
        updateEditType("Visitor");
    }

    // Method called when editGap button is clicked
    @FXML
    public void editGap(ActionEvent event) {
        updateEditType("Gap");
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
                saveToDatabase(currentEditType, newVal);
                System.out.println("Data saved to database successfully.");
            }
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
        }
    }

    // Method to update the edit type Text
    private void updateEditType(String editType) {
        editTypeText.setText("Edit Type: " + editType);
        newValue.setText(""); // Clear new value field
    }

    // Method to save values to the database
    private void saveToDatabase(int editType, String newValue) {
        // Your code to save values to the database goes here
        System.out.println("Saving to database:");
        System.out.println("Edit Type: " + editType);
        System.out.println("New Value: " + newValue);
        // Add database saving logic here
    }
}