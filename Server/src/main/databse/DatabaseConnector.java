package main.databse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import main.MainServer;

public class DatabaseConnector {	

	private boolean loadDriver() {
		try 
		{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("[DatabaseConnector] - Driver Connection succeded");
            return true;
        } catch (Exception ex) {
        	System.out.println("[DatabaseConnector] - Error loading driver");
        	 ex.printStackTrace();
        	 return false;
        }
	}
	//a function to return a connection to the database, in case of failure returns null
	//private because the use of this function depends on the success of loadDriver
	private Connection makeConnection() {
        try 
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gonature?serverTimezone=IST","root", MainServer.dbPassword);
            System.out.println("SQL connection succeed");
            return conn;
     	} catch (SQLException ex) 
     	    {/* handle any errors*/
     			System.out.println("[DatabaseConnector] - Error creating database connection");
	            System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
	            return null;
            }
	}
	
	//a function that returns a database connection
	public Connection getConnection() {
		if (loadDriver())//if succeded to connect to the driver
			return makeConnection();//return a Connection
		return null;
	}
	
	
}
