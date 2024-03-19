package main.handlers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import entities.Bill;
import entities.User;
import entities.Visitor;
import main.Constants;
import main.MainServer;
import main.gui.MainServerFrameController;

public class UserRequestHandler {
	
	public static User handleLogInRequest(User k) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		User u = userExists(k.getUsername(),k.getPassword());
		return u;

	}
	
	public static User userExists(String userName,String password){
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR); 
			return null;
		}
		User u;
		try {
			if(!(MainServerFrameController.isImport)){
				return null;
			}
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM users WHERE username ='"+userName+"'AND password ='"+password+"'");
			if (!rs.next()) {
				System.out.println("[UserRequestHandler] - result set was empty");
				return null;
			}
			u = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
			rs.close();
		}catch(Exception ex) {
			System.out.println("[UserRequestHandler] - failed to execute query");
			ex.printStackTrace();
			return null;
		}
		return u;
	}
	
	public static Bill billExists(Bill b1){
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		Bill b;
		try {
			Statement st = MainServer.dbConnection.createStatement();
			//reservation
			ResultSet rs = st.executeQuery("SELECT * FROM reservations WHERE ReservationID='"+b1.getId()+"'");
			if (!rs.next()) {
				System.out.println("[UserRequestHandler] - r1 result set was empty");
				return null;
			}
			b = new Bill(rs.getString(1),rs.getString(2),rs.getString(12).equals("1"),rs.getString(13).equals("1"));
			rs.close();
		}catch(Exception ex) {
			System.out.println("[UserRequestHandler] - failed to execute query");
			ex.printStackTrace();
			return null;
		}
		return b;
	}
	
	public static int instructorExists(Visitor v) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return -1;
		}
		String id=v.getId();
		try {
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM registered_instructors WHERE ID='"+id+"'");
			if (!rs.next()) {
				int s=st.executeUpdate( "INSERT INTO registered_instructors (ID, Name, Email, Telephone) " +
		                  				"VALUES ('159', 'loa', 'loa@email.com', '05494')");
				if(s>0)
					return 1;
				return -1;
			}
			else 
			{return 0;}
		}catch(Exception ex) {
			System.out.println("[UserRequestHandler] - failed to instructorExists");
			ex.printStackTrace();
			return -1;
		}
		
	}
	
	//exit from the park
	public static boolean checkExiting(String id) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT NumberOfVisitors,Park FROM tempreservation WHERE ReservationID='"+id+"'");
			if (!rs.next()) {
				return false;
			}
			
			int oldNumber=rs.getInt("NumberOfVisitors");
			String parkName = rs.getString("Park");
			
			ResultSet rs2 = st.executeQuery("SELECT gap FROM parks WHERE ParkName='"+parkName+"'");
			if (!rs2.next()) {
				return false;
			}
			int total = rs2.getInt("gap");
			int newGap = total - oldNumber;
			
				String str = "UPDATE parks SET gap=? WHERE ParkName=? ";
				PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);	
		        ps.setInt(1, newGap);
		        ps.setString(2, parkName);
		        int rowsAffected = ps.executeUpdate(); // Execute the update query
		        return rowsAffected > 0; // Return true if the update was successful
				//return true;
			}catch(Exception ex) {
				System.out.println("[UserRequestHandler] - failed to checkEntering");
				ex.printStackTrace();
				return false;
			}
	}
	
	//entring to the park 
	public static boolean checkEntering(String id) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT NumberOfVisitors,Park FROM reservations WHERE ReservationID='"+id+"'");
			if (!rs.next()) {
				return false;
			}
			int oldNumber=rs.getInt("NumberOfVisitors");
			String parkName = rs.getString("Park");
			ResultSet rs2 = st.executeQuery("SELECT gap FROM parks WHERE ParkName='"+parkName+"'");
			if (!rs2.next()) {
				return false;
			}
			int total = rs2.getInt("gap");
			int newGap = total + oldNumber;
				String str = "UPDATE parks SET gap=? WHERE ParkName=? ";
				PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);	

		        ps.setInt(1, newGap);
		        ps.setString(2, parkName);
		        int rowsAffected = ps.executeUpdate(); // Execute the update query
		        return rowsAffected > 0; // Return true if the update was successful

				//return true;
			}catch(Exception ex) {
				System.out.println("[UserRequestHandler] - failed to checkEntering");
				ex.printStackTrace();
				return false;
			}
		
	}
	
	//delete reservation 
	public static boolean delete(String s[]) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {    	
				String str = "DELETE FROM " + s[1] + " WHERE ReservationID = ?";
    			PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);	
    			ps.setString(1, s[0]);
				
		        int rowsAffected = ps.executeUpdate(); 
		        return rowsAffected > 0; // Return true if the Delete was successful

				//return true;
			}catch(Exception ex) {
				System.out.println("[UserRequestHandler] - failed to checkEntering");
				ex.printStackTrace();
				return false;
			}
	}
	
	//insert reservation(copy from reservations to tempreservation)
	public static boolean insert(String id) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {    	
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM reservations WHERE ReservationID='"+id+"'");
			if (!rs.next()) {
				return false;
			}
				String str = "INSERT INTO tempreservation (Type, NumberOfVisitors, ReservationDate, Hour, Minute, Park, Telephone, Email, ReservationID, visitorID, isConfirmed, InvitedInAdvance, payed) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
    			PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);	
    			ps.setString(1,rs.getString("Type"));
   		        ps.setString(2,rs.getString("NumberOfVisitors"));
   		        ps.setString(3,rs.getString("ReservationDate"));
   		        ps.setString(4,rs.getString("Hour"));
		   		ps.setString(5,rs.getString("Minute"));
		   		ps.setString(6,rs.getString("Park"));
		    	ps.setString(7,rs.getString("Telephone"));
		   		ps.setString(8,rs.getString("Email"));
		   		ps.setString(9,rs.getString("ReservationID"));
		   		ps.setString(10,rs.getString("visitorID"));
		   		ps.setString(11,rs.getString("isConfirmed"));
		   		ps.setString(12,rs.getString("InvitedInAdvance"));
		   		ps.setString(13,rs.getString("payed"));
		        int rowsAffected = ps.executeUpdate(); 
		        return rowsAffected > 0; // Return true if the INSERT was successful

				//return true;
			}catch(Exception ex) {
				System.out.println("[UserRequestHandler] - failed to checkEntering");
				ex.printStackTrace();
				return false;
			}
	}
	
}
