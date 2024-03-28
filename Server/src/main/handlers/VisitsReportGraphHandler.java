package main.handlers;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import entities.VisitsReport;
import main.Constants;
import main.MainServer;

/**
 * The VisitsReportGraphHandler class is responsible for fetching data related to park visits,
 * which is utilized for generating graphical reports. This includes retrieving entry and exit times  
 * of visitors for a given date and park, and categorizing these times based on the reservation type.
 */
public class VisitsReportGraphHandler {
	

	/**
	 * Retrieves visitation data for a specified park on a specific date to be used in generating graphical reports.
	 * The data includes entry and exit times of visitors along with the type of their reservations.
	 * 
	 * @param requestedData An array of strings where the first element is the date and the second element is 
	 *        the park name for which the visit data is requested.
	 * @return An array of VisitsReport objects each representing a visit record with entry and exit times and
	 *         the reservation type. Returns {@code null} if the database connection is unavailable or if an error 
	 *         occurs during the query execution.
	 */
	public static VisitsReport[] getVisitsDataForGraph(String[] requestedData){
		     // Check for database connection availability
	        if (MainServer.dbConnection == null) {
	            System.out.println(Constants.DB_CONNECTION_ERROR);
	            return null;
	        }
	        ArrayList<VisitsReport> temp = new ArrayList<>();
	        try {
	            Statement st = MainServer.dbConnection.createStatement();
	            // Execute query to fetch entry and exit times along with reservation types for the specified date and park.
	            ResultSet rs = st.executeQuery(
	                         "SELECT enterHour, enterMin, exitHour, exitMin, reservationType"
	                        +" FROM processedres"
	                        +" WHERE Park = '"+requestedData[1]+"' AND date = '"+requestedData[0]+"'"); 
	            while(rs.next()) {
	            	int enterHour = Integer.parseInt(rs.getString(1));
	            	int enterMin = Integer.parseInt(rs.getString(2));
	            	int exitHour = Integer.parseInt(rs.getString(3));
	            	int exitMin = Integer.parseInt(rs.getString(4));
	            	// Create a new VisitsReport object for each record and add it to the list.	
	            	VisitsReport fillReport = new VisitsReport(enterHour,enterMin,exitHour,exitMin,rs.getString(5));
					temp.add(fillReport);
				}
				rs.close();
				System.out.println(temp);				
				// Convert the list of VisitsReport objects to an array and return
				return (VisitsReport[]) temp.toArray(new VisitsReport[temp.size()]);
	        } catch (Exception ex) {
	            System.out.println("[VisitsReportGraphHandler] - failed to execute query");
	            ex.printStackTrace();
	            return null;
	        } 
	 }
}
 
