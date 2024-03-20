package main.handlers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entities.Order;
import main.Constants;
import main.MainServer;

public class ReservationRequestHandler {
	
	public static Order getReservationById(String id,String tableName) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		try {    
				Order o;
				String query = "SELECT * FROM " + tableName + " WHERE ReservationID = ?";
		        PreparedStatement ps = MainServer.dbConnection.prepareStatement(query);
		        ps.setString(1, id);
		        ResultSet rs = ps.executeQuery();
		        if (!rs.next()) {
		            return null;
		        }
			if(tableName.equals("reservations"))
			{
				 o=new Order(rs.getString("Type"),rs.getInt("NumberOfVisitors"),rs.getString("ReservationDate"),rs.getString("Hour"),
						rs.getString("Minute"),rs.getString("Park"),rs.getString("Telephone"),rs.getString("Email"),
						rs.getInt("ReservationID"),rs.getString("visitorID"),
						rs.getBoolean("isConfirmed"),rs.getBoolean("InvitedInAdvance"),rs.getBoolean("payed"));
				return o;//reservation data
			}
			if(tableName.equals("tempreservation")) {
				o=new Order(rs.getInt("NumberOfVisitors"),rs.getString("Park"),rs.getString("ReservationID"));
				return o;
			}
			return null;

		}catch(Exception ex) {
			System.out.println("[UserRequestHandler] - failed to checkEntering");
			ex.printStackTrace();
			return null;
		}
	}
	
	
	//insert copy to temp reservation
	//3 data order and insert to tmp table
	public static boolean createTempReservation(Order o) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {  	
				String str = "INSERT INTO tempreservation (NumberOfVisitors,Park,ReservationID) VALUES (?,?,?) ";
    			PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);	
   		        ps.setInt(1,o.getNumOfVisitors());
		   		ps.setString(2,o.getParkName());
		   		ps.setString(3,o.getOrderID());
		        int rowsAffected = ps.executeUpdate(); 
		        return rowsAffected > 0; // Return true if the INSERT was successful
				//return true;
			}catch(Exception ex) {
				System.out.println("[UserRequestHandler] - failed to checkEntering");
				ex.printStackTrace();
				return false;
			}
	}
	
	
	// if reservation deleted 
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
				//return true;
			}catch(Exception ex) {
				System.out.println("[UserRequestHandler] - failed to checkEntering");
				ex.printStackTrace();
				return false;
			}
	}
	
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
			pr.setString(5, o.isCancelRequest() ? "1" : "0");
			pr.setString(6, o.isCancelRequest() ? "0" : "1");
			
			return pr.executeUpdate() > 0;
		}catch(Exception ex) {
			System.out.println("[UserRequestHandler] - failed to checkEntering");
			ex.printStackTrace();
			return false;
		}
	}
		
}
