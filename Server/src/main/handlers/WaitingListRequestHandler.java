package main.handlers;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import entities.InboxMessage;
import entities.Order;
import main.Constants;
import main.MainServer;

/**
 * The WaitingListRequestHandler class is responsible for managing the waiting list for park reservations.
 * This includes operations such as fetching the entire waiting list for a specific park, removing orders from the waiting list,
 * and checking the waiting list for orders that can be admitted to the park based on current availability or those that have expired.
 */
public class WaitingListRequestHandler {
	

	/**
	 * Retrieves all orders in the waiting list for a specified park.
	 *  
	 * @param parkName The name of the park for which the waiting list orders are to be fetched.
	 * @return An array of Order objects representing the orders in the waiting list for the specified park
	 *         or null if the database connection is unavailable or an error occurs during the query execution.
	 */
	public static Order[] getAllWaitingList(String parkName) {
		 // Check for database connection availability
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
	
	/**
	 * Removes an order from the waiting list based on a given reservation ID.
	 * 
	 * @param id The identifier of the order to be removed from the waiting list.
	 * @return True if the order is successfully removed from the waiting list false otherwise.
	 */
	public static boolean removeFromWaitingList(String id) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {
			String str = "DELETE FROM waiting_list WHERE Queue='"+id+"'";
			Statement st = MainServer.dbConnection.createStatement();
			if (!(st.executeUpdate(str) > 0)) {
				System.out.println("did not delete from waiting list");
				return false;
			}
			return true;
			}catch(Exception ex) {
				System.out.println("[WaitingListRequestHandler] - Error executing query in getAllWaitingList");
				ex.printStackTrace();
				return false;
			}
	}
	
	/**
	 * Checks the waiting list for the specified park and processes orders that can be admitted based on the current availability,
	 * or marks orders as canceled if they have expired (past the reservation date).
	 * 
	 * @param parkName The name of the park for which the waiting list is to be checked.
	 */
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
				InboxRequestHandler.addMessage(o.getVisitorID(), new InboxMessage("Waiting Expiration", "Your order #"+o.getOrderID() + "'s time has expired"));
				ReservationRequestHandler.addToCanceledReports(o);
				WaitingListRequestHandler.removeFromWaitingList(o.getOrderID());
			}
			String id = o.getOrderID();
			if (ParkRequestHandler.parkHasSpace(o)) {
				Order o2 = VisitorRequestHandler.handleMakeReservationRequest(o, "reservations");
				InboxRequestHandler.addMessage(o.getVisitorID(), new InboxMessage("New Order", "Your order #"+id + "'s has been set to active, new Id: "+o2.getOrderID()));
				WaitingListRequestHandler.removeFromWaitingList(id);
			}
			
		}
	}
	
	public static Order[] getVisitorWaitingList(String id) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		
		try {
			String str = "SELECT * FROM waiting_list WHERE visitorID='"+id+"'";
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery(str);
			ArrayList<Order> arr = new ArrayList<>();
			while(rs.next()) {
				Order o=new Order(rs.getString("Type"),rs.getInt("NumberOfVisitors"),rs.getString("ReservationDate"),rs.getString("Hour"),
						rs.getString("Minute"),rs.getString("Park"),rs.getString("Telephone"),rs.getString("Email"),
						rs.getInt("Queue"),rs.getString("visitorID"),
						rs.getBoolean("isConfirmed"),rs.getBoolean("InvitedInAdvance"),rs.getBoolean("payed"),rs.getString("processed"));
				arr.add(o);
			}
			return (Order[])arr.toArray(new Order[arr.size()]);
			}catch(Exception ex) {
				System.out.println("[WaitingListRequestHandler] - Error executing query in getAllWaitingList");
				ex.printStackTrace();
				return null;
			}
	}
	
}
