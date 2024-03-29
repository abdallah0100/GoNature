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

/**
 * Handles user-related requests including login, user validation, bill validation,
 * instructor activation, and instructor status checks. It interacts with the database
 * to perform these operations and provides necessary functionalities for user management.
 */
public class UserRequestHandler {
	
	/**
	 * Handles login requests by validating user password and user name against the database.
	 * 
	 * @param k User object containing the user name and password for validation.
	 * @return User object populated with user details from the database if credentials are valid; otherwise null.
	 */
	public static User handleLogInRequest(User k) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		User u = userExists(k.getUsername(),k.getPassword());
		return u;

	}
	
	/**
	 * Validates if a user exists in the database with the given user name and password.
	 * 
	 * @param userName The user name of the user.
	 * @param password The password of the user.
	 * @return User object if the user exists and credentials match; otherwise null.
	 */
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
	
	/**
	 * Checks if a bill exists in the database based on a given Bill object's ID.
	 * 
	 * @param b1 Bill object containing the ID to check for in the database.
	 * @return Bill object populated with bill details if exists; otherwise null.
	 */
	public static Bill billExists(Bill b1){
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		Bill b;
		try {
			Statement st = MainServer.dbConnection.createStatement();
			//Select the reservation to calculate bill based on its details
			ResultSet rs = st.executeQuery("SELECT * FROM reservations WHERE ReservationID='"+b1.getId()+"' ");
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
	
	/**
	 * Activates an instructor by updating their status in the database and inserting them into the visitor table if necessary.
	 * 
	 * @param id The ID of the instructor to be activated.
	 * @return A string indicating the result of the operation.
	 */
	public static String activated(String id) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return "fail DB";
		}

		Visitor v;
		try {
			Statement st = MainServer.dbConnection.createStatement();
			// Check if instructor in the table
			ResultSet rs = st.executeQuery("SELECT * FROM instructor WHERE ID='"+id+"' ");
			if (rs.next()) {
				String str = "UPDATE instructor SET activated=? WHERE ID=? ";
				PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);	
		        ps.setString(1, "1");
		        ps.setString(2, id);
		        // Execute the update statement
		        int x= ps.executeUpdate(); 
		        // If updated 
				if(x>0)
				{
					v=new Visitor(rs.getString(1),rs.getString(2),rs.getString(3),"1");
					rs.close();
					if(instructorToUser(v))
						return "regest";
				return " error Visitor insert";
				}
			}
			else {
				return "can not regest";
			}
		}catch(Exception ex) {
			System.out.println("[UserRequestHandler] - failed to instructorExists");
			ex.printStackTrace();
			return "fail in catch";
		}
		return  "fail";
		
	}
	
	/**
	 * Inserts an instructor into the visitor table or updates their record if they already exist as a visitor.
	 * 
	 * @param v Visitor object representing the instructor.
	 * @return True if the operation is successful; otherwise false.
	 */
	public static boolean instructorToUser(Visitor v) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM visitors WHERE ID='"+v.getId()+"'");
			// If the instructor not in the table
			if (!rs.next()) {
				 int s = st.executeUpdate("INSERT INTO visitors (ID, Email, Telephone, isInstructor) " +
			                    "VALUES ('" + v.getId() + "','" + v.getEmail() + "','" + v.getPhone() + "', 1)");
				 rs.close();
				 if (s>0)
					 return true;
				 return false;
			}
			else {
				// Update to instructor in visitor table
				String str = "UPDATE visitors SET isInstructor=? WHERE ID=? ";
				PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);	
		        ps.setString(1, "1");
		        ps.setString(2, v.getId());
		        // Execute the update statement
		        int x= ps.executeUpdate(); 
		        if(x>0)
		        	return true;
		        return false;
			}	
		}catch(Exception ex) {
				System.out.println("[UserRequestHandler] - failed to instructorExists");
				ex.printStackTrace();
				return false;
			}	
	}
	
	/**
	 * Checks if a given ID belongs to an activated instructor.
	 * 
	 * @param id The ID to check.
	 * @return True if the ID belongs to an activated instructor; otherwise false.
	 */
	public static boolean isInstructor(String id) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM instructor WHERE ID='"+id+"' AND activated='1' ");
			if (!rs.next()) {
				System.out.println("[UserRequestHandler] - empty");
				return false;
			}
			rs.close();
			return true;
		}catch(Exception ex) {
			System.out.println("[UserRequestHandler] - failed to execute query");
			ex.printStackTrace();
			return false;
		}		
	}
}
