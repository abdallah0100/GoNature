package main.handlers;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import entities.CancelledReservation;
import main.Constants;
import main.MainServer;

public class CancellationsReportHandler {

	public static CancelledReservation[] getReservations(String condition){
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}

		
		ArrayList<CancelledReservation> temp = new ArrayList<>();

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