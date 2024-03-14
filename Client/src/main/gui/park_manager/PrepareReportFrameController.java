package main.gui.park_manager;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

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
	private ComboBox<String> monthBox;
	@FXML
	private ComboBox<String> yearBox;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[] monthsArray = {"January", "February", "March", "April",
							"May", "June", "july", "August",
							"September", "October", "November", "December"};
		ObservableList<String> months = FXCollections.observableArrayList(monthsArray);		
		monthBox.setItems(months);
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String[] years = new String[3];
		years[0] = year + "";
		years[1] = (year -1) + "";
		years[2] = (year -2) + "";
		
		ObservableList<String> yearList = FXCollections.observableArrayList(years);
		yearBox.setItems(yearList);
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
	public void fetchData(ActionEvent event) {
		reportDataTxt.setText(reportType.getText());
		reportDataTxt.setVisible(true);
		dataValue.setVisible(true);
		
		cancelBtn.setLayoutX(216);
		generateBtn.setVisible(true);
		
	}
	
	private void resetValues() {
		cancelBtn.setLayoutX(135);
		
		reportDataTxt.setVisible(false);
		dataValue.setVisible(false);
		generateBtn.setVisible(false);
		
		parkField.setText("");
	}

}
