package main.handlers;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import main.Constants;
import main.MainServer;

public class CancellationsReportHandler {

	public static ArrayList<?> getReservations(ArrayList<?> l,String condition){
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}

		ArrayList<String[]> list = new ArrayList();
    	try {
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT Type, Date, Park FROM cancellationsReports"
					+ "					    WHERE ReservationIdCancelled ='"+condition+"'");
			if (!rs.next()) {
    			System.out.println("[CancellationsReportHandler] - result set was empty");
    			return null;
			}
			// reset cursor to the beginning of the result set
			 rs.beforeFirst();
			 
			// fill the array records from DB
			 while (rs.next()) {
	              String[] rowData = new String[3];
	              for (int i = 1; i <= 3; i++) {
	                  rowData[i - 1] = rs.getString(i);
	              }
	              list.add(rowData);
	        }
			rs.close();
		}catch(Exception ex) {
			System.out.println("[CancellationsReportHandler] - failed to execute query");
			ex.printStackTrace();
			return null;
		}
    
		return list;
	}		
}