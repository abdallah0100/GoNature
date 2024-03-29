package main.handlers;

import java.sql.Statement;
import main.Constants;
import main.MainServer;

/**
 * The class DeletedRequestChangeHandler handles deletion of change requests from the 'requested_changes' table.
 */
public class DeletedRequestChangeHandler {

    /**
     * Deletes a specific change request based on park name and variable to change.
     * @param dataToDelete Array where index 0 is park name and index 1 is variable to change.
     * @return True if deletion succeeded,false if no matching data,null if an error occurred.
     */
    public static Object deleteData(String[] dataToDelete) {
        if (MainServer.dbConnection == null) {
            System.out.println(Constants.DB_CONNECTION_ERROR);
            return null;
        }
        try {
            Statement st = MainServer.dbConnection.createStatement();
            int rowsAffected = st.executeUpdate("DELETE FROM requested_changes WHERE parkName = '"+dataToDelete[0]+"'"
            								  + " AND variableToChange = '"+dataToDelete[1]+"';");
            // Check if any rows were affected by the DELETE operation
            if (rowsAffected > 0) {
                System.out.println("Data deleted successfully.");
                return true;
            } else {
                System.out.println("No data found matching the specified criteria.");
                return false;
            }
        } catch (Exception ex) {
            System.out.println("[DeletedRequestChangeHandler] - failed to execute query");
            ex.printStackTrace();
            return null;
        }
    }
}
