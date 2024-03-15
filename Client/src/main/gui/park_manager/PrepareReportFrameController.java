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
	private Text dataValue;
	@FXML
	private Text reportDataTxt;	
	@FXML
	private Button generateBtn;
	@FXML
	private Button cancelBtn;
	@FXML
	private Button fetchBtn;
	@FXML
	private ComboBox<String> monthBox;
	@FXML
	private ComboBox<String> yearBox;
	@FXML
	private Label msgLabel;
	
	private static ArrayList<String> monthsList;
	private static ArrayList<String> yearsList;
	public static Report report_withData;
	
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
			msgLabel.setTextFill(Color.valueOf("green"));
			msgLabel.setVisible(true);
		}
	}
	
	@FXML
	public void fetchData(ActionEvent event) {
		if (validInput()) {
			UserRequestController.fetchReportData(parkField.getText(), Utils.getMonthNumberByName(monthBox.getValue())+"", yearBox.getValue());
			if (report_withData != null) {
				System.out.println(report_withData.getIndividuals() + ", " + report_withData.getGroups());
				reportDataTxt.setText(reportType.getText());
				reportDataTxt.setVisible(true);
				dataValue.setVisible(true);
				
				cancelBtn.setLayoutX(216);
				generateBtn.setVisible(true);
				fetchBtn.setVisible(false);
				msgLabel.setVisible(false);
			}else {
				displayError("Error fetching data");
			}
		}else {
			if (!ClientController.connectedUser.getParkWork().equals(parkField.getText()))
				displayError("You can only create a report for the park you work at.");
			else
				displayError("Fill all fields to continue");
		}
	}
	
	private void resetValues() {
		cancelBtn.setLayoutX(135);
		
		reportDataTxt.setVisible(false);
		dataValue.setVisible(false);
		generateBtn.setVisible(false);
		
		parkField.setText("");
		fetchBtn.setVisible(true);
		msgLabel.setVisible(false);
	}
	
	public void displayError(String txt) {
		msgLabel.setText(txt);
		msgLabel.setVisible(true);
		msgLabel.setTextFill(Color.valueOf("red"));
	}
	
	public boolean validInput() {
		if (parkField.getText() == null || parkField.getText().length() == 0)
			return false;
		if (!ClientController.connectedUser.getParkWork().equals(parkField.getText())) 
			return false;
		if (monthBox.getValue() == null || yearBox.getValue() == null)
			return false;
		return monthsList.contains(monthBox.getValue()) && yearsList.contains(yearBox.getValue());
	}

}
