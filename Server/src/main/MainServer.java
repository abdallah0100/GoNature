package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.databse.DatabaseConnector;
import main.entities.ClientConnection;
import main.handlers.ServerRequestHandler;
import ocsf.server.src.AbstractServer;
import ocsf.server.src.ConnectionToClient;
import requests.Message;
import requests.RequestType;



public class MainServer extends AbstractServer{
	
	public static Connection dbConnection;
	
	public static int SERVER_PORT;
	private static MainServer server;
	public static boolean serverStarted = false;
	
	private static ObservableList<ClientConnection> connections = FXCollections.observableArrayList();;

	private MainServer(int port) {
		super(port);
	}

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
	private static MainServer createInstance(int port) {
		server = new MainServer(port);
		return server;
	}
	//MainServer is a singleton
	public static MainServer getInstance() {
		if (server == null)
			server = new MainServer(Constants.SERVER_PORT);
		return server;
	}
	
	public void closeConnection() {
		try {
			if (MainServer.dbConnection != null)
				MainServer.dbConnection.close();
		} catch (SQLException e) {
			System.out.println("[MainServer] - Error closing database connection");
			e.printStackTrace();
		}
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
	private static void resetServer() {
		server = null;
	}
	
	public static ObservableList<ClientConnection> getConnections(){
		return connections;
	}
	public static void setClientConnections(ObservableList<ClientConnection> conns) {
		connections = conns;
	}

}
