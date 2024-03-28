package main.handlers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import entities.AvailablePlace;
import entities.Order;
import entities.Park;
import entities.Report;
import main.Constants;
import main.MainServer;

/**
 * Handles various park-related requests, such as fetching park details, updating park information,
 * managing park capacity and reservations, and providing available visiting times based on park capacity.
 */
public class ParkRequestHandler {

	/**
	 *  Fetches all parks from the database.
	 * @return An array of Park objects containing details for each park.
	 */
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
	
	/**
	 * Updates a specific variable of a park.
	 * @param p The park object containing the variable to update and its new value.
	 * @return True if the update is successful, false otherwise.
	 */
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

	/**
	 * Checks if a specific report request has already been made if not insert the request change to requestchange table.
	 * @param r The report to check.
	 * @return True if the report request exists, false otherwise.
	 */
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
	
	/**
	 * Inserts or updates a change request for a park variable to requestchange table.
	 * 
	 * @param r The report containing the change request details.
	 * @return True if the operation is successful, false otherwise.
	 */
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
	
	/**
	 * Update 2 time or more park manager ask to update value second time or more 
	 * @param r The report containing the change request details.
	 * @return True if the operation is successful, false otherwise.
	 */
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
	
	/**
	 * Updates the number of visitors currently in the park based on reservations.
	 * 
	 * @param o The order affecting the visitor count.
	 * @param num The number to add to the current visitor count.
	 * @return True if the update is successful, false otherwise.
	 */
	public static boolean updateCurrentAmoun(Order o,int num) {
			if (MainServer.dbConnection == null) {
				System.out.println(Constants.DB_CONNECTION_ERROR);
				return false;
			}
			// String to know where i have to change the amount
			String v;
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
			        // Execute the update query
			        int rowsAffected = ps.executeUpdate();
			        // Return true if the update was successful 
			        return rowsAffected > 0; 
					//return true;
				}catch(Exception ex) {
					System.out.println("[UserRequestHandler] - failed to checkEntering");
					ex.printStackTrace();
					return false;
				}
	}
	
	/**
	 * Fetches detailed data for a specific park.
	 * 
	 * @param name The name of the park.
	 * @return A Park object with the park's details.
	 */
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
	
	/**
	 * Updates the current visitor count with reservation in the specified park.
	 * 
	 * @param park The name of the park where the update is to be made.
	 * @param num The number to add to the current visitor count with reservation.
	 * @return True if the update is successful, indicating the new visitor count is set in the database; false otherwise.
	 */
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
	
	/**
	 * Checks if a park has reached its maximum capacity including both visitors with and without reservations.
	 * 
	 * @param parkName The name of the park to check for capacity.
	 * @return True if the total number of visitors (with and without reservations) equals or exceeds the park's capacity; false otherwise
	 */
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
	
	/**
	 * Creates a record in the 'full_park' table indicating that the park has reached its full capacity at a specific time.
	 * @param order The order object containing details such as park name, date, and time when the park reached full capacity.
	 */
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
	
	/**
	 * Gets all orders of a given park.
	 * 
	 * @param parkName The park name.
	 * @return An array of Order containing all orders in the park.
	 */
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
	
	/**
	 * Checks if the park has space for a new reservation.
	 * 
	 * @param o The order representing the reservation request.
	 * @return True if there is space available, false otherwise.
	 */
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
		
		return reserved + o.getNumOfVisitors() <= (parkMaxCapacity - p.getGap());
	}
	
	/**
	 * Checks if the park has space for a new reservation.
	 * 
	 * @param o The order representing the reservation request.
	 * @param parkOrders All orders of the park in the reservation details.
	 * @param p The park that the order reservation request was made for.
	 * @return True if there is space available, false otherwise.
	 */
	public static boolean parkHasSpace(Order o, Order[] parkOrders, Park p) {
		if (parkOrders == null)
			return true;

		if (p == null)
			return false;
		
		int parkMaxCapacity = p.getMaxCapacity();
		int reserved = 0;
		
		for (Order o1 : parkOrders)
			if (o.overlappingOrders(o1, p.getEstimatedTime()))
				reserved += o1.getNumOfVisitors();
		
		return reserved + o.getNumOfVisitors() <= (parkMaxCapacity - p.getGap());
	}
	
	/**
	 * Calculates the total number of reserved places in the park.
	 * 
	 * @param parkOrders The list of all orders for the park.
	 * @return The total number of places reserved.
	 */
	public static int getReservedPlaces(Order[] parkOrders) {
		int reserved = 0;	
		for (Order o1 : parkOrders)
			reserved += o1.getNumOfVisitors();
		return reserved;
	}
	
	/**
	 * Provides available visiting times based on park capacity and existing reservations.
	 * 
	 * @param o The order containing the visiting details.
	 * @return An array of AvailablePlace objects representing available visiting times.
	 */
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
		
		Park p = ParkRequestHandler.getParkData(o.getParkName());
		Order[] parkOrders = getAllParkOrders(o.getParkName());
		
		if (o.getNumOfVisitors() > p.getMaxCapacity() - p.getGap())
			return new AvailablePlace[0];//new empty array
		
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
				if (parkHasSpace(temp, parkOrders, p)) {
					arr.add(new AvailablePlace(o.getParkName(), date, hour+":00"));
					daySuggestions++;
					numOfSuggestions++;
				}
				hour++;
			}
		}
		return (AvailablePlace[])arr.toArray(new AvailablePlace[arr.size()]);
	}
}
