package main.handlers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Park;
import main.Constants;
import main.MainServer;

public class ParkRequestHandler {

	public static Park[] getAllParks() {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		ArrayList<Park> list = new ArrayList<>();
		try {
			Statement st = MainServer.dbConnection.createStatement();
			String str = "SELECT * FROM parks";
			ResultSet rs = st.executeQuery(str);
			Park p;
			while(rs.next()) {
				p = new Park(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
				list.add(p);
			}
			
		}catch(Exception ex) {
			System.out.println("[ParkRequestHandler] - failed to execute query");
			ex.printStackTrace();
			return null;
		}
		return (Park[])list.toArray(new Park[list.size()]);
	}
	
	public static boolean updateParkVariable(Park p) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {
			String str = "UPDATE parks SET "+p.getVarbToUpdate()+"=? WHERE ParkName=?";
			PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);
			ps.setInt(1, p.getNewValue());
			ps.setString(2, p.getParkName());
			return ps.executeUpdate() > 0;
		}catch(Exception ex) {
			System.out.println("[ParkRequestHandler] - failed to update park variable");
			ex.printStackTrace();
			return false;
		}
	}
	
	
	
	
	
	
	// insert the request change
	public static String reqToChange(String []s) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return Constants.DB_CONNECTION_ERROR;
		}
		try {
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM requested_changes WHERE parkName ='"+s[0]+"'AND variableToChange='"+s[2]+"'");
			if (!rs.next()) {
				String str = "INSERT INTO requested_changes (parkName, madeBy, variableToChange, newValue, status) VALUES "
						+ "('"+s[0]+"', '"+s[1]+"','"+ s[2]+"' , '"+s[3]+"','0')";
				PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);
				if(ps.executeUpdate() > 0)
					return "inserted";			
			}
			else {
				rs.close();
				return "exist";
			}
			return "fail";
		}catch(Exception ex) {
				System.out.println("[ParkRequestHandler] - failed to insert to requested_changes)");
				ex.printStackTrace();
				return "fail in catch";
		}
	}	
	
	
	
	
	
	//update 2 time or more park manager ask to update value second time or more 
	public static boolean reqToChange2(String []s) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {
			String str = "UPDATE requested_changes SET newValue=? WHERE ParkName=? AND variableToChange=? ";
			PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);
			ps.setString(1, s[3]);
			ps.setString(2, s[0]);
			ps.setString(3, s[2]);
			return ps.executeUpdate() > 0;
		}catch(Exception ex) {
			System.out.println("[ParkRequestHandler] - failed to update park variable");
			ex.printStackTrace();
			return false;
		}
	}
	
	
	
	
	public static boolean updateCurrentAmoun(String park, int num) {
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
					newCurrent=oldNumber+num;
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
	
	
}
