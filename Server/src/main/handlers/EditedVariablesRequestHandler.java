package main.handlers;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import main.Constants;
import main.MainServer;

/**
 * Handles requests related to fetching variables that have been requested for editing in a particular park.
 * This class queries the 'requested_changes' table to retrieve such variables and their new proposed values.
 */
public class EditedVariablesRequestHandler {

	/**
	 * Retrieves a map of variables that have been requested for editing along with their new values for a specified park.
	 * 
	 * @param requestedPark The name of the park for which edited variables are requested.
	 * @return A HashMap where keys are the names of the variables requested for editing and values are the new proposed values.
     *         Returns null if the database connection is unavailable or an error occurs during query execution.
	 */
	public static HashMap<String, String> getEditedVariables(String requestedPark){
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}		
		// key is the edited variable with newValue
		HashMap<String, String> hashMapToReturn = new HashMap<>();
    	try {
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM requested_changes"
										   +" WHERE parkName ='"+requestedPark+"'");						  
			 while(rs.next()) {
				 hashMapToReturn.put(rs.getString(3), rs.getString(4));
			 }
			 rs.close();
			 return hashMapToReturn;
		}catch(Exception ex) {
			System.out.println("[EditedVariablesRequestHandler] - failed to execute query");
			ex.printStackTrace();
			return null;
		}

	}	
}