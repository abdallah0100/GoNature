package main;

import java.io.IOException;
import java.util.ArrayList;

import OCSF.src.ocsf.client.AbstractClient;


public class GoNatureClient extends AbstractClient{
	
	  public static boolean awaitResponse = false;
	  
	  private ArrayList<String[]> orders;

	public GoNatureClient(String host, int port) throws IOException{
		super(host, port);
		//connected successfully, setting the variable to true
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		awaitResponse = false;
		
		if (msg instanceof ArrayList) {
			orders = (ArrayList<String[]>) msg;
			ClientController.fetchedData = true;
			System.out.println("[GoNatureClient] - Received order data");
		}else {
			System.out.println("[GoNatureClient] - Received msg: " + msg);
		}
	}
	
	  /**
	   * This method handles all data coming from the UI            
	   *
	   * @param message The message from the UI.    
	   */
	  
	  public void handleMessageFromClientUI(ArrayList<String> message)  
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
	    //	e.printStackTrace();
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
