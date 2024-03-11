package main.handlers;

import javafx.collections.ObservableList;
import main.MainServer;
import main.entities.ClientConnection;
import ocsf.server.src.ConnectionToClient;

public class ClientConnectionHandler {

	public static void addNewConnection(ConnectionToClient client) {
		ClientConnection newCon = new ClientConnection(client);
		ObservableList<ClientConnection> list = MainServer.getInstance().getConnections();

		//checking to prevent duplicates
		for (ClientConnection c : list) {
			if (c.equals(newCon)) {
				list.remove(c);
				break;
			}
		}

		list.add(newCon);
		MainServer.getInstance().setClientConnections(list);
	}
	
}
