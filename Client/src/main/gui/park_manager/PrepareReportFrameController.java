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

public class PrepareReportFrameController implements Initializable{
	
	@FXML
	private Text reportType;
	@FXML
	private DatePicker dateField;
	@FXML
	private Pane rightPane;
	@FXML
	private TextField nameField;
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
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
		nameField.setText("");
		dateField.setValue(null);
	}

}
