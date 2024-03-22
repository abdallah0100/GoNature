package main.handlers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
			
			Statement stat = MainServer.dbConnection.createStatement();
		    String checkVisitorQuery = "SELECT * FROM visitors WHERE ID = '" + o.getVisitorID() + "'";
		    ResultSet rs = stat.executeQuery(checkVisitorQuery);
		    boolean visitorExists = rs.next(); 
		    if (!visitorExists) {
		         String insertVisitorQuery = "INSERT INTO visitors (ID, Email, Telephone, isInstructor) VALUES ('" +
		                 o.getVisitorID() + "', '" + o.getEmail() + "', '" + o.getPhone() + "', '0')";
		         stat.executeUpdate(insertVisitorQuery);
		    }
			int isConfirmed = o.getIsConfirmed() ? 1 : 0;
	        int invitedInAdvance = o.getInvitedInAdvance() ? 1 : 0;
	        int payed = o.getIsPayed() ? 1 : 0;
			Statement st = MainServer.dbConnection.createStatement();
		    int s = st.executeUpdate( "INSERT INTO reservations (Type,NumberOfVisitors,ReservationDate,Hour,Minute,Park,Telephone,Email,visitorID,isConfirmed,invitedInAdvance,payed, processed)" + 
			" VALUES ('"+o.getOrderType()+"', '"+o.getNumOfVisitors()+"','"+o.getDate()+"' ,'"+o.getHour()+"', '"+o.getMinute()+"', '"+o.getParkName()+"', '"+o.getPhone()+"', '"+o.getEmail()+"','"+o.getVisitorID()+"','"+isConfirmed+"','"+invitedInAdvance+"','"+payed+"', '-1')");
		    if(s<=0)return null;
		}catch(Exception ex) {
			System.out.println("[VisitorRequestHandler] - failed to execute query");
			ex.printStackTrace();
			return null;}
		return o;
			
	}
	
	public static Order[] handleShowReservationsRequest(String id) {
		 List<Order> orders = new ArrayList<>();
		    ResultSet rs = null;
		    try {           
		        String query = "SELECT Type, NumberOfVisitors, ReservationDate, Hour, Minute, Park, Telephone, Email, ReservationID, isConfirmed, InvitedInAdvance, payed " +
		                       "FROM reservations " +
		                       "WHERE visitorID = ?";
		        PreparedStatement ps = MainServer.dbConnection.prepareStatement(query);
		        ps.setString(1, id);
		        rs = ps.executeQuery();
		        while (rs.next()) {
		            String type = rs.getString(1);
		            int numberOfVisitors = rs.getInt(2);
		            String date = rs.getString(3);
		            String hour = rs.getString(4);
		            String minute = rs.getString(5);
		            String park = rs.getString(6);
		            String telephone = rs.getString(7);
		            String email = rs.getString(8);
		            int reservationID = rs.getInt(9);
		            boolean isConfirmed = rs.getBoolean(10);
		            boolean invitedInAdvance = rs.getBoolean(11);
		            boolean payed = rs.getBoolean(12);
		            orders.add(new Order(type, numberOfVisitors, date, hour, minute, park, telephone, email, reservationID, isConfirmed, invitedInAdvance, payed));  
		        }
		        rs.close();
		    } catch (SQLException e) {
		        e.printStackTrace();         
		    }
		    Order[] ordersArray = new Order[orders.size()];
		    ordersArray = orders.toArray(ordersArray);
		    
		    return ordersArray;
			
		}
	public static Order handleUpdateReservationRequest(Order o) {
		 try {
        String query = "UPDATE reservations " +
                       "SET Type = ?, NumberOfVisitors = ?, ReservationDate = ?, Hour = ?, Minute = ?, Park = ?, Telephone = ?, Email = ?, isConfirmed = ?, InvitedInAdvance = ?, payed = ? " +
                       "WHERE ReservationID = ?";
        PreparedStatement ps = MainServer.dbConnection.prepareStatement(query);
        ps.setString(1, o.getOrderType());
        ps.setInt(2, o.getNumOfVisitors());
        ps.setString(3, o.getDate());
        ps.setString(4, o.getHour());
        ps.setString(5, o.getMinute());
        ps.setString(6, o.getParkName());
        ps.setString(7, o.getPhone());
        ps.setString(8, o.getEmail());
        ps.setBoolean(9, o.getIsConfirmed());
        ps.setBoolean(10, o.getInvitedInAdvance());
        ps.setBoolean(11, o.getIsPayed());
        ps.setInt(12, Integer.parseInt(o.getOrderID()));
        int rowsUpdated = ps.executeUpdate();
        if (rowsUpdated == 1) {
            return o;}
		} catch (SQLException e) {
        e.printStackTrace();} 
         return null;
    }
	
	public static boolean admitReservation(Order o) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
        try {
        	Statement st = MainServer.dbConnection.createStatement();
        	String str = "UPDATE reservations SET isConfirmed='1' WHERE ReservationID='"+o.getOrderID()+"'";
        	return st.executeUpdate(str) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
         }		
	}
		
}
