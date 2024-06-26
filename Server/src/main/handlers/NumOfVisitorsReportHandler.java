package main.handlers;

import java.sql.ResultSet;
import java.sql.Statement;
import main.Constants;
import main.MainServer;
import utilities.Utils;

/**
 * Handles the retrieval of visitor statistics from the 'numofvisitorsreports' table,
 * specifically focusing on the number of organized group visitors and private visitors for a given park, month, and year.
 */
public class NumOfVisitorsReportHandler {

	/**
	 * Gets the number of organized group and private visitors.
	 * @param requestedData Contains park name, month name, and year.
	 * @return Array with totals: [0] for organized groups, [1] for privates.
	 *         null if the database connection is unavailable or if an error occurs during the query execution.
	 */
	public static String[] getNumOfVisitors(String[] requestedData){
				
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		int sumOfOrganized = 0;
		int sumOfIndivisuals = 0;
		String park = requestedData[0];
		String year = requestedData[2];
		String[] listToReturn = new String[2];
		int selectedMonth = Utils.getMonthNumberByName(requestedData[1]);
		
		try {
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM numofvisitorsreports " +
                    "WHERE Park = '" + park + "' AND month = '" + selectedMonth + "' " +
                    "AND year = '" + year + "'");

			 while(rs.next()) {
				 sumOfOrganized += Integer.parseInt(rs.getString(3));
				 sumOfIndivisuals += Integer.parseInt(rs.getString(4));
			 }
			 rs.close();
			 listToReturn[0] = Integer.toString(sumOfOrganized);
			 listToReturn[1] = Integer.toString(sumOfIndivisuals);
			 return listToReturn;
		}catch(Exception ex) {
			System.out.println("[NumOfVisitorsReportHandler] - failed to execute query");
			ex.printStackTrace();
			return null;
		}
	}		
}