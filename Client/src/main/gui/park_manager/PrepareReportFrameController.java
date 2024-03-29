package main.gui.park_manager;

import java.net.URL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.ResourceBundle;

import entities.Report;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import main.ClientController;
import main.controllers.UserRequestController;
import utilities.Utils;
/**
 * The controller class for managing the preparation of reports in the GUI for park manager.
 * This class handles interactions and data retrieval for generating reports.
 */
public class PrepareReportFrameController implements Initializable{
	
	@FXML
	private Text reportType;
	@FXML
	private Pane rightPane;
	@FXML
	private TextField parkField;
	@FXML
	private Text dataValue1;
	@FXML
	private Text dataValue2;
	@FXML
	private Text reportDataTxt1;	
	@FXML
	private Text reportDataTxt2;	
	@FXML
	private Button generateBtn;
	@FXML
	private Button cancelBtn;
	@FXML
	private Button fetchBtn;
	@FXML
	private Button updateBtn;
	@FXML
	private Button numOfVisitorBtn;
	@FXML
	private ComboBox<String> monthBox;
	@FXML
	private ComboBox<String> yearBox;
	@FXML
	private Label msgLabel;
	
	private static ArrayList<String> monthsList;
	private static ArrayList<String> yearsList;
	public static Report report_withData;
	private boolean fetched = false;
	
	
	/**
     * Sets up month and year ComboBox with years (2024,2024,2022).
     * And month ComboBox with all the months.
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[] arr1 = {"January", "February", "March", "April",
							"May", "June", "july", "August",
							"September", "October", "November", "December"};
		ObservableList<String> months = FXCollections.observableArrayList(arr1);		
		monthBox.setItems(months);
		monthsList = new ArrayList<>(Arrays.asList(arr1));
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String[] arr2 = new String[3];
		arr2[0] = year + "";
		arr2[1] = (year -1) + "";
		arr2[2] = (year -2) + "";
		
		ObservableList<String> yearList = FXCollections.observableArrayList(arr2);
		yearBox.setItems(yearList);
		yearsList = new ArrayList<>(Arrays.asList(arr2));
	}
	
	
	/**
     * Shows the report pane when Number Of Visitors button is clicked.
     * @param event The ActionEvent that triggered this method.
     */
	@FXML
	public void showReportPane(ActionEvent event) {
		resetValues();
		Button btn = (Button) event.getSource();
		reportType.setText(btn.getText());
		rightPane.setVisible(true);
	}
	
	/**
     * Hides the report pane when clicking of cancel button.
     * @param event The ActionEvent that triggered this method.
     */
	@FXML
	public void cancelReportCreation(ActionEvent event) {
		resetValues();
		rightPane.setVisible(false);
	}
	
	
	
	/**
     * Generates the report based on the input provided when clicking of Generate Report Button.
     * @param event The ActionEvent that triggered this method.
     */
	@FXML
	public void generateReport(ActionEvent event) {
		if (validInput() && report_withData != null) {
			UserRequestController.createReport(report_withData);
			msgLabel.setText(report_withData.getCreationStatus());
			msgLabel.setTextFill(Color.valueOf("white"));
			msgLabel.setVisible(true);
			msgLabel.setLayoutX(70);
			msgLabel.setLayoutY(297);
			generateBtn.setVisible(false);
			fetched = true;
		}else {
			System.out.println("not valid");
		}
	}
	
	
	/**
     * Hide the generate report Button and the labels to start new report creation
     * @param event The Event that triggered this method.
     */
	@FXML
	public void newInputTrigger(Event event) {
		if (fetched) {
			fetched = false;
			cancelBtn.setLayoutX(135);
			
			reportDataTxt1.setVisible(false);
			dataValue1.setVisible(false);
			reportDataTxt2.setVisible(false);
			dataValue2.setVisible(false);
			generateBtn.setVisible(false);
			
			fetchBtn.setVisible(true);
			msgLabel.setVisible(false);
			updateBtn.setVisible(false);
		}
	}
	
	
	/**
     * Shows the individuals orders and the group orders number when clicking fetch data button.
     * @param event The ActionEvent that triggered this method.
     */
	@FXML
	public void fetchData(ActionEvent event) {
		if (validInput()) {
			int year = Calendar.getInstance().get(Calendar.YEAR);
			int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
			int selectedMonth = Utils.getMonthNumberByName(monthBox.getValue());
			
			if (selectedMonth > month && year == Integer.parseInt(yearBox.getValue())) {
				displayError("Can't create a report for a month that is yet to come", 19);
				return;
			}
			UserRequestController.fetchReportData(parkField.getText(), Utils.getMonthNumberByName(monthBox.getValue())+ "", yearBox.getValue(), reportType.getText());
			if (report_withData != null) {
				fetched = true;
				if (reportType.getText().equals(numOfVisitorBtn.getText()))
					handleNumOfVisitors();
				else
					handleNotFullPark();
			}else {
				displayError("Error fetching data", 85);
			}
		}else {
			if (!ClientController.connectedUser.getPark().getParkName().equals(parkField.getText()))
				displayError("You can only create a report for the park you work at.", 19);
			else
				displayError("Fill all fields to continue", 70);
		}
	}
	
	/**
	* This method updates the UI components to display the relevant data about the park's full capacity.
	* It sets the appropriate labels and values based on the report data fetched.
	* If the report already exists, it prompts the park manager to update it, otherwise, it allows generating a new report.
	*/
	public void handleNotFullPark() {
		reportDataTxt1.setText("Times park was full:");
		dataValue1.setText(report_withData.getAmountOfFullDays() + "");
		reportDataTxt1.setVisible(true);
		dataValue1.setVisible(true);
		fetchBtn.setVisible(false);
		if (report_withData.isReportExist()) {		
			updateBtn.setVisible(true);
			msgLabel.setText("Report already exists, would you like to update it ?");
			msgLabel.setLayoutX(26);
			msgLabel.setLayoutY(300);
			msgLabel.setTextFill(Color.valueOf("#c2b830"));
			msgLabel.setVisible(true);
		}else {
			generateBtn.setVisible(true);
			msgLabel.setVisible(false);
		}
	}
	
	
	/**
	* Handles the display for the  number of visitors.
	*/
	public void handleNumOfVisitors() {
		reportDataTxt1.setText("Individual Orders:");
		reportDataTxt2.setText("Group Orders:");
		dataValue1.setText(report_withData.getIndividuals() + "");
		dataValue2.setText(report_withData.getGroups() + "");
		
		reportDataTxt2.setVisible(true);
		dataValue2.setVisible(true);
		
		reportDataTxt1.setVisible(true);
		dataValue1.setVisible(true);
		fetchBtn.setVisible(false);
		if (report_withData.isReportExist()) {
			updateBtn.setVisible(true);
			msgLabel.setText("Report already exists, would you like to update it ?");
			msgLabel.setLayoutX(26);
			msgLabel.setLayoutY(300);
			msgLabel.setTextFill(Color.valueOf("#c2b830"));
			msgLabel.setVisible(true);
			
		}else {
			generateBtn.setVisible(true);
			msgLabel.setVisible(false);
		}
	}
	
	
	/**
	* Resets input fields and hides unnecessary components.
	*/
	private void resetValues() {
		cancelBtn.setLayoutX(135);
		
		reportDataTxt1.setVisible(false);
		dataValue1.setVisible(false);
		reportDataTxt2.setVisible(false);
		dataValue2.setVisible(false);
		generateBtn.setVisible(false);
		
		monthBox.getSelectionModel().clearSelection();
		yearBox.getSelectionModel().clearSelection();
		parkField.setText("");
		yearBox.getSelectionModel().clearSelection();
		monthBox.getSelectionModel().clearSelection();
		fetchBtn.setVisible(true);
		msgLabel.setVisible(false);
		updateBtn.setVisible(false);
	}
	
	
	/**
	* Displays an error message.
	* @param txt The text of the error message.
	* @param layoutX The x-coordinate for displaying the error message.
	*/
	public void displayError(String txt, int layoutX) {
		msgLabel.setText(txt);
		msgLabel.setLayoutX(layoutX);
		msgLabel.setTextFill(Color.valueOf("red"));
		msgLabel.setLayoutY(286);
		msgLabel.setVisible(true);
	}
	
	/**
	* Validates that the park manager input for report generation is valid.
	* @return True if input is valid, otherwise false.
	*/
	public boolean validInput() {
		if (parkField.getText() == null || parkField.getText().length() == 0)
			return false;
		if (!ClientController.connectedUser.getPark().getParkName().equals(parkField.getText())) 
			return false;
		if (monthBox.getValue() == null || yearBox.getValue() == null)
			return false;
		
		return monthsList.contains(monthBox.getValue()) && yearsList.contains(yearBox.getValue());
	}

}