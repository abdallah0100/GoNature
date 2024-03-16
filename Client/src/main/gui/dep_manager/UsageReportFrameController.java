package main.gui.dep_manager;

import java.net.URL;
import java.util.ArrayList;
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
	

	private static ArrayList<String[]> arrayList = new ArrayList<String[]>();



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colPark.setCellValueFactory(new PropertyValueFactory<>("Park"));
		colMonth.setCellValueFactory(new PropertyValueFactory<>("Month"));
		colYear.setCellValueFactory(new PropertyValueFactory<>("Year"));
		colAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
		colMadeBy.setCellValueFactory(new PropertyValueFactory<>("MadeBy"));
		
		
		UsageReportRequestController.sendShowUsageReport(arrayList);
		
		 // Loop over the ArrayList and add its data to the ObservableList
        for (String[] array : arrayList) {
            	UsageReport report = new UsageReport(array[0], array[1], array[2], array[3], array[4]);
                list.add(report);
        }
		tableView.setItems(list);
}


	public static void setList(ArrayList<String[]> list) {
		arrayList = list;
	}

	
	
	
	
	


	
}

