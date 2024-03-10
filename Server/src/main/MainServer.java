package main;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import OCSF.src.ocsf.server.AbstractServer;
import OCSF.src.ocsf.server.ConnectionToClient;
import main.commons.requests.RequestType;
import main.controllers.ServerController;
import main.databse.DatabaseController;



public class MainServer extends AbstractServer{
	
	public static String PORT;
	public static String dbPassword;
	public static Connection dbConnection;

	public MainServer(int port) {
		super(port);
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) 
	{
		ArrayList<String> request = (ArrayList<String>) msg;
		int msgType = Integer.parseInt(request.get(0));
		
		//if the database connection failed, respond to the client with an appropriate message and stop
		if (dbConnection == null) {
			try {
				client.sendToClient("Error connecting to Databse");
				System.out.println("[MainServer] - No connection to the database");
			} catch (IOException e) {
				System.out.println("[MainServer] - Error responding to client");
				e.printStackTrace();
			}
			//check if it's client startUp and no DB add to connect client 
			if(msgType == RequestType.REQUEST_DATA.getRequestId())
				ServerController.getInstance().addClient(client);
			return;
		}
		
		ArrayList<String[]> data = null;
		RequestType type = RequestType.getTypeById(msgType);
		boolean updated = false;
		switch(type) {
		case REQUEST_DATA:
			data = DatabaseController.getAllOrders();
			ServerController.getInstance().addClient(client);
			break;
		case UPDATE:
			if (DatabaseController.updateOrder(request)) {
				updated = true;
				System.out.println("[MainServer] - Order has been updated successfully");
					
			}
			else {
				System.out.println("[MainServer] - Update failed");
			}
			
			break;
		default:
			break;
		}
		
		try {
			if (type == RequestType.UPDATE)
				client.sendToClient(updated);
			else
				client.sendToClient(data);
		} catch (IOException e) {
			System.out.println("[MainServer] - Error responding to client: " + client.toString());
			e.printStackTrace();
		}
		
	}
	

}
