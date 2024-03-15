package main.handlers;

import java.io.IOException;

import entities.Report;
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
		case REQUEST_BILL:
			if (!(msg.getRequestData() instanceof String)) {
				respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (String)"));
				return;
			}
			String b = UserRequestHandler.billExists((String) msg.getRequestData());
			respondToClient(client, new Message(RequestType.REQUEST_BILL, b));
			return;
		case INSERT_INSTRUCTOR:
			if(!(msg.getRequestData() instanceof Visitor)) {	
			respondToClient(client, new Message(RequestType.REQUEST_ERROR, "invalid request data (Visitor)"));
			return;
		}
			int x=UserRequestHandler.instructorExists((Visitor) msg.getRequestData());
			respondToClient(client, new Message(RequestType.INSERT_INSTRUCTOR, x));
			return;
		case FETCH_RESERVATION_DATA:
			if (!(msg.getRequestData() instanceof Report)) {
				respondToClient(client, new Message(RequestType.FETCH_RESERVATION_DATA, "invalid request data (Report -> fetching data)"));
				return;
			}
			int individuals = ReportRequestHandler.getReservationCountByType("Individual");
			int group = ReportRequestHandler.getReservationCountByType("Organized Group");
			Report r = (Report) msg.getRequestData();
			r.setIndividuals(individuals);
			r.setGroups(group);
			respondToClient(client, new Message(RequestType.FETCH_RESERVATION_DATA, r));
		return;
		case CREATE_REPORT:
			if (!(msg.getRequestData() instanceof Report)) {
				respondToClient(client, new Message(RequestType.FETCH_RESERVATION_DATA, "invalid request data (Report -> Creation)"));
				return;
			}
			Report r2 = (Report) msg.getRequestData();
			boolean result = ReportRequestHandler.insertNewReport(r2);
			respondToClient(client, new Message(RequestType.CREATE_REPORT, result ? "Successfuly created report" : "Error creating report"));
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
