package main.handlers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;

import entities.Order;
import main.Constants;
import main.MainServer;

public class ReservationRequestHandler {
	
	public static Order getReservationById(String []s,String tableName) {//s[0]  ClientController.connectedUser.getParkName s[1] resevation id
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
				 if(s[0].equals(o.getParkName()))
					 return o;//reservation data
				 else 
					 return null;
		}catch(Exception ex) {
			System.out.println("[UserRequestHandler] - failed to checkEntering");
			ex.printStackTrace();
			return null;
		}
	}

	
	
	//insert copy to processedres reservation
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

	
	
	// change  processed in reservation table to 0/1 (know if the reservation )
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
		

	
	//change
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
