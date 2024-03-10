package main;

import java.io.IOException;
import java.sql.Connection;

import ocsf.server.src.AbstractServer;
import ocsf.server.src.ConnectionToClient;
import requests.Message;
import requests.RequestType;



public class MainServer extends AbstractServer{
	
	public static String PORT;
	public static String dbPassword;
	public static Connection dbConnection;

	public MainServer(int port) {
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

}
