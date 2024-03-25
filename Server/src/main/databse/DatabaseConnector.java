package main.databse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages the creation and management of database connections.
 * This class is responsible for initializing the JDBC driver, establishing
 * connections to the database, and handling any errors that occur during the
 * connection process.
 */
public class DatabaseConnector {	

	/**
	 * Attempts to load the JDBC driver.
	 * This method tries to initialize the MySQL JDBC driver and logs the outcome.
	 * 
	 * @return true if the driver was loaded successfully, false otherwise.
	 */
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

	/**
	 * Establishes a connection to the database using the provided credentials.
	 * This method is private as it should only be called internally once the JDBC
	 * driver has been successfully loaded.
	 * 
	 * @param dbName The URL of the database to connect to.
	 * @param user The user name for the database login.
	 * @param pass The password for the database login.
	 * @return A Connection object if the connection is successful, null otherwise.
	 */
	private Connection makeConnection(String dbName, String user, String pass) {
        try 
        {
            Connection conn = DriverManager.getConnection(dbName, user, pass);
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
	
	/**
	 * Sets up a connection to the database with the given login details. First, it 
	 * checks if the database is ready, and if so, it tries to connect to the database.
	 * 
	 * @param dbName The URL of the database to connect to.
	 * @param user The user name for the database login.
	 * @param pass The password for the database login.
	 * @return A Connection object if the connection is successful, null otherwise.
	 */
	public Connection getConnection(String dbName, String user, String pass) {
		if (loadDriver())//if succeeded to connect to the driver
			return makeConnection(dbName, user, pass);//return a Connection
		return null;
	}
	
	
}
