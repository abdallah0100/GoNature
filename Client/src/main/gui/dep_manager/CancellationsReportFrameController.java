package main.gui.dep_manager;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entities.CancelledReservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.controllers.CancellationsReportRequestController;


public class CancellationsReportFrameController implements Initializable{
	
	@FXML
	private TableView<CancelledReservation> leftTableView;
	@FXML
	private TableColumn<CancelledReservation,String> colTypeLeft;
	@FXML
	private TableColumn<CancelledReservation,String> colDateLeft;
	@FXML
	private TableColumn<CancelledReservation,String> colParkLeft;
	private ObservableList<CancelledReservation> listLeft = FXCollections.observableArrayList();
	private static ArrayList<String[]> arrayListLeft = new ArrayList<String[]>();

	
	@FXML
	private TableView<CancelledReservation> rightTableView;
	@FXML
	private TableColumn<CancelledReservation,String> colTypeRight;
	@FXML
	private TableColumn<CancelledReservation,String> colDateRight;
	@FXML
	private TableColumn<CancelledReservation,String> colParkRight;
	private ObservableList<CancelledReservation> listRight = FXCollections.observableArrayList();
	private static ArrayList<String[]> arrayListRight = new ArrayList<String[]>();

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		colTypeLeft.setCellValueFactory(new PropertyValueFactory<>("Type"));
		colDateLeft.setCellValueFactory(new PropertyValueFactory<>("Date"));
		colParkLeft.setCellValueFactory(new PropertyValueFactory<>("Park"));
		
		colTypeRight.setCellValueFactory(new PropertyValueFactory<>("Type"));
		colDateRight.setCellValueFactory(new PropertyValueFactory<>("Date"));
		colParkRight.setCellValueFactory(new PropertyValueFactory<>("Park"));
		
		
	    CancellationsReportRequestController.sendShowCancellationsReport(arrayListLeft,"Yes");
	    
	    if(arrayListLeft != null) {
		    // Loop over the ArrayList and add its data to the ObservableList
	        for (String[] array : arrayListLeft) {
	        	CancelledReservation cancelledReservation1 = new CancelledReservation(array[0], array[1], array[2]);
	        	listLeft.add(cancelledReservation1);
	        }
	        leftTableView.setItems(listLeft);
        }
	    
	    CancellationsReportRequestController.sendShowCancellationsReport(arrayListRight,"No");
	    
	    if (arrayListRight != null) {
		    // Loop over the ArrayList and add its data to the ObservableList    
	        for (String[] array : arrayListRight) {
	        	CancelledReservation cancelledReservation2 = new CancelledReservation(array[0], array[1], array[2]);
	        	listRight.add(cancelledReservation2);
	        }
	        rightTableView.setItems(listRight);	
        }
	}

	public static void setArrayListLeft(ArrayList<String[]> arrayListLeft) {
		CancellationsReportFrameController.arrayListLeft = arrayListLeft;
	}

	public static void setArrayListRight(ArrayList<String[]> arrayListRight) {
		CancellationsReportFrameController.arrayListRight = arrayListRight;
	}
	
}