package main;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.databse.DatabaseConnector;
import main.entities.ClientConnection;
import main.handlers.ServerRequestHandler;
import ocsf.server.src.AbstractServer;
import ocsf.server.src.ConnectionToClient;
import requests.Message;
import requests.RequestType;

/**
 * The MainServer class extends AbstractServer and is responsible for initializing the server,
 * handling messages from clients, managing client connections, and maintaining the database connection.
 */
public class MainServer extends AbstractServer{
	
	public static Connection dbConnection;
	public static int SERVER_PORT;
	private static MainServer server;
	public static boolean serverStarted = false;
	private static ObservableList<ClientConnection> connections = FXCollections.observableArrayList();
	
	/**
	 * Keeps track of who is connected to the server by linking each client's ID to their connection.
	 * used to determine if they are logged in.
	 */
	private HashMap<String, ConnectionToClient> connectionMap;

	/**
	 * Constructs a new MainServer instance.
	 * 
	 * @param port is the port number on which the server will listen for connections.
	 */
	private MainServer(int port) {
		super(port);
		connectionMap = new HashMap<>();
	}

	/**
	 * Handles incoming messages from clients. Validates the message type and sends 
	 * requests to the ServerRequestHandler for further processing.
	 */
	@Override
	protected void handleMessageFromClient(Object incomingMsg, ConnectionToClient client) 
	{
		if (!(incomingMsg instanceof Message)){
			System.out.println("[MainServer] - received invalid message type from client " + client.getName());
			ServerRequestHandler.respondToClient(client, new Message(RequestType.REQUEST_ERROR, "Invalid request data type; required is: Message"));
			return;
		}
		Message msg = (Message)incomingMsg;
		ServerRequestHandler.handleRequest(msg, client);		
	}
	
	/**
	 * Starts the server with specified database login details and listens for client connections.
	 * 
	 * @param p The port number as a String.
	 * @param dbName The name of the database to connect to.
	 * @param dbUser The database user name.
	 * @param dbPass The database password.
	 */	
	public static void startServer(String p, String dbName, String dbUser, String dbPass) {
		int port;
		try {
			port = Integer.parseInt(p);
		}catch(Exception e) {
			System.out.println("[MainServer] - received invalid port: " + p);
			return;
		}
		//initiating database connection
		DatabaseConnector dbConnector = new DatabaseConnector();
		MainServer.dbConnection = dbConnector.getConnection(dbName, dbUser, dbPass);
		
		//creating main server instance
		MainServer ms = createInstance(port);
		try {
			MainServer.serverStarted = true;
			ms.listen();
		}catch(Exception ex) {
			System.out.println("[MainServer] - Error listening to clients");
			MainServer.serverStarted = false;
			ex.printStackTrace();
		}
	}
	
	/**
	 * Creates a new instance of MainServer with the given port.
	 * 
	 * @param port The port number for the server.
	 * @return The newly created instance of MainServer.
	 */
	private static MainServer createInstance(int port) {
		server = new MainServer(port);
		return server;
	}

	/**
	 * Retrieves the single instance of MainServer, creating it if it doesn't exist.
	 * Implements the Singleton design pattern.
	 * 
	 * @return The single instance of MainServer.
	 */
	public static MainServer getInstance() {
		if (server == null)
			server = new MainServer(Constants.SERVER_PORT);
		return server;
	}
	
	/**
	 * Closes the server connection and resets the server state.
	 */
	public void closeConnection() {
		try {
			close();
		} catch (IOException e) {
			System.out.println("[MainServer] - Error closing server");
			e.printStackTrace();
		}
		MainServer.serverStarted = false;
		MainServer.dbConnection = null;
		resetServer();
		
	}
	
	/**
	 *  Resets the server to a null state.
	 */
	private static void resetServer() {
		server = null;
	}
	
	/**
	 * Retrieves the list of current client connections.
	 * 
	 * @return An ObservableList of ClientConnection objects.
	 */
	public static ObservableList<ClientConnection> getConnections(){
		return connections;
	}
	
	/**
	 * Sets the list of client connections.
	 * 
	 * @param conns The new list of client connections to be set.
	 */
	public static void setClientConnections(ObservableList<ClientConnection> conns) {
		connections = conns;
	}

	/**
	 * Gets the map linking client IDs to their connections.
	 * 
	 * @return The map of client IDs to connections.
	 */
	public HashMap<String, ConnectionToClient> getConnectionMap() {
		return connectionMap;
	}
}
