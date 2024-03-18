package main.gui.dep_manager;

import java.net.URL;
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
	private static CancelledReservation[] leftTableArray = null;

	
	@FXML
	private TableView<CancelledReservation> rightTableView;
	@FXML
	private TableColumn<CancelledReservation,String> colTypeRight;
	@FXML
	private TableColumn<CancelledReservation,String> colDateRight;
	@FXML
	private TableColumn<CancelledReservation,String> colParkRight;
	private ObservableList<CancelledReservation> listRight = FXCollections.observableArrayList();
	private static CancelledReservation[] rightTableArray = null;

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		colTypeLeft.setCellValueFactory(new PropertyValueFactory<>("Type"));
		colDateLeft.setCellValueFactory(new PropertyValueFactory<>("Date"));
		colParkLeft.setCellValueFactory(new PropertyValueFactory<>("Park"));
		
		colTypeRight.setCellValueFactory(new PropertyValueFactory<>("Type"));
		colDateRight.setCellValueFactory(new PropertyValueFactory<>("Date"));
		colParkRight.setCellValueFactory(new PropertyValueFactory<>("Park"));
		
		CancellationsReportRequestController.sendShowCancellationsReport("Yes");
		if(leftTableArray != null) {
			 // Loop over the ArrayList and add its data to the ObservableList
	        for (CancelledReservation element : leftTableArray) {
	        	 CancelledReservation cancelledReservation = new CancelledReservation(element.getType(),
	        			 element.getDate(),element.getPark());
	        	 listLeft.add(cancelledReservation);      	
	        }
	        leftTableView.setItems(listLeft);

	    }
		
		CancellationsReportRequestController.sendShowCancellationsReport("No");
		if(rightTableArray != null) {
			 // Loop over the ArrayList and add its data to the ObservableList
	        for (CancelledReservation element : rightTableArray) {
	        	CancelledReservation cancelledReservation = new CancelledReservation(element.getType(),
	        			 element.getDate(),element.getPark());
	        	listRight.add(cancelledReservation); 
	            	
	        }
	        rightTableView.setItems(listRight);
	    }
	}
	

	public static void setArrayListLeft(CancelledReservation[] arrayListLeft) {
		CancellationsReportFrameController.leftTableArray = arrayListLeft;
    }
	    
	public static void setArrayListRight(CancelledReservation[] arrayListRight) {
		CancellationsReportFrameController.rightTableArray= arrayListRight;
	}
	
}