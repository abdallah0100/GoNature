package main.handlers;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import entities.UsageReport;
import main.Constants;
import main.MainServer;

public class UsageReportHandler {

	
	public static UsageReport[] getUsageReports() {
		
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		
		try {
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM  NotFullReports");
			
			if (!rs.next()) {
				System.out.println("[UsageReportHandler] - result set was empty");
				return null;
			}
			// reset cursor to the beginning of the result set
			 rs.beforeFirst();
			 
			ArrayList<UsageReport> temp = new ArrayList<>();
			while(rs.next()) {
				UsageReport listToReturn = new UsageReport(rs.getString(1),rs.getString(2),rs.getString(3),
	            		  							     rs.getString(4),rs.getString(5));
				temp.add(listToReturn);
			}
			rs.close();
			return (UsageReport[]) temp.toArray(new UsageReport[temp.size()]);
			
		}catch(Exception ex) {
			System.out.println("[UsageReportHandler] - failed to execute query");
			ex.printStackTrace();
			return null;
		}
	}
}


