package main.gui.dep_manager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import main.ClientController;
import main.controllers.NumOfVisitorsController;

/**
 * Controller class for managing the Report Details Frame.
 */
public class ReportDetailsFrameController implements Initializable {

    @FXML
    private ComboBox<String> parkBox;

    @FXML
    private ComboBox<String> monthBox;

    @FXML
    private ComboBox<String> yearBox;

    @FXML
    private Label errorMsgLabel;

    @FXML
    private Label reservationTypeLabel;

    @FXML
    private Label organizedGroupLabel;

    @FXML
    private Label IndivisualsLabel;

    @FXML
    private Label amountLabel;

    @FXML
    private Label organizedAmountLabel;

    @FXML
    private Label indivisualsAmountLabel;

    private static String organizedGroupAmount;
    private static String indivisualsAmount;

    private static ArrayList<String> monthsList;
    private static ArrayList<String> yearsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] arr1 = {"January", "February", "March", "April",
                "May", "June", "July", "August",
                "September", "October", "November", "December"};
        ObservableList<String> months = FXCollections.observableArrayList(arr1);
        monthBox.setItems(months);
        monthsList = new ArrayList<>(Arrays.asList(arr1));

        int year = Calendar.getInstance().get(Calendar.YEAR);
        String[] arr2 = new String[3];
        arr2[0] = year + "";
        arr2[1] = (year - 1) + "";
        arr2[2] = (year - 2) + "";

        ObservableList<String> yearList = FXCollections.observableArrayList(arr2);
        yearBox.setItems(yearList);
        yearsList = new ArrayList<>(Arrays.asList(arr2));

        // Fill all the parks in comboBox
        Iterator<String> iterator = ClientController.getParks().keySet().iterator();
        ArrayList<String> al = new ArrayList<String>();
        while (iterator.hasNext()) {
            al.add(iterator.next());
        }
        ObservableList<String> list = FXCollections.observableArrayList(al);
        parkBox.setItems(list);
    }

    /**
     * Handles the action when the department manager clicks
     * the button to show the number of visitors.
     * 
     * @param event clicking on show button
     */
    @FXML
    public void showNumberOfVisitors(ActionEvent event) {

        if (checkInput()) {
            errorMsgLabel.setVisible(false);
            String[] requestedData = new String[3];
            requestedData[0] = parkBox.getValue();
            requestedData[1] = monthBox.getValue();
            requestedData[2] = yearBox.getValue();

            NumOfVisitorsController.sendShowNumOfVisitorsReport(requestedData);
            if (organizedGroupAmount != null && !organizedGroupAmount.isEmpty() &&
                    indivisualsAmount != null && !indivisualsAmount.isEmpty()) {
                changeVisibility(true);
                organizedAmountLabel.setText(organizedGroupAmount);
                indivisualsAmountLabel.setText(indivisualsAmount);
            }
        } else {
            errorMsgLabel.setText("Fill All Fields");
            errorMsgLabel.setVisible(true);
        }
    }

    /**
     * Changes the visibility of labels.
     * 
     * @param bool true to make labels visible, false otherwise
     */
    public void changeVisibility(boolean bool) {
        reservationTypeLabel.setVisible(bool);
        organizedGroupLabel.setVisible(bool);
        IndivisualsLabel.setVisible(bool);
        amountLabel.setVisible(bool);
        organizedAmountLabel.setVisible(bool);
        indivisualsAmountLabel.setVisible(bool);
    }

    /**
     * Handles the action when the user clicks the button to clear fields.
     * 
     * @param event clicking on the clear button
     */
    @FXML
    public void clearFieldsBtn(ActionEvent event) {
        parkBox.setValue(null);
        monthBox.setValue(null);
        yearBox.setValue(null);

        // Hide report details
        changeVisibility(false);
        errorMsgLabel.setVisible(false);
    }

    /**
     * Checks if all necessary inputs are provided.
     * 
     * @return true if all necessary inputs are provided, false otherwise
     */
    public boolean checkInput() {
        if (monthBox.getValue() == null || yearBox.getValue() == null || parkBox.getValue() == null)
            return false;
        return monthsList.contains(monthBox.getValue()) && yearsList.contains(yearBox.getValue());
    }

    /**
     * Sets the data for organized group and individuals.
     * 
     * @param data an array containing organized group amount and individuals amount
     */
    public static void setData(String[] data) {
        organizedGroupAmount = data[0];
        indivisualsAmount = data[1];
    }
}
