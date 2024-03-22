package main.handlers;

import java.sql.ResultSet;
import java.sql.Statement;
import main.Constants;
import main.MainServer;

public class CancellationsGraphDataHandler {
    
    public static int[] getDataForGraph(String[] dataToSelect){
        if (MainServer.dbConnection == null) {
            System.out.println(Constants.DB_CONNECTION_ERROR);
            return null;
        }
        
        
        int[] dataToReturn = new int[3];
        
        // Get ReservationIdCancelled and ReservationIdNotActivated Number from cancellationsReports
        try {
            Statement st = MainServer.dbConnection.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT "
                  + " COUNT(CASE WHEN ReservationIdCancelled = 'Yes' THEN 1 END) AS CancelledCount, "
                  + " COUNT(CASE WHEN ReservationIdNotActivated = 'Yes' THEN 1 END) AS NotActivatedCount "
                  + " FROM cancellationsReports WHERE Park = '"+ dataToSelect[1]+"' AND" 
                  + " Date = '"+dataToSelect[0]+"'");
            
            // Move the cursor to the first row
            if (rs.next()) {
                // Retrieve data from the result set
                dataToReturn[0] = rs.getInt("CancelledCount");
                dataToReturn[1] = rs.getInt("NotActivatedCount");
            } else {
                // Handle the case where no rows were returned
                System.out.println("No data found for the given query.");
            }
            
            rs.close(); // Close the result set
        } catch (Exception ex) {
            System.out.println("[CancellationsGraphDataHandler] - failed to execute query");
            ex.printStackTrace();
            return null;
        }
        
        
        // Get Reservations Number from reservations
        try {
            Statement st = MainServer.dbConnection.createStatement();
            ResultSet rs = st.executeQuery(
                       "SELECT COUNT(*) AS InvitedInAdvanceCount"
                       +" FROM reservations"
                       +" WHERE InvitedInAdvance = 1"
                       +" AND Park = '"+ dataToSelect[1]+"'"
                       +" AND ReservationDate = '"+dataToSelect[0]+"'");  
            // Move the cursor to the first row
            if (rs.next()) {
                // Retrieve data from the result set
                dataToReturn[2] = rs.getInt("InvitedInAdvanceCount");
            } else {
                // Handle the case where no rows were returned
                System.out.println("No data found for the given query.");
            }
            
            rs.close(); // Close the result set
        } catch (Exception ex) {
            System.out.println("[CancellationsGraphDataHandler] - failed to execute query");
            ex.printStackTrace();
            return null;
        }
        return dataToReturn;
    }   
}
