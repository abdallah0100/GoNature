package main;

import java.io.IOException;
import entities.Order;
import entities.User;
import entities.Visitor;
import requests.Message;

public class ClientController {

	private static ClientController clientController;
	
	private GoNatureClient client;
	
	
	/*
	 * variables to determine whether connected to the server/database or not
	 * */
	public static boolean connectedToServer = false;
	public static boolean fetchedData = false;
	public static Visitor connectedVisitor;
	public static User connectedUser;
	public static Order reservationMade;
	
	private ClientController(String host, int port) {
		try {
			client = new GoNatureClient(host, port);
		} catch (IOException e) {
			System.out.println("[ClientController] - Error setting up connection");
			e.printStackTrace();
		}
	}
	
	  /**
	   * This method waits for input from the console.  Once it is 
	   * received, it sends it to the client's message handler.
	   */
	  public void accept(Message str) 
	  {
		  client.handleMessageFromClientUI(str);
	  }
	  
	  public GoNatureClient getClient() {
		  return client;
	  }
	  
	  public static void createInstance(String host, int port) {
		  if (connectedToServer) {
			 System.out.println("[ClientController] - a controller instance already exist");
			 return;
		  }
		  clientController = new ClientController(host, port);
	  }
		public static ClientController getController() {
			return clientController;			
		}

}
