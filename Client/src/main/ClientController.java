package main;

import java.io.IOException;
import java.util.HashMap;

import entities.Bill;
import entities.Order;
import entities.Park;
import entities.User;
import entities.Visitor;
import requests.Message;

/**
* The ClientController class manages the client-side functionality of the GoNature application.
* It handles communication with the server, maintains connection status, and stores relevant data.
*/
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
	public static Bill showBill;
	public static Order[] reservationshowed; 
	public static boolean monitoring = false;
	public static Order updatedReservation;
 
	private static HashMap<String, Park> parks = new HashMap<>();//key is the name of the park
	
	
	/**
    * Constructs a ClientController object with the given host and port.
    * @param host The host address of the server.
    * @param port The port number of the server.
    */
	private ClientController(String host, int port) {
		try {
			client = new GoNatureClient(host, port);
		} catch (IOException e) {
			System.out.println("[ClientController] - Error setting up connection");
			e.printStackTrace();
		}
	}
	
	 /**
     * Sends a message to the client's message handler from Client UI.
     * @param message The message to be handled by the client.
     */
	 public void accept(Message str) 
	 {
		  client.handleMessageFromClientUI(str);
	 }
	 
	 /**
	 * Retrieves the GoNatureClient instance associated with this controller.
	 * @return The GoNatureClient instance.
	 */
	 public GoNatureClient getClient() {
		  return client;
	 }
	 
	 
	 /**
	 * Creates a singleton instance of the ClientController with the given host and port.
	 * @param host The host address of the server.
	 * @param port The port number of the server.
	 */
	 public static void createInstance(String host, int port) {
		  if (connectedToServer) {
			 System.out.println("[ClientController] - a controller instance already exist");
			 return;
		  }
		  clientController = new ClientController(host, port);
	 }
	 
	 /**
	 * Retrieves the singleton instance of the ClientController.
	 * @return The ClientController instance.
	 */
	 public static ClientController getController() {
			return clientController;			
	 }

	 /**
	 * Retrieves the HashMap containing information about parks.
	 * @return The HashMap containing park information.
	 */
	 public static HashMap<String, Park> getParks() {
			return parks;
	 }

	 /**
	 * Sets the HashMap containing information about parks.
	 * @param parks The HashMap containing park information.
	 */
	 public static void setParks(HashMap<String, Park> parks) {
			ClientController.parks = parks;
	 }
}