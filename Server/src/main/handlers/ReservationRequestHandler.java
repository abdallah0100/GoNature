package main.handlers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM '"+ tableName+"' WHERE ReservationID='"+id+"'");
			if (!rs.next()) {
				return null;
			}
			if(tableName.contentEquals("reservations"))
			{
				 o=new Order(rs.getString("Type"),rs.getInt("NumberOfVisitors"),rs.getString("ReservationDate"),rs.getString("Hour"),
						rs.getString("Minute"),rs.getString("Park"),rs.getString("Telephone"),rs.getString("Email"),
						rs.getInt("ReservationID"),rs.getString("visitorID"),
						rs.getBoolean("isConfirmed"),rs.getBoolean("InvitedInAdvance"),rs.getBoolean("payed"));
				return o;//reservation data
			}
			if(tableName.contentEquals("tempreservation")) {
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
	
	
		
}
