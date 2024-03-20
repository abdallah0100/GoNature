package main.gui.visitor;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entities.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import main.ClientController;
import main.controllers.ReservationController;
import main.threads.VisitorReminder;

public class VisitorHomePageController implements Initializable{

	@FXML
	private ImageView inboxImg;
	@FXML
	private Pane inboxPane;
	@FXML
	private Pane newMsgPane;
	@FXML
	private Label msgAmount;
	@FXML
	private ComboBox<String> msgSelector;
	@FXML
	private Label confirmationLabel;
	@FXML
	private Pane confirmationPane;
	@FXML
	private Label confirmResultLabel;
	@FXML
	private Button admitBtn;
	@FXML
	private Button cancelBtn;
	
	
	public static Pane publicNewMsgPane;
	private boolean select = true;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//msgAmountLabel.textProperty().bind(VisitorReminder.test1);
		publicNewMsgPane = newMsgPane;
		Thread visitorReminder = new Thread(new VisitorReminder());
		visitorReminder.start();
	}
	
	@FXML
	public void clickInboxIcon(MouseEvent event) {
		msgAmount.setText(VisitorReminder.getMsgcount() + "");
		ArrayList<Order> ordersToConfirm = VisitorReminder.getOrdersToConfirm();
		if (msgSelector.getItems() != null)
			msgSelector.getItems().clear();
		
		if (VisitorReminder.getMsgcount() > 1) {
			for (int i = 0; i < ordersToConfirm.size(); i++)
				msgSelector.getItems().add("Reservation #" + (i+1));
			msgSelector.setVisible(true);
			confirmationPane.setVisible(false);
		}else if (VisitorReminder.getMsgcount() == 1) {
			confirmationLabel.setText("You have a reservation in "+ordersToConfirm.get(0).getParkName()+" at " + ordersToConfirm.get(0).getTime());
			confirmationPane.setVisible(true);
			msgSelector.setVisible(false);
			admitBtn.setVisible(true);
			cancelBtn.setVisible(true);
			confirmResultLabel.setVisible(false);
		}else {
			confirmationPane.setVisible(false);
			msgSelector.setVisible(false);
		}
		
		inboxPane.setVisible(!inboxPane.isVisible());
	}
	@FXML
	public void closeInbox(MouseEvent event) {
		inboxPane.setVisible(false);
	}
	
	public void selectReservation(ActionEvent event) {
		if (select) {
			ArrayList<Order> orders = VisitorReminder.getOrdersToConfirm();
			int index = msgSelector.getItems().indexOf(msgSelector.getValue());
			if ((orders != null && orders.size() == 1) || index < 0)
				index = 0;
			if (orders != null && orders.size() != 0 && orders.get(index) != null) {
				Order selectedOrder = orders.get(index);
					if (selectedOrder != null) {
						confirmationLabel.setText("You have a reservation in "+selectedOrder.getParkName()+" at " + selectedOrder.getTime());
						confirmationPane.setVisible(true);
						admitBtn.setVisible(true);
						cancelBtn.setVisible(true);
						confirmResultLabel.setVisible(false);
					}
			}
		}
		else 
			select = true;
	}
	
	@FXML
	public void admit(ActionEvent event) {
		ArrayList<Order> orders = VisitorReminder.getOrdersToConfirm();
		int index = msgSelector.getItems().indexOf(msgSelector.getValue());
		if (orders != null && orders.size() == 1)
			index = 0;
		if (orders != null && orders.size() != 0 && orders.get(index) != null) {
			Order selectedOrder = orders.get(index);
			if (selectedOrder != null) {
				ReservationController.sendConfirmReservation(selectedOrder);
				for (Order o : ClientController.reservationshowed) {
					if (o.getOrderID().equals(selectedOrder.getOrderID())){
						if (o.getIsConfirmed()) {
							if (msgSelector.getItems() != null && msgSelector.getItems().size() != 0) {
								select = false;
								msgSelector.getItems().remove(index);
								select = false;
								msgSelector.setValue("");
							}
							
							confirmResultLabel.setText("Order has been admitted");
							confirmResultLabel.setVisible(true);
							admitBtn.setVisible(false);
							cancelBtn.setVisible(false);
							
							msgAmount.setText(VisitorReminder.getMsgcount() + "");
		
							if (VisitorReminder.getMsgcount() == 0)
								newMsgPane.setVisible(false);
							
							break;
						}
					}
				}
			}
			else
				System.out.println("[VisitorHomePageController] - Order was null");
		}
	}
	
	@FXML
	public void cancel(ActionEvent event) {
		ArrayList<Order> orders = VisitorReminder.getOrdersToConfirm();
		int index = msgSelector.getItems().indexOf(msgSelector.getValue());
		if (orders != null && orders.size() == 1)
			index = 0;
		if (orders != null && orders.size() != 0 && orders.get(index) != null) {
			Order selectedOrder = orders.get(index);
			if (selectedOrder != null) {
				selectedOrder.setCancelRequest(true);
				ReservationController.sendCancelReservation(selectedOrder);
				
				for (Order o : ClientController.reservationshowed)
					if (o.getOrderID().equals(selectedOrder.getOrderID())) {
						confirmResultLabel.setText("  Error canceling order");
						confirmResultLabel.setVisible(true);
						return;
					}
				if (msgSelector.getItems() != null && msgSelector.getItems().size() != 0) {
					select = false;
					msgSelector.getItems().remove(index);
					select = false;
					msgSelector.setValue("");
				}
				confirmResultLabel.setText("Order has been canceled");
				confirmResultLabel.setVisible(true);
				admitBtn.setVisible(false);
				cancelBtn.setVisible(false);
				
				msgAmount.setText(VisitorReminder.getMsgcount() + "");

				if (VisitorReminder.getMsgcount() == 0)
					newMsgPane.setVisible(false);
			}
		}
	}
	
}
