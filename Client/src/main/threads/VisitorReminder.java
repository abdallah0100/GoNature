package main.threads;

import java.util.ArrayList;
import java.util.Calendar;

import entities.Order;
import main.ClientController;
import main.controllers.VisitorRequestController;
import main.gui.visitor.VisitorHomePageController;

public class VisitorReminder implements Runnable{

	private static Order[] visitorOrders;
	private static int messageCnt = 0;
	private static ArrayList<Order> ordersToConfirm;
	
	@Override
	public void run() {
		VisitorRequestController.showReservations(ClientController.connectedVisitor.getId());
		updateOrders();
		
		while(visitorOrders.length > 0) {
			updateOrders();
			if (messageCnt > 0)
				VisitorHomePageController.publicNewMsgPane.setVisible(true);
			else 
				VisitorHomePageController.publicNewMsgPane.setVisible(false);
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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
	
	public static int getMsgcount() {
		return messageCnt;
	}
	public static void updateOrders() {
		visitorOrders = ClientController.reservationshowed;
		ordersToConfirm = new ArrayList<>();
		messageCnt = 0;
		for (Order o : visitorOrders)
			if (timeArrived(o) && !o.getIsConfirmed()) {
				ordersToConfirm.add(o);
				messageCnt++;
			}
	}
	public static ArrayList<Order> getOrdersToConfirm(){
		updateOrders();
		return ordersToConfirm;
	}
	
}
