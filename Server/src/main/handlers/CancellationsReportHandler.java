package main.handlers;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import entities.CancelledReservation;
import main.Constants;
import main.MainServer;

/**
 * The CancellationsReportHandler class is responsible for retrieving data related to cancelled reservations
 * from the database. It provides functionalities to query the cancellationsReports table based on specific conditions.
 */
public class CancellationsReportHandler {

	/**
	 * Retrieves an array of CancelledReservation objects that meet a specified condition, such as being cancelled or not activated.
     * The method queries the cancellationsReports table and filters the records based on the provided condition.
     * 
	 * @param condition A string specifying the condition to filter the cancellations, such as 'Yes' for cancelled reservations.
	 * @return An array of CancelledReservation objects containing the type, date, and park of each cancelled reservation that meets the condition.
     *         Returns null if the database connection is unavailable or if no records meet the condition.
	 */
	public static CancelledReservation[] getReservations(String condition){
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}

		ArrayList<CancelledReservation> temp = new ArrayList<>();

    	try {
			Statement st = MainServer.dbConnection.createStatement();
			// Execute query to retrieve cancellations based on the specified condition
			ResultSet rs = st.executeQuery("SELECT Type, Date, Park FROM cancellationsReports"
					+ "					    WHERE ReservationIdCancelled ='"+condition+"'");
			if (!rs.next()) {
    			System.out.println("[CancellationsReportHandler] - result set was empty");
    			return null;
			}
			// Reset cursor to the beginning of the result set
			 rs.beforeFirst(); 
			// Fill the array records from DB
			 while(rs.next()) {
				 CancelledReservation listToReturn = new CancelledReservation(rs.getString(1),rs.getString(2),rs.getString(3));
					temp.add(listToReturn);
				}
				rs.close();
				return (CancelledReservation[]) temp.toArray(new CancelledReservation[temp.size()]);
		}catch(Exception ex) {
			System.out.println("[CancellationsReportHandler] - failed to execute query");
			ex.printStackTrace();
			return null;
		}
	}		
}