package main.handlers;

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
	
}
