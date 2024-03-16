package main.handlers;

import java.sql.ResultSet;
import java.sql.Statement;

import entities.Bill;
import entities.User;
import entities.Visitor;
import main.Constants;
import main.MainServer;

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
		String email=v.getEmail();
		String tel=v.getPhone();
		
		try {
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM visitors WHERE ID='"+id+"'");
			if (!rs.next()) {//if its not exist ever
				int s=st.executeUpdate( "INSERT INTO visitors (ID, Email, Telephone ,isInstructor) " +
		                  				"VALUES ('"+id+"','"+email+"', '"+tel+"' ,'1')");
				if(s>0)//if the insertion successful
					return 1;
				return -1;
			}else {//now we want to check if exist and not instructor we update the field
				int s=rs.getInt("isInstructor");
				if(s==1) {
					return 0;
				}else {
					st.executeUpdate("UPDATE visitors SET isInstructor = '"+1+"' WHERE ID='"+id+"'");
					return 1;
				}
				
			}
		}catch(Exception ex) {
			System.out.println("[UserRequestHandler] - failed to instructorExists");
			ex.printStackTrace();
			return -1;
		}
		
	}

}
