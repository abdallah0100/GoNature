package main.handlers;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import entities.Order;
import main.Constants;
import main.MainServer;

public class WaitingListRequestHandler {

	public static Order[] getAllWaitingList(String parkName) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		try {   ArrayList<Order> arr = new ArrayList<>();
				String str = "SELECT * FROM waiting_list WHERE Park='"+parkName+"'";
				Statement st = MainServer.dbConnection.createStatement();
				ResultSet rs = st.executeQuery(str);
				while (rs.next()) {
					Order o = new Order(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10),
							rs.getString(11).equals("1"), rs.getString(12).equals("1"), rs.getString(13).equals("1"),rs.getString(14));
					arr.add(o);
				}
				return (Order[])arr.toArray(new Order[arr.size()]);
			}catch(Exception ex) {
				System.out.println("[WaitingListRequestHandler] - Error executing query in getAllWaitingList");
				ex.printStackTrace();
				return null;
			}
	}
	
	public static boolean removeFromWaitingList(Order o) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {  
			String str = "DELETE * FROM waiting_list WHERE Queue='"+o.getOrderID()+"'";
			Statement st = MainServer.dbConnection.createStatement();
			return st.executeUpdate(str) > 0;
			}catch(Exception ex) {
				System.out.println("[WaitingListRequestHandler] - Error executing query in getAllWaitingList");
				ex.printStackTrace();
				return false;
			}
	}
	
	public static void checkWaitingListForAdmittableOrder(String parkName) {
		Order[] waitingList = WaitingListRequestHandler.getAllWaitingList(parkName);
		Calendar rightNow = Calendar.getInstance();
		int month = rightNow.get(Calendar.MONTH) + 1;
		int year = rightNow.get(Calendar.YEAR);
		int day = rightNow.get(Calendar.DAY_OF_MONTH);
		for (Order o : waitingList) {
			if ((year > o.getYear())//order expired
				|| (o.getYear() == year && month > o.getMonth())
				|| (o.getYear() == year && o.getMonth() == month && day > o.getDay())) {
				o.setCanceled(false);
				ReservationRequestHandler.addToCanceledReports(o);
				WaitingListRequestHandler.removeFromWaitingList(o);
			}
			if (ParkRequestHandler.parkHasSpace(o)) {
				VisitorRequestHandler.handleMakeReservationRequest(o, "reservations");
				WaitingListRequestHandler.removeFromWaitingList(o);
			}
			
		}
	}
	
}
