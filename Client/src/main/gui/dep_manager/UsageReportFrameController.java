package main.gui.dep_manager;

import java.net.URL;
import java.util.ResourceBundle;

import entities.UsageReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import main.controllers.UsageReportRequestController;

/**
 * Controller class for managing the Usage Report Frame (not full report).
 */
public class UsageReportFrameController implements Initializable {

    @FXML
    private TableView<UsageReport> tableView;

    @FXML
    private TableColumn<UsageReport,String> colPark;

    @FXML
    private TableColumn<UsageReport,String> colMonth;

    @FXML
    private TableColumn<UsageReport,String> colYear;

    @FXML
    private TableColumn<UsageReport,String> colMadeBy;
    @FXML
    private Pane statisticsPane;
    @FXML
    private Label percentage;
    @FXML
    private Label amountLabel;

    private ObservableList<UsageReport> list = FXCollections.observableArrayList();

    private static UsageReport[] usageReportArray = null;
    private UsageReport selectedReport;
    

    /**
     * Initializes the table with the details of not full times at a park.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colPark.setCellValueFactory(new PropertyValueFactory<>("Park"));
        colMonth.setCellValueFactory(new PropertyValueFactory<>("Month"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("Year"));
        colMadeBy.setCellValueFactory(new PropertyValueFactory<>("MadeBy"));

        UsageReportRequestController.sendShowUsageReport();

        if(usageReportArray != null) {
            for (UsageReport usageReport : usageReportArray) {
                UsageReport report = new UsageReport(usageReport.getMonth(), usageReport.getYear(),
                                                      usageReport.getAmount(), usageReport.getPark(),
                                                      usageReport.getMadeBy());
                list.add(report);
            }
            tableView.setItems(list);
        }
        handleTable();
    }
    
    /**
    * Handles selection in the table to display statistics.
    */
    private void handleTable() {
    	tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	        if (newSelection != null) {
	        	selectedReport = (UsageReport)newSelection;    
	        	if (selectedReport != null) {
	        		double amount = Double.parseDouble(selectedReport.getAmount());
	        		double percent = amount /(30*14);
	        		String percentFormatted = String.format("%.2f", percent);
	        		percentage.setText(percentFormatted + "% ");
	        		amountLabel.setText(amount + "");
	        		statisticsPane.setVisible(true);
	        	}
	        }    
	    });
    }

    /**
    * Sets the list of usage reports.
    * @param newArrayList the array of usage reports
    */
    public static void setList(UsageReport[] newArrayList) {
        usageReportArray =  newArrayList;
    }
}