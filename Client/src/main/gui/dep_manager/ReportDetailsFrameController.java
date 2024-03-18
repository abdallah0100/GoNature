package main.gui.dep_manager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.controllers.NumOfVisitorsController;

public class ReportDetailsFrameController implements Initializable{
	
	@FXML
	private  TextField parkField;
	@FXML
	private  ComboBox<String> monthBox;
	@FXML 
	private  ComboBox<String> yearBox;
	@FXML
	private  Label errorMsgLabel;
	@FXML
	private  Label reservationTypeLabel;
	@FXML
	private  Label organizedGroupLabel;
	@FXML
	private  Label IndivisualsLabel;
	@FXML
	private  Label amountLabel;
	@FXML
	private  Label organizedAmountLabel;
	@FXML
	private  Label indivisualsAmountLabel;
	
	private static String organizedGroupAmount;
	private static String indivisualsAmount;
	
	
	private static ArrayList<String> monthsList;
	private static ArrayList<String> yearsList;
	
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
	public void showNumberOfVisitors(ActionEvent event) {	
		
		if(checkInput()) {
			if (!parkField.getText().equals("park1") && 
				    !parkField.getText().equals("park2") &&
				    !parkField.getText().equals("park3")) {
					changeVisibility(false);
				    errorMsgLabel.setVisible(true);
				    errorMsgLabel.setText("Enter Valid park name");
			}
			else {
				errorMsgLabel.setVisible(false);
				String[] requestedData = new String[3];
				requestedData[0] = parkField.getText();
				requestedData[1] = monthBox.getValue();
				requestedData[2] = yearBox.getValue();
				
				NumOfVisitorsController.sendShowNumOfVisitorsReport(requestedData);
				if (organizedGroupAmount != null && !organizedGroupAmount.isEmpty() &&
					    indivisualsAmount != null && !indivisualsAmount.isEmpty()) {
					    changeVisibility(true);
					    organizedAmountLabel.setText(organizedGroupAmount);
					    indivisualsAmountLabel.setText(indivisualsAmount);    
			  }else{
				changeVisibility(true);
				organizedAmountLabel.setText("Not Found");
				indivisualsAmountLabel.setText("Not Found");
			  }

			}	
		}
	   else {
			errorMsgLabel.setText("Fill All Fields");
			errorMsgLabel.setVisible(true);
		}	
	}
	
	public void changeVisibility(boolean bool) {
		reservationTypeLabel.setVisible(bool);;	
		organizedGroupLabel.setVisible(bool);
		IndivisualsLabel.setVisible(bool);
		amountLabel.setVisible(bool);
		organizedAmountLabel.setVisible(bool);
		indivisualsAmountLabel.setVisible(bool);
	}
	
	@FXML
	public void clearFieldsBtn(ActionEvent event) {	
		   parkField.clear();
		   monthBox.setValue(null);
		   yearBox.setValue(null);
		   
		   // Hide report details
		   changeVisibility(false);
		   errorMsgLabel.setVisible(false);
	}
	
	public boolean checkInput() {
		if (parkField.getText() == null || parkField.getText().length() == 0)
			return false;
		if (monthBox.getValue() == null || yearBox.getValue() == null)
			return false;
		return monthsList.contains(monthBox.getValue()) && yearsList.contains(yearBox.getValue());
	}
	
	public static void setData(String[] data) {
		ReportDetailsFrameController.organizedGroupAmount = data[0];
		ReportDetailsFrameController.indivisualsAmount = data[1];

	}
}
