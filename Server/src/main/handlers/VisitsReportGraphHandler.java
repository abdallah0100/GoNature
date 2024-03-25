package main.handlers;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import entities.VisitsReport;
import main.Constants;
import main.MainServer;

public class VisitsReportGraphHandler {
	
	
	 public static VisitsReport[] getVisitsDataForGraph(String[] requestedData){
	        if (MainServer.dbConnection == null) {
	            System.out.println(Constants.DB_CONNECTION_ERROR);
	            return null;
	        }
	        ArrayList<VisitsReport> temp = new ArrayList<>();
	    
	        // Get all the entry and exit times at a specific date
	        try {
	            Statement st = MainServer.dbConnection.createStatement();
	            ResultSet rs = st.executeQuery(
	                         "SELECT enterHour, enterMin, exitHour, exitMin, reservationType"
	                        +" FROM processedres"
	                        +" WHERE Park = '"+requestedData[1]+"' AND date = '"+requestedData[0]+"'"); 
	            while(rs.next()) {
	            	int enterHour = Integer.parseInt(rs.getString(1));
	            	int enterMin = Integer.parseInt(rs.getString(2));
	            	int exitHour = Integer.parseInt(rs.getString(3));
	            	int exitMin = Integer.parseInt(rs.getString(4));
	            		
	            	VisitsReport fillReport = new VisitsReport(enterHour,enterMin,exitHour,exitMin,rs.getString(5));
					temp.add(fillReport);
				}
				rs.close();
				System.out.println(temp);
				
				
				return (VisitsReport[]) temp.toArray(new VisitsReport[temp.size()]);
	        } catch (Exception ex) {
	            System.out.println("[VisitsReportGraphHandler] - failed to execute query");
	            ex.printStackTrace();
	            return null;
	        } 
	 }

}
 
