package main.handlers;
import java.io.IOException;
import entities.Order;

import entities.User;
import entities.Visitor;
import ocsf.server.src.ConnectionToClient;
import requests.Message;
import requests.RequestType;

public class ServerRequestHandler {

	public static void handleRequest(Message msg, ConnectionToClient client) {
		String generalRespondMsg = "responded with no message.";
		switch(msg.getRequestEnumType()) {
		case CONNECT_TO_SERVER:
			ClientConnectionHandler.handleConnectRequest(client, true);
			generalRespondMsg = "New Connection has been added successfully";
			break;
		case DISCONNECT_FROM_SERVER:
			ClientConnectionHandler.handleConnectRequest(client, false);
			generalRespondMsg = "Client has succesfully disconnected from the server";
			break;
		case VALIDATE_VISITOR:
			if (!(msg.getRequestData() instanceof String)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not String)"));
				return;
			}
			Visitor v = VisitorRequestHandler.handleValidateRequest((String) msg.getRequestData());
			respondToClient(client, new Message(RequestType.VALIDATE_VISITOR, v));
			return;
			
		case LOGIN_USER:
			if (!(msg.getRequestData() instanceof User)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not User)"));
				return;
			}
			User u = UserRequestHandler.handleLogInRequest((User) msg.getRequestData());
			respondToClient(client, new Message(RequestType.LOGIN_USER, u));
			return;
		case MAKE_RESERVATION:
			if (!(msg.getRequestData() instanceof Order)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (not Order)"));
				return;
			}
			Order o = VisitorRequestHandler.handleMakeReservationRequest((Order)msg.getRequestData());
			respondToClient(client, new Message(RequestType.MAKE_RESERVATION, o));
			return;
			
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
