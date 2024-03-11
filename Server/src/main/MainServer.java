package main;

import java.io.IOException;
import java.sql.Connection;

import main.databse.DatabaseConnector;
import ocsf.server.src.AbstractServer;
import ocsf.server.src.ConnectionToClient;
import requests.Message;
import requests.RequestType;



public class MainServer extends AbstractServer{
	
	public static Connection dbConnection;
	
	private static int port;
	private static MainServer server;
	public static boolean serverStarted = false;

	private MainServer(int port) {
		super(port);
	}

	@Override
	protected void handleMessageFromClient(Object incomingMsg, ConnectionToClient client) 
	{
		if (!(incomingMsg instanceof Message)){
			System.out.println("[MainServer] - received invalid message type from client " + client.getName());
			respondToClient(client, new Message(RequestType.REQUEST_ERROR, "Invalid request data type; required is: Message"));
			return;
		}
		Message msg = (Message)incomingMsg;
		
		switch(msg.getRequestEnumType()) {
		case GENERAL_RESPOND:
			break;
		default:
			respondToClient(client, new Message(RequestType.UNIMPLEMENTED_RESPOND, "response type is not implemented"));
			break;
		}
		
	}
	
	public void respondToClient(ConnectionToClient client, Message msg) {
		try {
			client.sendToClient(msg);
		} catch (IOException e) {
			System.out.println("[MainServer] - Error responding to client");
			e.printStackTrace();
		}
	}
	
	public static void startServer(String p, String dbName, String dbUser, String dbPass) {
		try {
			MainServer.port = Integer.parseInt(p);
		}catch(Exception e) {
			System.out.println("[MainServer] - received invalid port: " + p);
			return;
		}
		//initiating database connection
		DatabaseConnector dbConnector = new DatabaseConnector();
		MainServer.dbConnection = dbConnector.getConnection(dbName, dbUser, dbPass);
		
		//creating main server instance
		getInstance();
		MainServer.serverStarted = true;
		try {
			server.listen();
		}catch(Exception ex) {
			System.out.println("[MainServer] - Error listening to clients");
			ex.printStackTrace();
		}
	}
	
	//MainServer is a singleton
	public static MainServer getInstance() {
		if (server == null)
			server = new MainServer(port);
		return server;
	}
	
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
	private static void resetServer() {
		server = null;
	}

}
