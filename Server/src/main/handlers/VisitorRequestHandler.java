package main.handlers;

import java.sql.ResultSet;
import java.sql.Statement;
import entities.Order;

import entities.Visitor;
import main.Constants;
import main.MainServer;

public class VisitorRequestHandler {

	public static Visitor handleValidateRequest(String id) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		Visitor v = visitorExists(id);
		if (v == null)
			v = new Visitor(id);
		else
			v.setFoundInDB(true);
		return v;
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
			v = new Visitor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4).equals("1"));
			rs.close();
		}catch(Exception ex) {
			System.out.println("[VisitorRequestHandler] - failed to execute query");
			ex.printStackTrace();
			return null;
		}
		return v;
	}

	public static Order handleMakeReservationRequest(Order o) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		try{
			Statement st = MainServer.dbConnection.createStatement();
		    int s = st.executeUpdate( "INSERT INTO reservations (Type,NumberOfVisitors,ReservationDate,Hour,Minute,Park,Telephone,Email,isConfirmed)" + " VALUES ('"+o.getOrderType()+"', '"+o.getNumOfVisitors()+"','"+o.getDate()+"' ,'"+o.getHour()+"', '"+o.getMinute()+"', '"+o.getParkName()+"', '"+o.getPhone()+"', '"+o.getEmail()+"',FALSE)");
		    if(s<=0)return null;
		}catch(Exception ex) {
			System.out.println("[VisitorRequestHandler] - failed to execute query");
			ex.printStackTrace();
			return null;}
		return o;
			
	}

}
