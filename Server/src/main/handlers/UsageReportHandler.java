package main.handlers;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import entities.UsageReport;
import main.Constants;
import main.MainServer;

/**
 * Handles operations related to fetching usage reports from the database. 
 * This includes generating reports on park usage, such as the number of visitors and the capacity.
 */
public class UsageReportHandler {

	/**
	 * Retrieves all usage reports from the database. These reports provide insights into the utilization of park,
	 * including details such as the month and year of the report, the total number of visitors, and the park's capacity usage.
	 * 
	 * @return An array of UsageReport objects containing the details of each usage report. Returns null if
	 *         there is an issue with the database connection or if an error occurs during the query execution.
	 */
	public static UsageReport[] getUsageReports() {
		// Check for database connection availability
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		try {
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM  NotFullReports");
			// Check if the result set is empty
			if (!rs.next()) {
				System.out.println("[UsageReportHandler] - result set was empty");
				return null;
			}
			// Reset cursor to the beginning of the result set
			 rs.beforeFirst();

			ArrayList<UsageReport> temp = new ArrayList<>();
			while(rs.next()) {
				UsageReport listToReturn = new UsageReport(rs.getString(1),rs.getString(2),rs.getString(3),
	            		  							     rs.getString(4),rs.getString(5));
				temp.add(listToReturn);
			}
			rs.close();
			// Convert the list to an array and return
			return (UsageReport[]) temp.toArray(new UsageReport[temp.size()]);
			
		}catch(Exception ex) {
			System.out.println("[UsageReportHandler] - failed to execute query");
			ex.printStackTrace();
			return null;
		}
	}
}


