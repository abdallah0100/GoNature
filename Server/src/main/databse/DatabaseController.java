package main.databse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.MainServer;

public class DatabaseController {

	private static int COLS_IN_ORDER = 6;
	
	//a function that returns an array list of string array, each strings array represents a row from the table
	public static ArrayList<String[]> getAllOrders() {
		String str = "SELECT * FROM gonature.Order;";
		
		Statement stmt;
		try 
		{
			stmt = MainServer.dbConnection.createStatement();
			ResultSet rs = stmt.executeQuery(str);
			//an arraylist of String arrays, each array represents a row from the database table
			ArrayList<String[]> result = new ArrayList<String[]>();
	 		while(rs.next())
	 		{
	 			String[] row = new String[COLS_IN_ORDER];
	 			for (int i = 1; i <= COLS_IN_ORDER; i++) 
	 				row[i-1] = rs.getString(i);//assigning to each array cell its respective value from the table
	 			
	 			result.add(row);//adding to the result
			} 
			rs.close();
			return result;
		} catch (SQLException e) {
			System.out.println("[DatabaseController] - Error getting all orders");
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean updateOrder(ArrayList<String> order) {
		try {
			String str = "UPDATE gonature.order SET ParkName=?, TelephoneNumber=? WHERE OrderNumber=?;";
			PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);
			//[reqType(0),ParkName(1),OrderNumber(2),TimeOfVisit(3),NumberOfVisitors(4),TelephoneNumber(5),Email(6)]
			ps.setString(1, order.get(1));	//ParkName
			ps.setString(2, order.get(5));	//TelephoneNumber
			ps.setString(3, order.get(2));	//OrderNumber
			return ps.executeUpdate()>0;
		} catch (SQLException e) {
			System.out.println("[DatabaseController] - error updating Orders");
			e.printStackTrace();
			return false;
		}
	}
	
}
