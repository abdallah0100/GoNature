package main.handlers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

import entities.Order;
import main.Constants;
import main.MainServer;

/**
 * Handles requests related to reservations including retrieval, entry processing, status updates, and deletions.
 */
public class ReservationRequestHandler {
	
	/**
	 * Retrieves a reservation by its ID and table name, ensuring it matches the park name and the current time.
	 * 
	 * @param s Array containing the park name and reservation ID.
	 * @param tableName The name of the table from which to retrieve the reservation.
	 * @return The matching Order object if found; null otherwise.
	 */
	public static Order getReservationById(String []s,String tableName) {
		//s[0]  ClientController.connectedUser.getParkName s[1] resevation id
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		try {    
				Order o;
				String query = "SELECT * FROM " + tableName + " WHERE ReservationID = ?";
		        PreparedStatement ps = MainServer.dbConnection.prepareStatement(query);
		        ps.setString(1, s[1]);
		        ResultSet rs = ps.executeQuery();
		        if (!rs.next()) {
		            return null;
		        }
				 o=new Order(rs.getString("Type"),rs.getInt("NumberOfVisitors"),rs.getString("ReservationDate"),rs.getString("Hour"),
						rs.getString("Minute"),rs.getString("Park"),rs.getString("Telephone"),rs.getString("Email"),
						rs.getInt("ReservationID"),rs.getString("visitorID"),
						rs.getBoolean("isConfirmed"),rs.getBoolean("InvitedInAdvance"),rs.getBoolean("payed"),rs.getString("processed"));
				  String date = o.getDate();
				  LocalDate parsedDate = LocalDate.parse(date);
				 if(s[0].equals(o.getParkName()) && o.getHour().equals(LocalTime.now().getHour() + "")  && LocalDate.now().equals(parsedDate)) //the same time and same park
					 return o;//reservation data
				 else 
					 return null;
		}catch(Exception ex) {
			System.out.println("[UserRequestHandler] - failed to checkEntering");
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Inserts a reservation into the processed reservations table upon entry.
	 * 
	 * @param o The Order object representing the reservation.
	 * @return True if the insertion is successful; false otherwise.
	 */
	public static boolean enterProcessed(Order o) {
			if (MainServer.dbConnection == null) {
				System.out.println(Constants.DB_CONNECTION_ERROR);
				return false;
			}
			try {  	
					String str = "INSERT INTO processedRes (NumberOfVisitors,Park,ReservationID,enterHour,enterMin,exitHour,exitMin,date,reservationType)"
							+ " VALUES (?,?,?,?,?,?,?,?,?) ";
					PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);	
	    		ps.setInt(1,o.getNumOfVisitors());//NumberOfVisitors
			   	ps.setString(2,o.getParkName());//Park
			   	ps.setString(3,o.getOrderID());//ReservationID
			   		
			    ps.setInt(4,LocalTime.now().getHour());//enterHour
			   	ps.setInt(5,LocalTime.now().getMinute());//enterMin
			   		
			   	ps.setInt(6,0);//exitHour
			    ps.setInt(7,0);//exitMin
			        
			   	ps.setString(8,o.getDate());//date
			   	ps.setString(9,o.getOrderType());//reservationType
			      int rowsAffected = ps.executeUpdate(); 
			       return rowsAffected > 0; // Return true if the INSERT was successful
					//return true;
				}catch(Exception ex) {
					System.out.println("[UserRequestHandler] - failed to checkEntering");
					ex.printStackTrace();
					return false;
				}
		}

		/**
		 *  Updates the processed status of a reservation in a specified table.
		 *  
		 * @param tableName The name of the table to update.
		 * @param id The ID of the reservation to update.
		 * @param n The new processed status value.
		 * @return True if the update is successful; false otherwise.
		 */
		public static boolean updateStatus(String tableName,String id ,int n) {
			if (MainServer.dbConnection == null) {
				System.out.println(Constants.DB_CONNECTION_ERROR);
				return false;
			}
			try {    	
					String str = "UPDATE "+tableName+" SET processed=? WHERE ReservationID = ?";
	    			PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);	
	    			ps.setInt(1, n);
	    			ps.setString(2, id);
			        int rowsAffected = ps.executeUpdate(); 
			        return rowsAffected > 0; // Return true if the update was successful
					//return true;
				}catch(Exception ex) {
					System.out.println("[UserRequestHandler] - failed to checkEntering");
					ex.printStackTrace();
					return false;
				}
		}
		
	/**
	 * Updates a processed reservation with exit times.
	 * 
	 * @param id The ID of the reservation to update.
	 * @return True if the update is successful; false otherwise.
	 */
	public static boolean exitProcessed(String id)
	{
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {    	
				String str = "UPDATE processedres SET exitHour=?, exitMin=? WHERE ReservationID = ?";
    			PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);	
			    ps.setInt(1,LocalTime.now().getHour());//exitHour
			   	ps.setInt(2,LocalTime.now().getMinute());//exitMin
    			ps.setString(3, id);
		        int rowsAffected = ps.executeUpdate(); 
		        return rowsAffected > 0; // Return true if the update was successful
			}catch(Exception ex) {
				System.out.println("[UserRequestHandler] - failed to checkEntering");
				ex.printStackTrace();
				return false;
			}
	}
	
	/**
	 * Deletes a reservation from a specified table.
	 * 
	 * @param tableName The name of the table to delete from.
	 * @param id The ID of the reservation to delete.
	 * @return True if the deletion is successful; false otherwise.
	 */
	public static boolean deleteReservation(String tableName,String id) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {    	
				String str = "DELETE FROM " + tableName + " WHERE ReservationID = ?";
    			PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);	
    			ps.setString(1, id);
		        int rowsAffected = ps.executeUpdate(); 
		        return rowsAffected > 0; // Return true if the Delete was successful
			}catch(Exception ex) {
				System.out.println("[UserRequestHandler] - failed to checkEntering");
				ex.printStackTrace();
				return false;
			}
	}
	
	/**
	 * Adds a cancelled reservation to the cancellations reports table.
	 * 
	 * @param o The Order object representing the cancelled reservation.
	 * @return True if the addition is successful; false otherwise.
	 */
	public static boolean addToCanceledReports(Order o) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {    	
			String str = "INSERT INTO cancellationsReports (Type, Date, Park, reservationId, ReservationIdCancelled, ReservationIdNotActivated) "
					+ "VALUES(?,?,?,?,?,?)";
			PreparedStatement pr = MainServer.dbConnection.prepareStatement(str);
			pr.setString(1, o.getOrderType());
			pr.setString(2, o.getDate());
			pr.setString(3, o.getParkName());
			pr.setString(4, o.getOrderID());
			pr.setString(5, o.isCancelRequest() ? "Yes" : "No");
			pr.setString(6, o.isCancelRequest() ? "No" : "Yes");
			
			return pr.executeUpdate() > 0;
		}catch(Exception ex) {
			System.out.println("[UserRequestHandler] - failed to checkEntering");
			ex.printStackTrace();
			return false;
		}
	}	
	
	/**
	 * Checks if a similar order exists in the waiting list to avoid duplicates.
	 * 
	 * @param o The Order object to check against the waiting list.
	 * @return True if a similar order exists; false otherwise.
	 */
	public static boolean inWaitngList (Order o) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR); 
			return false;
		}
		try {
			String str = "SELECT * FROM waiting_list WHERE"
					+ " Type = '"+o.getOrderType()+"' AND"
					+" NumberOfVisitors = '"+o.getNumOfVisitors()+"' AND"
					+" ReservationDate = '"+o.getDate()+"' AND" 
					+" Hour = '"+o.getHour()+"' AND"
					+" Minute = '"+o.getMinute()+"' AND"
					+" Park = '"+o.getParkName()+"' AND"
					+" visitorID = '"+o.getVisitorID()+"'";	
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery(str);
			return rs.next();
		}catch(Exception ex) {
				System.out.println("[ReservationRequestHandler] - failed to execute query");
				ex.printStackTrace();
				return false;
		}
	}
	
	/**
	 * Retrieves the  ReservationID from the reservations table for non-advance reservations.
	 * 
	 * @param parkName The name of the park for the reservation.
	 * @return The highest ReservationID or 0 if none found.
	 */
	public static int OrderId(String parkName) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR); 
			return 0;
		}
		try {
			String str = "SELECT MAX(ReservationID) AS MaxReservationID FROM reservations WHERE InvitedInAdvance = 0";
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery(str);
			if (rs.next()) {
			  return rs.getInt("MaxReservationID");
			} else {
			   return 0;
			}
		}catch(Exception ex) {
				System.out.println("[ReservationRequestHandler] - failed to execute query");
				ex.printStackTrace();
				return 0;
		}
	}
}
