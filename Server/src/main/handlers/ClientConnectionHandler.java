package main.handlers;

import javafx.collections.ObservableList;
import main.MainServer;
import main.entities.ClientConnection;
import ocsf.server.src.ConnectionToClient;

public class ClientConnectionHandler {

	public static void addNewConnection(ConnectionToClient client) {
		ClientConnection newCon = new ClientConnection(client);
		ObservableList<ClientConnection> list = MainServer.getConnections();

		//checking to prevent duplicates
		for (ClientConnection c : list) {
			if (c.equals(newCon)) {
				list.remove(c);
				break;
			}
		}

		list.add(newCon);
		MainServer.setClientConnections(list);
		dumb();
	}
	public static void dumb() {
		for (ClientConnection c : MainServer.getConnections())
			System.out.println(c.toString());
	}
	
}
