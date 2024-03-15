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
		User u = userExists(k.getID(),k.getPassword());
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
			ResultSet r1 = st.executeQuery("SELECT * FROM reservations WHERE ReservationID='"+b1.getId()+"'");
			if (!r1.next()) {
				System.out.println("[UserRequestHandler] - r1 result set was empty");
				return null;
			}

			//r1(2)=numberOfVisitor,r2(4)=check if the instructor,r1(11)=check if invited ,r1(12) if payed
			b = new Bill(r1.getString(1),r1.getString(2),r1.getString(12).equals("1"),r1.getString(13).equals("1"));
			r1.close();
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
	
	

}
