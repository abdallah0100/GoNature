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
	
	@FXML
	public void showReportPane(ActionEvent event) {
		resetValues();
		Button btn = (Button) event.getSource();
		reportType.setText(btn.getText());
		rightPane.setVisible(true);
	}
	
	@FXML
	public void cancelReportCreation(ActionEvent event) {
		resetValues();
		rightPane.setVisible(false);
	}
	
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
	
	private void resetValues() {
		cancelBtn.setLayoutX(135);
		
		reportDataTxt1.setVisible(false);
		dataValue1.setVisible(false);
		reportDataTxt2.setVisible(false);
		dataValue2.setVisible(false);
		generateBtn.setVisible(false);
		
		parkField.setText("");
		fetchBtn.setVisible(true);
		msgLabel.setVisible(false);
		updateBtn.setVisible(false);
	}
	
	public void displayError(String txt, int layoutX) {
		msgLabel.setText(txt);
		msgLabel.setLayoutX(layoutX);
		msgLabel.setTextFill(Color.valueOf("red"));
		msgLabel.setLayoutY(286);
		msgLabel.setVisible(true);
	}
	
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
