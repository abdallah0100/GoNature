package main.handlers;

import java.sql.PreparedStatement;

import entities.Order;
import main.Constants;
import main.MainServer;

public class ReservationRequestHandler {
	
	public static Order getReservationById(String id) {
			return null;//reservation data
	}
	
	public static boolean createTempReservation(Order o) {
		return false;//3 data order and insert to tmp table
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
