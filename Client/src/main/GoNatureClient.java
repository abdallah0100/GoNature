package main;

import java.io.IOException;
import java.util.ArrayList;

import main.client_requests.RequestHandler;
import ocsf.client.AbstractClient;
import requests.Message;
import requests.RequestType;

/**
* The GoNatureClient class represents the client-side of the GoNature application.
* It extends the AbstractClient class from the OCSF framework to handle communication with the server.
*/
public class GoNatureClient extends AbstractClient{
	 
	  // if the client is awaiting a response from server
	  public static boolean awaitResponse = false;
	  
	  private ArrayList<String[]> orders;

	  
	/**
	* Constructs a GoNatureClient object with the specified host and port.
	* @param host The host address of the server.
	* @param port The port number of the server.
	* @throws IOException If an I/O error occurs when creating the client.
	*/
	public GoNatureClient(String host, int port) throws IOException{
		super(host, port);
		//connected successfully, setting the variable to true
	}

	
	/**
    * Handles incoming messages from the server.
    * @param incomingMsg The message received from the server.
    */
	@Override
	protected void handleMessageFromServer(Object incomingMsg) {
		awaitResponse = false;
		if (!(incomingMsg instanceof Message)) {
			System.out.println("[GoNatureClient] - the message we received from the server is not of type Message");
			return;
		}
		Message msg = (Message)incomingMsg;
		RequestHandler.handleIncomingRequests(msg);
	}
	
	 /**
     * Sends a message to the server from the client UI.
     * @param message The message to be sent to the server.
     */
	 public void handleMessageFromClientUI(Message message)  
	  {
	    try
	    {
	    	openConnection();//in order to send more than one message
			ClientController.connectedToServer = true;
	       	awaitResponse = true;
	    	sendToServer(message);
			// wait for response
			while (awaitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	    }
	    catch(IOException e)
	    {
	    	System.out.println("[GoNatureClient] - Error sending message to server");
			ClientController.connectedToServer = false;
	    	e.printStackTrace();
	    	quit();
	    }
	  }
	 
	  /**
	  * Terminates the client.
	  */
	  public void quit()
	  {
	    try
	    {
	    	if (ClientController.connectedToServer) {
	    		String connectedAs = "";
	    		if (ClientController.connectedUser != null)
	    			connectedAs = ClientController.connectedUser.getUsername();
	    		else if (ClientController.connectedVisitor != null)
	    			connectedAs = ClientController.connectedVisitor.getId();
	    		handleMessageFromClientUI(new Message(RequestType.DISCONNECT_FROM_SERVER, connectedAs));
	    	}
	    	ClientController.connectedVisitor = null;
	    	closeConnection();
	    }
	    catch(IOException e) {
	    	System.out.println("[GoNatureClient] - Error closing connection");
	    	e.printStackTrace();
	    }
	  }
	  
	  
	  /**
	  * Retrieves the list of orders.
	  * @return The list of orders.
	  */
	  public ArrayList<String[]> getOrders(){
		  return orders;
	  }
	  
	  
	  /**
	  * Retrieves the order with the specified ID.
	  * @param id The ID of the order to retrieve.
	  * @return The order with the specified ID, or null if not found.
	  */
	  public String[] getOrderById(String id) {
		  for (String[] o : orders)
			  if (o[1].equals(id))
				  return o;
		  return null;
	  }
}