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
	
	
	//make the instructor active and insert to visitor table
	public static String activated(String id) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return "fail DB";
		}

		Visitor v;
		try {
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM instructor WHERE ID='"+id+"'");//check if instructor in the table
			if (rs.next()) {
				String str = "UPDATE instructor SET activated=? WHERE ID=? ";
				PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);	
		        ps.setString(1, "1");
		        ps.setString(2, id);
		        ps.executeUpdate(); // Execute the update statement
				v=new Visitor(rs.getString(1),rs.getString(2),rs.getString(3),"1");
				rs.close();
				if(instructorToUser(v))
					return "regest";
				return " error Visitor insert";
			}
			else {
				return "can not regest";
			}
		}catch(Exception ex) {
			System.out.println("[UserRequestHandler] - failed to instructorExists");
			ex.printStackTrace();
			return "fail in catch";
		}
		
	}
	
	
	
	//insert instructor to visitor table
	public static boolean instructorToUser(Visitor v) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM visitors WHERE ID='"+v.getId()+"'");
			if (!rs.next()) {//if the instructor not in the table
				 int s = st.executeUpdate("INSERT INTO visitors (ID, Email, Telephone, isInstructor) " +
			                    "VALUES ('" + v.getId() + "','" + v.getEmail() + "','" + v.getPhone() + "', 1)");
				 rs.close();
				 if (s>0)
					 return true;
				 return false;
			}
			else {
				return false;
			}	
		}catch(Exception ex) {
				System.out.println("[UserRequestHandler] - failed to instructorExists");
				ex.printStackTrace();
				return false;
			}
			
	}
	

	
	//exit/enter from the park
	public static boolean changeCurrent(String park, int num,String s) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT currentAmount FROM parks WHERE ParkName='"+park+"'");
			if (!rs.next()) {
				return false;
			}
				int newCurrent;
				int oldNumber=rs.getInt("currentAmount");
				if(s.contentEquals("+"))
					newCurrent=oldNumber+num;
				else if(s.contentEquals("-"))
					newCurrent=oldNumber-num;
				else return false;
				String str = "UPDATE parks SET currentAmount=? WHERE ParkName=? ";
				PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);	
		        ps.setInt(1, newCurrent);
		        ps.setString(2, park);
		        int rowsAffected = ps.executeUpdate(); // Execute the update query
		        return rowsAffected > 0; // Return true if the update was successful
				//return true;
			}catch(Exception ex) {
				System.out.println("[UserRequestHandler] - failed to checkEntering");
				ex.printStackTrace();
				return false;
			}
	}
	
	
	/*
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
				String str = "UPDATE parks SET currentAmount=? WHERE ParkName=? ";
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
		
	}*/
}
