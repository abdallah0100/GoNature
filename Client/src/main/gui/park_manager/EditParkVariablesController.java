package main.gui.park_manager;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entities.Park;
import entities.Report;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import main.ClientController;
import main.controllers.ParkRequestHandler;

public class EditParkVariablesController implements Initializable{
	
	public static String updateResult;
	
	public static String requestResult;
	@FXML
	private Button editEstimatedTimeBtn;
	@FXML
	private Button editVisitorBtn;
	@FXML
	private Button editGapBtn;
	@FXML
	private Pane rightPane;
	@FXML
	private Label editingVariable;
	@FXML
	private Label parkName;
	@FXML
	private Label msgLabel;
	@FXML
	private Label currentValue;
	@FXML
	private Button send;
	@FXML
	private Button reSend;
	@FXML
	private Button cancel;
	@FXML
	private TextField newValue;
	@FXML
	public static boolean gap;
	public static boolean time;
	public static boolean limit;
	
	public void selectVariableToEdit(ActionEvent e ) {
		newValue.setText("");
		msgLabel.setVisible(false);
	    Button clickedBtn = (Button)e.getSource();
	    editingVariable.setText(clickedBtn.getText());   
	    setValues();
	    rightPane.setVisible(true);
		   reSend.setVisible(false);
		   send.setVisible(true);
	    
	}
	@FXML
	public void cancel(ActionEvent e ) {
		newValue.setText("");
		rightPane.setVisible(false);
		msgLabel.setVisible(false);
	}
	
	public void setValues() {
	    if (editingVariable.getText().contains("Gap"))
	    	currentValue.setText(ClientController.connectedUser.getPark().getGap() + "");
	    else if (editingVariable.getText().contains("Estimated"))
	    	currentValue.setText(ClientController.connectedUser.getPark().getEstimatedTime() + "");
	    else 
	    	currentValue.setText(ClientController.connectedUser.getPark().getMaxCapacity() + "");
	}
	
	@FXML
	public void update(ActionEvent e ) {
	   if (validInput()) {
		   if(editingVariable.getText().contains("Gap") && gap)
		   {
			   displayMsg("want to update request?", 129, "red");
			   reSend.setVisible(true);
			   send.setVisible(false);
			   return;
		   }
		   if(editingVariable.getText().contains("Estimated Time") && time)
		   {
			   displayMsg("want to update request?", 129, "red");
			   reSend.setVisible(true);
			   send.setVisible(false);
			   return;
		   }
		    if(editingVariable.getText().contains("Visitor Limit") && limit)
		   {
			   displayMsg("want to update request?", 129, "red");
			   reSend.setVisible(true);
			   send.setVisible(false);
			   return;
		   }
		   ParkRequestHandler.requsetChange(insertDataChange());
		   if(editingVariable.getText().contains("Gap"))
			   gap=true;  
		   if(editingVariable.getText().contains("Estimated Time"))
			   time=true;
		   if(editingVariable.getText().contains("Visitor Limit"))
			   limit=true;
	 	   displayMsg("sent request", 129, "red");
	 	   System.out.println(requestResult);
	 	}
	
	}
	public void reSend(ActionEvent e ) {
		   if (validInput()) {
			   msgLabel.setVisible(false);
		 	   ParkRequestHandler.UpdateData(insertDataChange());
		 	   displayMsg("sent request", 129, "red");
		   }
	}

	public boolean validInput() {
		if (newValue.getText() == null || newValue.getText().length() == 0 || newValue.getText().equals("0")) {
			displayMsg("Enter a new value to update", 106, "red");
			return false;
		}
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(newValue.getText());
		if (!m.matches()) {
			displayMsg("Enter a valid number", 129, "red");
			return false;
		}
		return true;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		parkName.setText(ClientController.connectedUser.getPark().getParkName());
		
	}
	
	public void displayMsg(String str, int layoutx, String color) {
		msgLabel.setText(str);
		msgLabel.setLayoutX(layoutx);
		msgLabel.setTextFill(Color.valueOf(color));
		msgLabel.setVisible(true);
	}
	
	public String[] insertDataChange()
	{
		String[] reqSer = new String[4];
		reqSer[0]=parkName.getText();
		reqSer[1]=ClientController.connectedUser.getUsername();
		reqSer[2]=editingVariable.getText();
		reqSer[3]=newValue.getText();
		return reqSer;
			    
	}
		    

}
