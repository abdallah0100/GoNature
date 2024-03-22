package main.gui.dep_manager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import entities.CancelledReservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.ClientController;
import main.ClientUI;
import main.controllers.CancellationsReportRequestController;
import utilities.SceneController;

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
    private Label errorMsg;
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

	@FXML
	private ComboBox<String> parkComboBox;
	@FXML
	private DatePicker datePicker;
	
	
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
		Iterator<String> iterator = ClientController.getParks().keySet().iterator();
        ArrayList<String> al = new ArrayList<>();
        while (iterator.hasNext()) {
            al.add(iterator.next());
        }
        ObservableList<String> list = FXCollections.observableArrayList(al);
        parkComboBox.setItems(list);
	}

	
	@FXML
	public void showGraphWindow(ActionEvent event) {
		errorMsg.setVisible(false);
				
		if (parkComboBox.getValue() == null || datePicker.getValue() == null) {
		    errorMsg.setText("Fill All Fields");
		    errorMsg.setVisible(true);		    
		}
		else {
			String year = datePicker.getValue().getYear() + "";
			String month = datePicker.getValue().getMonthValue() + "";
			String day = datePicker.getValue().getDayOfMonth() + "";
			String parkName = parkComboBox.getValue();
			CancellationsGraphFrameController.setYear(year);	
			CancellationsGraphFrameController.setMonth(month);	
			CancellationsGraphFrameController.setDay(day);	
			CancellationsGraphFrameController.setParkName(parkName);
			SceneController sc = new SceneController();
			sc.setPane(ClientUI.contentPane, "/main/gui/dep_manager/CancellationsGraphFrame.fxml");
		}		
	}
	
	public static void setArrayListLeft(CancelledReservation[] arrayListLeft) {
		CancellationsReportFrameController.leftTableArray = arrayListLeft;
    }
	    
	public static void setArrayListRight(CancelledReservation[] arrayListRight) {
		CancellationsReportFrameController.rightTableArray= arrayListRight;
	}

}