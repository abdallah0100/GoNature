package main.gui.visitor;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Bill;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class BookConfirmationController implements Initializable{

	@FXML
	private Label resultLabel;
	@FXML
	private Label cost;
	@FXML
	private Button cancelBtn;
	@FXML
	private Button confirm;
	
	public static  Bill bill;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (bill != null) {
			String price = String.format("%.2f₪", bill.returnPrice());
			cost.setText(price);
		}else {
			resultLabel.setText("Error creating bill");
			resultLabel.setVisible(false);
		}
		
	}
	
	public void confirmOrder(ActionEvent event) {
		
		resultLabel.setText("Order has been confirmed.");
		confirm.setDisable(true);
		cancelBtn.setDisable(true);
		resultLabel.setVisible(true);
	}
	
	public void cancel(ActionEvent event) {
		resultLabel.setText("Order has been canceled.");
		resultLabel.setVisible(true);
		confirm.setDisable(true);
		cancelBtn.setDisable(true);
	}
	
	
}
