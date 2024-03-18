package main.gui.dep_manager;

import java.net.URL;
import java.util.ResourceBundle;
import entities.UsageReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.controllers.UsageReportRequestController;

public class UsageReportFrameController  implements Initializable{

	@FXML
	private TableView<UsageReport> tableView;

	@FXML
	private TableColumn<UsageReport,String> colPark;

	@FXML
	private TableColumn<UsageReport,String> colMonth;

	@FXML
	private TableColumn<UsageReport,String> colYear;

	@FXML
	private TableColumn<UsageReport,String> colAmount;

	@FXML
	private TableColumn<UsageReport,String> colMadeBy;

	private ObservableList<UsageReport> list =
	FXCollections.observableArrayList();
	
	private static UsageReport[] usageReportArray = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colPark.setCellValueFactory(new PropertyValueFactory<>("Park"));
		colMonth.setCellValueFactory(new PropertyValueFactory<>("Month"));
		colYear.setCellValueFactory(new PropertyValueFactory<>("Year"));
		colAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
		colMadeBy.setCellValueFactory(new PropertyValueFactory<>("MadeBy"));

		UsageReportRequestController.sendShowUsageReport();

		if(usageReportArray != null) {
			 // Loop over the ArrayList and add its data to the ObservableList
	        for (UsageReport usageReport : usageReportArray) {
	            	UsageReport report = new UsageReport(usageReport.getMonth(), usageReport.getYear(),usageReport.getAmount(),
	            						 usageReport.getPark(), usageReport.getMadeBy());
	                list.add(report); 
	            	
	        }
			tableView.setItems(list);

	        }
		}
	

	public static void setList(UsageReport[] newArrayList) {
		usageReportArray =  newArrayList;
	}
}

