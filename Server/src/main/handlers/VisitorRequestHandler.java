package main.handlers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Order;
import entities.Visitor;
import main.Constants;
import main.MainServer;

/**
 * The VisitorRequestHandler class provides functionalities to handle various visitor-related requests such as
 * validating visitor existence, making reservation requests, showing reservations for a visitor, updating reservations,
 * and admitting a reservation. It interacts with the database to perform these operations.
 */
public class VisitorRequestHandler {

	/**
	 * Validates if a visitor exists in the database and returns a Visitor object.
	 * If the visitor does not exist, a new Visitor object is created without marking it as found in the database.
	 * 
	 * @param id The unique ID of the visitor.
	 * @return A Visitor object with information from the database if the visitor exists, otherwise a new Visitor object.
	 */
	public static Visitor handleValidateRequest(String id) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		Visitor v = visitorExists(id);
		if (v == null)
			v = new Visitor(id);
		else
			v.setFoundInDB(true);
		return v;
	}
	
	/**
	 * Checks if a visitor exists in the database by their unique ID.
	 * 
	 * @param id The unique ID of the visitor.
	 * @return A Visitor object if the visitor exists, otherwise null.
	 */
	public static Visitor visitorExists(String id){
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		Visitor v;
		try {
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM visitors WHERE ID='"+id+"'");
			if (!rs.next()) {
				System.out.println("[VisitorRequestHandler] - result set was empty");
				return null; 
			}
			v = new Visitor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4).equals("1"));
			rs.close();
		}catch(Exception ex) {
			System.out.println("[VisitorRequestHandler] - failed to execute query");
			ex.printStackTrace();
			return null;
		}
		return v;
	}

	/**
	 * Handles the request to make a reservation for a visitor. It checks if the visitor exists and adds them if not.
	 * Then, it either adds the reservation to the reservations table or to the waiting list depending on the provided table name.
	 * 
	 * @param o The Order object containing reservation details.
	 * @param tableName  The name of the table to insert the reservation into, either "reservations" or "waiting_list".
	 * @return The Order object with updated information, including the generated reservation ID if the operation is successful, otherwise null.
	 */
	public static Order handleMakeReservationRequest(Order o,String tableName) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		// Check visitor existence, insert visitor if not exists, and insert reservation details to specified table.
		try{			
			Statement stat = MainServer.dbConnection.createStatement();
		    String checkVisitorQuery = "SELECT * FROM visitors WHERE ID = '" + o.getVisitorID() + "'";
		    ResultSet rs = stat.executeQuery(checkVisitorQuery);
		    boolean visitorExists = rs.next(); 
		    if (!visitorExists) {
		         String insertVisitorQuery = "INSERT INTO visitors (ID, Email, Telephone, isInstructor) VALUES ('" +
		                 o.getVisitorID() + "', '" + o.getEmail() + "', '" + o.getPhone() + "', '0')";
		         stat.executeUpdate(insertVisitorQuery);
		    }
		    // If in waiting list
	        if(tableName.equals("waiting_list") && ReservationRequestHandler.inWaitngList(o)) 
	        	return null;
	        
	        String sql = "INSERT INTO " + tableName + " (Type, NumberOfVisitors, ReservationDate, Hour, Minute, Park, Telephone, Email, visitorID, isConfirmed, invitedInAdvance, payed, processed) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement preparedStatement = MainServer.dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        preparedStatement.setString(1, o.getOrderType());
	        preparedStatement.setInt(2, o.getNumOfVisitors());
	        preparedStatement.setDate(3, java.sql.Date.valueOf(o.getDate()));
	        preparedStatement.setString(4, o.getHour());
	        preparedStatement.setString(5, o.getMinute());
	        preparedStatement.setString(6, o.getParkName());
	        preparedStatement.setString(7, o.getPhone());
	        preparedStatement.setString(8, o.getEmail());
	        preparedStatement.setString(9, o.getVisitorID());
	        preparedStatement.setBoolean(10, o.getIsConfirmed());
	        preparedStatement.setBoolean(11, o.getInvitedInAdvance());
	        preparedStatement.setBoolean(12, o.getIsPayed());
	        preparedStatement.setString(13, "-1");
	        
		    if(preparedStatement.executeUpdate() <=0)return null;
		    // Getting the generated order id
		    ResultSet keys = preparedStatement.getGeneratedKeys(); 
		    if (keys.next())
		    	o.setOrderID(keys.getInt(1)+ "");
		}catch(Exception ex) {
			System.out.println("[VisitorRequestHandler] - failed to execute query");
			ex.printStackTrace();
			return null;}
		return o;
			
	}
	
	/**
	 * Retrieves all reservations associated with a given visitor ID.
	 * 
	 * @param id The unique ID of the visitor.
	 * @return An array of Order objects representing the reservations made by the visitor.
	 */
	public static Order[] handleShowReservationsRequest(String id) {
		 List<Order> orders = new ArrayList<>();
		    ResultSet rs = null;
		    // Fetch reservations from the database and populate the orders list.
		    try {           
		        String query = "SELECT * FROM reservations WHERE visitorID = ?";
		        PreparedStatement ps = MainServer.dbConnection.prepareStatement(query);
		        ps.setString(1, id);
		        rs = ps.executeQuery();
		        while (rs.next()) {
		            String type = rs.getString(1);
		            int numberOfVisitors = rs.getInt(2);
		            String date = rs.getString(3);
		            String hour = rs.getString(4);
		            String minute = rs.getString(5);
		            String park = rs.getString(6);
		            String telephone = rs.getString(7);
		            String email = rs.getString(8);
		            int reservationID = rs.getInt(9);
		            boolean isConfirmed = rs.getBoolean(11);
		            boolean invitedInAdvance = rs.getBoolean(12);
		            boolean payed = rs.getBoolean(13);
		            String processed = rs.getString(14);
		            orders.add(new Order(type, numberOfVisitors, date, hour, minute, park, telephone, email, reservationID, id, isConfirmed, invitedInAdvance, payed, processed));  
		        }
		        rs.close();
		    } catch (SQLException e) {
		        e.printStackTrace();         
		    }
		    Order[] ordersArray = new Order[orders.size()];
		    ordersArray = orders.toArray(ordersArray);
		    
		    return ordersArray;
		}
	
	/**
	 * Updates an existing reservation with new details provided in the {@code Order} object.
	 *  
	 * @param o The Order object containing the updated reservation details.
	 * @return The updated Order object if the operation is successful, otherwise null.
	 */
	public static Order handleUpdateReservationRequest(Order o) {
		 try {
		// Update reservation details in the database.
        String query = "UPDATE reservations " +
                       "SET Type = ?, NumberOfVisitors = ?, ReservationDate = ?, Hour = ?, Minute = ?, Park = ?, Telephone = ?, Email = ?, isConfirmed = ?, InvitedInAdvance = ?, payed = ? " +
                       "WHERE ReservationID = ?";
        PreparedStatement ps = MainServer.dbConnection.prepareStatement(query);
        ps.setString(1, o.getOrderType());
        ps.setInt(2, o.getNumOfVisitors());
        ps.setString(3, o.getDate());
        ps.setString(4, o.getHour());
        ps.setString(5, o.getMinute());
        ps.setString(6, o.getParkName());
        ps.setString(7, o.getPhone());
        ps.setString(8, o.getEmail());
        ps.setBoolean(9, o.getIsConfirmed());
        ps.setBoolean(10, o.getInvitedInAdvance());
        ps.setBoolean(11, o.getIsPayed());
        ps.setInt(12, Integer.parseInt(o.getOrderID()));
        int rowsUpdated = ps.executeUpdate();
        if (rowsUpdated == 1) {
            return o;}
		} catch (SQLException e) {
        e.printStackTrace();} 
         return null;
    }
	
	/**
	 * Marks a reservation as confirmed/admitted in the database based on the reservation ID provided in the Order object.
	 * @param o The Order object containing the reservation ID to be confirmed.
	 * @return True if the operation is successful and the reservation is marked as confirmed, otherwise false.
	 */
	public static boolean admitReservation(Order o) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
        try {
        	// Update the reservation's confirmed status in the database.
        	Statement st = MainServer.dbConnection.createStatement();
        	String str = "UPDATE reservations SET isConfirmed='1' WHERE ReservationID='"+o.getOrderID()+"'";
        	return st.executeUpdate(str) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
         }		
	}		
}
