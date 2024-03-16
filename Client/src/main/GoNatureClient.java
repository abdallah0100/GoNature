package main;

import java.io.IOException;
import java.util.ArrayList;

import main.client_requests.RequestHandler;
import ocsf.client.AbstractClient;
import requests.Message;
import requests.RequestType;


public class GoNatureClient extends AbstractClient{
	 
	  public static boolean awaitResponse = false;
	  
	  private ArrayList<String[]> orders;

	public GoNatureClient(String host, int port) throws IOException{
		super(host, port);
		//connected successfully, setting the variable to true
	}

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
	   * This method handles all data coming from the UI            
	   *
	   * @param message The message from the UI.    
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
	   * This method terminates the client.
	   */
	  public void quit()
	  {
	    try
	    {
	    	if (ClientController.connectedToServer)
	    		handleMessageFromClientUI(new Message(RequestType.DISCONNECT_FROM_SERVER));
	    	ClientController.connectedVisitor = null;
	    	closeConnection();
	    }
	    catch(IOException e) {
	    	System.out.println("[GoNatureClient] - Error closing connection");
	    	e.printStackTrace();
	    }
	  }
	  
	  public ArrayList<String[]> getOrders(){
		  return orders;
	  }
	  
	  public String[] getOrderById(String id) {
		  for (String[] o : orders)
			  if (o[1].equals(id))
				  return o;
		  return null;
	  }
}
