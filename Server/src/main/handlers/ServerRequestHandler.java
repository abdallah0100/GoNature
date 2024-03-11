package main.handlers;

import java.io.IOException;

import ocsf.server.src.ConnectionToClient;
import requests.Message;
import requests.RequestType;

public class ServerRequestHandler {

	public static void handleRequest(Message msg, ConnectionToClient client) {
		String generalRespondMsg = "responded with no message.";
		switch(msg.getRequestEnumType()) {
		case CONNECT_TO_SERVER:
			ClientConnectionHandler.addNewConnection(client);
			generalRespondMsg = "New Connection has been added successfully";
			break;
		default:
			respondToClient(client, new Message(RequestType.UNIMPLEMENTED_RESPOND, "response type is not implemented"));
			break;			
		}
		
		respondToClient(client, new Message(RequestType.GENERAL_RESPOND, generalRespondMsg));
	}
	
	public static void respondToClient(ConnectionToClient client, Message msg) {
		try {
			client.sendToClient(msg);
		} catch (IOException e) {
			System.out.println("[ServerRequestHandler] - Error responding to client");
			e.printStackTrace();
		}
	}
	
}
