package main.handlers;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import main.Constants;
import main.MainServer;

public class EditedVariablesRequestHandler {

	public static HashMap<String, String> getEditedVariables(String requestedPark){
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		
		//key is the edited variable with newValue
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