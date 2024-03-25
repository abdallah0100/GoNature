package main;

/**
 *  This class defines constants used throughout the server. 
 *  These constants include configuration for server and database 
 *  settings, as well as common error messages. The purpose of this
 *  class is to centralize the management of these values, making the 
 *  code more maintainable and consistent.
 */

public class Constants {
	
	/**
	 * The port number on which the server listens for connections.
	 */
	
	public static int SERVER_PORT;
	
	/**
	 * The user name for accessing the database. It is set to 'root' by default.
	 */
	
	public static final String DB_USERNAME = "root";
	
	/**
	 * The connection path for the database, specifying the database type,
	 * location, and database name ('gonature'), as well as the server time zone.
	 */
	
	public static final String DB_NAME = "jdbc:mysql://localhost/gonature?serverTimezone=IST";
	
	/**
	 * The default port number is set to '5555'.
	 */
	
	public static final String DEFAULT_PORT = "5555";
	
	/**
	 * A common error message indicating that there was an error in connecting 
	 * to the database. 
	 */
	
	public static final String DB_CONNECTION_ERROR = "Error connecting to database";
}
