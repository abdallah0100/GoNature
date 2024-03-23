package main.handlers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

import entities.AvailablePlace;
import entities.Order;
import entities.Park;
import main.Constants;
import main.MainServer;

public class ReservationRequestHandler {
	
	public static Order getReservationById(String []s,String tableName) {
		//s[0]  ClientController.connectedUser.getParkName s[1] resevation id
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		try {    
				Order o;
				String query = "SELECT * FROM " + tableName + " WHERE ReservationID = ?";
		        PreparedStatement ps = MainServer.dbConnection.prepareStatement(query);
		        ps.setString(1, s[1]);
		        ResultSet rs = ps.executeQuery();
		        if (!rs.next()) {
		            return null;
		        }
				 o=new Order(rs.getString("Type"),rs.getInt("NumberOfVisitors"),rs.getString("ReservationDate"),rs.getString("Hour"),
						rs.getString("Minute"),rs.getString("Park"),rs.getString("Telephone"),rs.getString("Email"),
						rs.getInt("ReservationID"),rs.getString("visitorID"),
						rs.getBoolean("isConfirmed"),rs.getBoolean("InvitedInAdvance"),rs.getBoolean("payed"),rs.getString("processed"));
				  String date = o.getDate();
				  LocalDate parsedDate = LocalDate.parse(date);
				 if(s[0].equals(o.getParkName()) && o.getHour().equals(LocalTime.now().getHour() + "")  && LocalDate.now().equals(parsedDate)) //the same time and same park
					 return o;//reservation data
				 else 
					 return null;
		}catch(Exception ex) {
			System.out.println("[UserRequestHandler] - failed to checkEntering");
			ex.printStackTrace();
			return null;
		}
	}

	
	
	//insert copy to processedres reservation
	public static boolean enterProcessed(Order o) {
			if (MainServer.dbConnection == null) {
				System.out.println(Constants.DB_CONNECTION_ERROR);
				return false;
			}
			try {  	
					String str = "INSERT INTO processedRes (NumberOfVisitors,Park,ReservationID,enterHour,enterMin,exitHour,exitMin,date,reservationType)"
							+ " VALUES (?,?,?,?,?,?,?,?,?) ";
					PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);	
	    		ps.setInt(1,o.getNumOfVisitors());//NumberOfVisitors
			   	ps.setString(2,o.getParkName());//Park
			   	ps.setString(3,o.getOrderID());//ReservationID
			   		
			    ps.setInt(4,LocalTime.now().getHour());//enterHour
			   	ps.setInt(5,LocalTime.now().getMinute());//enterMin
			   		
			   	ps.setInt(6,0);//exitHour
			    ps.setInt(7,0);//exitMin
			        
			   	ps.setString(8,o.getDate());//date
			   	ps.setString(9,o.getOrderType());//reservationType
			      int rowsAffected = ps.executeUpdate(); 
			       return rowsAffected > 0; // Return true if the INSERT was successful
					//return true;
				}catch(Exception ex) {
					System.out.println("[UserRequestHandler] - failed to checkEntering");
					ex.printStackTrace();
					return false;
				}
		}

	
	
	// change  processed in reservation table to 0/1 (know if the reservation )
		public static boolean updateStatus(String tableName,String id ,int n) {
			if (MainServer.dbConnection == null) {
				System.out.println(Constants.DB_CONNECTION_ERROR);
				return false;
			}
			try {    	
					String str = "UPDATE "+tableName+" SET processed=? WHERE ReservationID = ?";
	    			PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);	
	    			ps.setInt(1, n);
	    			ps.setString(2, id);
			        int rowsAffected = ps.executeUpdate(); 
			        return rowsAffected > 0; // Return true if the update was successful
					//return true;
				}catch(Exception ex) {
					System.out.println("[UserRequestHandler] - failed to checkEntering");
					ex.printStackTrace();
					return false;
				}
		}
		

	
	//change
	public static boolean exitProcessed(String id)
	{
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {    	
				String str = "UPDATE processedres SET exitHour=?, exitMin=? WHERE ReservationID = ?";
    			PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);	
			    ps.setInt(1,LocalTime.now().getHour());//exitHour
			   	ps.setInt(2,LocalTime.now().getMinute());//exitMin
    			ps.setString(3, id);
		        int rowsAffected = ps.executeUpdate(); 
		        return rowsAffected > 0; // Return true if the update was successful
				//return true;
			}catch(Exception ex) {
				System.out.println("[UserRequestHandler] - failed to checkEntering");
				ex.printStackTrace();
				return false;
			}
	}
	
	// if reservation deleted 
	public static boolean deleteReservation(String tableName,String id) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {    	
				String str = "DELETE FROM " + tableName + " WHERE ReservationID = ?";
    			PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);	
    			ps.setString(1, id);
		        int rowsAffected = ps.executeUpdate(); 
		        return rowsAffected > 0; // Return true if the Delete was successful
				//return true;
			}catch(Exception ex) {
				System.out.println("[UserRequestHandler] - failed to checkEntering");
				ex.printStackTrace();
				return false;
			}
	}
	public static boolean addToCanceledReports(Order o) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {    	
			String str = "INSERT INTO cancellationsReports (Type, Date, Park, reservationId, ReservationIdCancelled, ReservationIdNotActivated) "
					+ "VALUES(?,?,?,?,?,?)";
			PreparedStatement pr = MainServer.dbConnection.prepareStatement(str);
			pr.setString(1, o.getOrderType());
			pr.setString(2, o.getDate());
			pr.setString(3, o.getParkName());
			pr.setString(4, o.getOrderID());
			pr.setString(5, o.isCancelRequest() ? "Yes" : "No");
			pr.setString(6, o.isCancelRequest() ? "No" : "Yes");
			
			return pr.executeUpdate() > 0;
		}catch(Exception ex) {
			System.out.println("[UserRequestHandler] - failed to checkEntering");
			ex.printStackTrace();
			return false;
		}
	}
	
	public static Order[] getAllParkOrders(String parkName) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR); 
			return null;
		}
		ArrayList<Order> orders = new ArrayList<>();
		try {
			String str = "SELECT * FROM reservations WHERE Park='"+parkName+"'";
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery(str);
			
			while(rs.next()) {
				Order o = new Order(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10),
						rs.getString(11).equals("1"), rs.getString(12).equals("1"), rs.getString(13).equals("1"),rs.getString(14));
				orders.add(o);
			}
		}catch(Exception ex) {
			System.out.println("[ReservationRequestHandler] - failed to execute query");
			ex.printStackTrace();
			return null;
		}
		return (Order[])orders.toArray(new Order[orders.size()]);
	}
	
	public static boolean parkHasSpace(Order o) {
		Order[] parkOrders = getAllParkOrders(o.getParkName());
		if (parkOrders == null)
			return true;
		Park p = ParkRequestHandler.getParkData(o.getParkName());
		if (p == null)
			return false;
		
		int parkMaxCapacity = p.getMaxCapacity();
		int reserved = 0;
		
		for (Order o1 : parkOrders)
			if (o.overlappingOrders(o1, p.getEstimatedTime()))
				reserved += o1.getNumOfVisitors();
		
		return reserved + o.getNumOfVisitors() <= parkMaxCapacity;
	}
	
	public static AvailablePlace[] getAvailableTimes(Order o) {
		ArrayList<AvailablePlace> arr = new ArrayList<>();
		//string = {year/month/day, avbl_hour}
	
		Calendar rightNow = Calendar.getInstance();
		int month = rightNow.get(Calendar.MONTH) + 1;
		int year = rightNow.get(Calendar.YEAR);
		int day = rightNow.get(Calendar.DAY_OF_MONTH);
		
		int numOfSuggestions = 0;
		int daySuggestions = 0;
		int hour;
		Order temp;
		String date;
		
		while (numOfSuggestions < 10) {
			if (day + 1 > 30) {
				day = 1;
				if (month + 1 > 12) {
					year++;
					month = 1;
				}else{
					month++;
				}
			}else
				day++;

			hour = 8;//suggestion starting hour
			daySuggestions = 0;
			while (daySuggestions < 3 && hour <= 20) {//20 is suggestion closing hour
				date = year+"-"+month+"-"+day;
				temp = new Order(o.getParkName(), date, hour+"", "00", o.getNumOfVisitors(), o.getOrderType());
				if (parkHasSpace(temp)) {
					arr.add(new AvailablePlace(o.getParkName(), date, hour+":00"));
					daySuggestions++;
					numOfSuggestions++;
				}
				hour++;
			}
		}
		return (AvailablePlace[])arr.toArray(new AvailablePlace[arr.size()]);
	}
	
	
	
	
	//check if waiting list has the same order for the same person
	public static boolean inWaitngList (Order o) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR); 
			return false;
		}
		try {
			String str = "SELECT * FROM waiting_list WHERE"
					+ " Type = '"+o.getOrderType()+"' AND"
					+" NumberOfVisitors = '"+o.getNumOfVisitors()+"' AND"
					+" ReservationDate = '"+o.getDate()+"' AND" 
					+" Hour = '"+o.getHour()+"' AND"
					+" Minute = '"+o.getMinute()+"' AND"
					+" Park = '"+o.getParkName()+"' AND"
					+" visitorID = '"+o.getVisitorID()+"'";	
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery(str);
			return rs.next();
		}catch(Exception ex) {
				System.out.println("[ReservationRequestHandler] - failed to execute query");
				ex.printStackTrace();
				return false;
		}
	}
	
}
