package main.handlers;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import main.Constants;
import main.MainServer;

public class UsageReportHandler {
	
	public static ArrayList<String[]> getUsageReports(ArrayList<String[]> l) {
		
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ArrayList<String[]> list = new ArrayList();
		try {
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM  NotFullReports");
			if (!rs.next()) {
				System.out.println("[UsageReportHandler] - result set was empty");
				return null;
			}
			// reset cursor to the beginning of the result set
			 rs.beforeFirst();
			 
			// fill the array records from DB
			 while (rs.next()) {
	              String[] rowData = new String[5];
	              for (int i = 1; i <= 5; i++) {
	                  rowData[i - 1] = rs.getString(i);
	              }
	              list.add(rowData);
	        }
			rs.close();
		}catch(Exception ex) {
			System.out.println("[UsageReportHandler] - failed to execute query");
			ex.printStackTrace();
			return null;
		}
		return list;
	}
}


