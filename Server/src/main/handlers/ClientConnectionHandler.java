package main.handlers;

import javafx.collections.ObservableList;
import main.MainServer;
import main.entities.ClientConnection;
import ocsf.server.src.ConnectionToClient;

/**
 * The ClientConnectionHandler class is responsible for managing client connections to the server,
 * including handling connect and disconnect requests, maintaining a list of active connections, and managing user logins and logouts.
 */
public class ClientConnectionHandler {

	/**
	 * Handles connection requests from clients. If the connect parameter is true, the client is added to the list
     * of active connections; if false, the client is removed to handle disconnections.
     * 
	 * @param client The ConnectionToClient object representing the client's connection.
	 * @param connect A boolean indicating whether the client is connecting (true) or disconnecting (false).
	 */
	public static void handleConnectRequest(ConnectionToClient client, boolean connect) {
		ClientConnection newCon = new ClientConnection(client);
		ObservableList<ClientConnection> list = MainServer.getConnections();
		// Checking to prevent duplicates by removing the existing connection before adding a new one
		list.remove(newCon);
		if (connect)
			list.add(newCon);
		MainServer.setClientConnections(list);
	}
	
	/**
	 * Clears the table of client connections, effectively removing all clients from the server's list of active connections.
	 */
	public static void clearConnectionTable() {
		ObservableList<ClientConnection> list = MainServer.getConnections();
		list.clear();
		MainServer.setClientConnections(list);
	}
	
	/**
	 * Handles user login by mapping the user's name to their connection object. This facilitates
     * tracking individual user connections and sending messages to specific users.
     * 
	 * @param client The client's connection object.
	 * @param name The name of the user logging in.
	 */
	public static void handleUserLogin(ConnectionToClient client, String name) {
		MainServer.getInstance().getConnectionMap().put(name, client);
	}
	
	/**
	 * Checks if a client is already connected under a specific identifier.
	 * 
	 * @param connectedAs The identifier to check for an existing connection.
	 * @return True if a client is already connected with the given identifier; false otherwise.
	 */
	public static boolean clientAlreadyConnected(String connectedAs) {
		return MainServer.getInstance().getConnectionMap().get(connectedAs) != null;
	}
	
	/**
	 * Handles user logout by removing the user's connection from the server's tracking map.
	 * @param toLogout The identifier of the user to log out.
	 */
	public static void handleLogout(String toLogout) {
		if (clientAlreadyConnected(toLogout))
			MainServer.getInstance().getConnectionMap().remove(toLogout);
	}
}
