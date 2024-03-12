package main.handlers;

import java.sql.ResultSet;
import java.sql.Statement;

import entities.Visitor;
import main.Constants;
import main.MainServer;

public class VisitorRequestHandler {

	public static Visitor handleValidateRequest(String id) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		return visitorExists(id);
	}
	
	public static Visitor visitorExists(String id){
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		Visitor v;
		try {
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM visitors WHERE ID='"+id+"'");
			if (!rs.next()) {
				System.out.println("[VisitorRequestHandler] - result set was empty");
				return null;
			}
			v = new Visitor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4).equals("1"));;
			rs.close();
		}catch(Exception ex) {
			System.out.println("[VisitorRequestHandler] - failed to execute query");
			ex.printStackTrace();
			return null;
		}
		return v;
	}
	
}
