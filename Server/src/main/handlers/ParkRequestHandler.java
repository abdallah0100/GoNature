package main.handlers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Order;
import entities.Park;
import entities.Report;
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
	
	
	
	
	
	
	// insert the request change to requestchange table
	public static boolean checkReportRequest(Report r) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM requested_changes WHERE parkName ='"+r.getPark()+"'AND variableToChange='"+r.getVariableToChange()+"'");
			if (!rs.next()) {
				return false;//the report in the table
			}
			return true;
		}catch(Exception ex) {
				System.out.println("[ParkRequestHandler] - failed to insert to requested_changes)");
				ex.printStackTrace();
				return false;
		}
	}	
	
	
	// insert the request change to requestchange table
	public static boolean reqToChange(Report r) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {
			String str = "INSERT INTO requested_changes (parkName, madeBy, variableToChange, newValue) VALUES "
					+ "('"+r.getPark()+"', '"+r.getMadeBy()+"','"+ r.getVariableToChange()+"' , '"+r.getNewValue()+"' )";
					PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);
					if(ps.executeUpdate() > 0) {
						return true; 
					}
					else {
						return false;
					}
		}catch(Exception ex) {
				System.out.println("[ParkRequestHandler] - failed to insert to requested_changes)");
				ex.printStackTrace();
				return false;
		 }
	}	
	
	
	
	
	//update 2 time or more park manager ask to update value second time or more 
	public static boolean reqToChange2(Report r) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {
			String str = "UPDATE requested_changes SET newValue=? WHERE ParkName=? AND variableToChange=? ";
			PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);
			ps.setInt(1, r.getNewValue());
			ps.setString(2, r.getPark());
			ps.setString(3, r.getVariableToChange());
			return ps.executeUpdate() > 0;
		}catch(Exception ex) {
			System.out.println("[ParkRequestHandler] - failed to update park variable");
			ex.printStackTrace();
			return false;
		}
	}
	
	
	//o=Order ,num=num of the visitor maybe for enter or exit ,coulm to know where to update (with/without reservation)
	public static boolean updateCurrentAmoun(Order o,int num) {
			if (MainServer.dbConnection == null) {
				System.out.println(Constants.DB_CONNECTION_ERROR);
				return false;
			}
			String v;//string to know where i have to change the amount
			if(o.getInvitedInAdvance())
				v="visitorsWithOrder";
			else
				v="visitorsWithoutOrder";
			try {
				Statement st = MainServer.dbConnection.createStatement();
				ResultSet rs = st.executeQuery("SELECT "+v+" FROM parks WHERE ParkName='"+o.getParkName()+"'");
				if (!rs.next()) {
					return false;
				}
					int newCurrent;
					int oldNumber=rs.getInt(v);
					newCurrent=oldNumber+num;
					String str = "UPDATE parks SET " + v +" = ? WHERE ParkName = ?";
					PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);	
			        ps.setInt(1, newCurrent);
			        ps.setString(2, o.getParkName());
			        int rowsAffected = ps.executeUpdate(); // Execute the update query
			        return rowsAffected > 0; // Return true if the update was successful
					//return true;
				}catch(Exception ex) {
					System.out.println("[UserRequestHandler] - failed to checkEntering");
					ex.printStackTrace();
					return false;
				}
		}
	
	public static Park getParkData(String name) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		try {
			String str = "SELECT * FROM parks WHERE ParkName='"+name+"'";
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery(str);
			if (!rs.next()) {
				System.out.println("[ParkRequestHandler] - park " + name + " doesn't exist");
				return null;
			}
			Park p = new Park(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
			return p;
		}catch(Exception ex) {
			System.out.println("[ParkRequestHandler] - failed to update park variable");
			ex.printStackTrace();
			return null;
		}
	}
	
	
	public static boolean processedResData(String park, int num) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT visitorsWithOrder FROM parks WHERE ParkName='"+park+"'");
			if (!rs.next()) {
				return false;
			}
				int newCurrent;
				int oldNumber=rs.getInt("visitorsWithOrder");
				newCurrent=oldNumber+num;
				String str = "UPDATE parks SET visitorsWithOrder=? WHERE ParkName=? ";
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
	
	public static boolean parkReachedMaxCapacity(String parkName) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR); 
			return false;
		}
		try {
			String str = "SELECT * FROM parks WHERE ParkName='"+parkName+"'";
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery(str);
			if (!rs.next()) {
				System.out.println("[ParkRequestHandler] - result set was empty");
				return false;
			}
			int visitorsWithOrder = rs.getInt("visitorsWithOrder");
			int visitorsWithoutOrder = rs.getInt("visitorsWithoutOrder");
			int capacity = rs.getInt("capacity");
			return visitorsWithOrder + visitorsWithoutOrder >= capacity;
		}catch(Exception ex) {
				System.out.println("[ParkRequestHandler] - failed to execute query");
				ex.printStackTrace();
				return false;
		}
	}
	
	public static void createFullParkInstance(Order order) {
		
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR); 
			return;
		}
		try {
			
			String str = "INSERT INTO full_park (parkName, year, month, day, hour) VALUES(?,?,?,?,?)";
			PreparedStatement pr = MainServer.dbConnection.prepareStatement(str);
			pr.setString(1, order.getParkName());
			pr.setString(2, order.getYear() + "");
			pr.setString(3, order.getMonth() + "");
			pr.setString(4, order.getDay() + "");
			pr.setString(5, order.getHour());
			if (!(pr.executeUpdate() > 0))
				System.out.println("[ParkRequestHandler] - Error creating new full park instance");
		}catch(Exception ex) {
				System.out.println("[ParkRequestHandler] - failed to execute query");
				ex.printStackTrace();
				return;
		}
	}
	
}
