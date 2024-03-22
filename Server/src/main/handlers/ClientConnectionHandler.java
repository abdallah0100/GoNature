package main.handlers;

import javafx.collections.ObservableList;
import main.MainServer;
import main.entities.ClientConnection;
import ocsf.server.src.ConnectionToClient;

public class ClientConnectionHandler {

	public static void handleConnectRequest(ConnectionToClient client, boolean connect) {
		ClientConnection newCon = new ClientConnection(client);
		ObservableList<ClientConnection> list = MainServer.getConnections();

		//checking to prevent duplicates
		list.remove(newCon);
		if (connect)
			list.add(newCon);
		MainServer.setClientConnections(list);
	}
	
	public static void handleUserLogin(ConnectionToClient client, String name) {
		MainServer.getInstance().getConnectionMap().put(name, client);
	}
	
	public static boolean clientAlreadyConnected(String connectedAs) {
		return MainServer.getInstance().getConnectionMap().get(connectedAs) != null;
	}
	
	public static void handleLogout(String toLogout) {
		if (clientAlreadyConnected(toLogout))
			MainServer.getInstance().getConnectionMap().remove(toLogout);
	}
}
