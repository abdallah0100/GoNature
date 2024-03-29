package main.threads;

import java.util.ArrayList;
import java.util.Calendar;

import entities.InboxMessage;
import entities.Order;
import main.ClientController;
import main.controllers.InboxRequestController;
import main.controllers.ReservationController;
import main.controllers.VisitorRequestController;
import main.entities.ConfirmationMessage;
import main.gui.visitor.VisitorHomePageController;


public class VisitorReminder implements Runnable{

	private static Order[] visitorOrders;
	private static int messageCnt = 0;
	private static ArrayList<InboxMessage> messages;	
	private static InboxMessage[] serverInboxResponse;
	
	@Override
	public void run() {
		if (ClientController.connectedVisitor == null)
			return;
		VisitorRequestController.showReservations(ClientController.connectedVisitor.getId());
		InboxRequestController.fetchInbox(ClientController.connectedVisitor.getId());
		updateAndGetMessages();
		
		while(visitorOrders.length > 0 || serverInboxResponse.length > 0) {
			if (ClientController.connectedVisitor == null)
				return;
			if (messageCnt > 0)
				VisitorHomePageController.publicNewMsgPane.setVisible(true);
			else 
				VisitorHomePageController.publicNewMsgPane.setVisible(false);
			try {
				Thread.sleep(60000);
				if (ClientController.connectedVisitor == null)
					return;
				VisitorRequestController.showReservations(ClientController.connectedVisitor.getId());
				InboxRequestController.fetchInbox(ClientController.connectedVisitor.getId());
				updateAndGetMessages();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		 
	}

	public static boolean timeArrived(Order o) {
		
		Calendar rightNow = Calendar.getInstance();
		int month = rightNow.get(Calendar.MONTH) + 1;
		int year = rightNow.get(Calendar.YEAR);
		int day = rightNow.get(Calendar.DAY_OF_MONTH);

		String[] dateSplitted = o.getDate().split("-");
		int orderDay = Integer.parseInt(dateSplitted[2]);
		int orderMonth= Integer.parseInt(dateSplitted[1]);
		int orderYear = Integer.parseInt(dateSplitted[0]);

		if (year - orderYear == 0 && orderMonth-month == 0 && orderDay-day == 1) {
			return true;
		}else
			return false;
	}
	
	public static boolean timePassed(Order o) {
		Calendar rightNow = Calendar.getInstance();
		int month = rightNow.get(Calendar.MONTH) + 1;
		int year = rightNow.get(Calendar.YEAR);
		int day = rightNow.get(Calendar.DAY_OF_MONTH);
		
		if (year > o.getYear())
			return true;
		if (year == o.getYear() && month > o.getMonth())
			return true;
		if (year == o.getYear() && month == o.getMonth() && day > o.getDay())
			return true;
		return false;
	}
	
	public static int getMsgcount() {
		return messageCnt;
	}
	public static void addConfirmableOrders() {
		Calendar rightNow = Calendar.getInstance();
		if (ClientController.reservationshowed == null) {
			VisitorRequestController.showReservations(ClientController.connectedVisitor.getId());
		}
		visitorOrders = ClientController.reservationshowed;
		messages = new ArrayList<>();
		messageCnt = 0;
		if(visitorOrders== null)return;
		int i=1;
		String content;
		String title;
		for (Order o : visitorOrders) {
			if (timeArrived(o) && !o.getIsConfirmed()) {
				if (!checkForMsgTimeOut(o)) {
					title = "Reservation #"+i;
					i++;
					content = "You have a reservation in "+ o.getParkName()+" at " + o.getTime();
					messages.add(new ConfirmationMessage(title, content, o));
					messageCnt++;
					if (!o.isSentMsg()) {
						o.setMessageTitle("Reservation #"+i);
						i++;
						o.setSentMsg(true);
						o.setMsgHour(rightNow.get(Calendar.HOUR_OF_DAY));
						o.setMsgMinute(rightNow.get(Calendar.MINUTE));
					}
				}else {
					o.setCancelRequest(false);
					ReservationController.sendCancelReservation(o);
				}
			}else if (timePassed(o)) { //handling confirmed but have not been used reservations
				
				o.setCancelRequest(false);
				ReservationController.sendCancelReservation(o);
			}
		}
	}
	
	public static void addInbox() {
		if (serverInboxResponse != null) {
			for (InboxMessage msg : serverInboxResponse) {
				messages.add(msg);
				messageCnt++;
			}
		}
	}
	
	public static void removeMsgIfExist(int id) {
		ArrayList<InboxMessage> msgs = new ArrayList<>();
		for (InboxMessage m : serverInboxResponse) {
			if (m.getId() == id)
				continue;
			msgs.add(m);
		}
		setServerInboxResponse(msgs.toArray(new InboxMessage[msgs.size()]));
	}
	
	public static boolean checkForMsgTimeOut(Order o) {
		if (!o.isSentMsg())
			return false;
		
		Calendar rightNow = Calendar.getInstance();
		int hour = rightNow.get(Calendar.HOUR_OF_DAY);
		int minute = rightNow.get(Calendar.MINUTE);
		
		if (hour - o.getMsgHour() > 2)
			return true;
		else if (hour - o.getMsgHour() == 2)
			return minute >= o.getMsgMinute();
		return false;
	}
	
	public static ArrayList<InboxMessage> updateAndGetMessages(){
		addConfirmableOrders();
		addInbox();
		VisitorHomePageController.inboxMessages.setAll(messages);
		return messages;
	}

	public static InboxMessage[] getServerInboxResponse() {
		return serverInboxResponse;
	}

	public static void setServerInboxResponse(InboxMessage[] serverInboxResponse) {
		VisitorReminder.serverInboxResponse = serverInboxResponse;
	}
	
}
