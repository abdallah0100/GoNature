package main.controllers;

import java.util.ArrayList;

import OCSF.src.ocsf.server.ConnectionToClient;

public class ServerController {
	
	private static ServerController controller= null;

	private ArrayList<ConnectionToClient> clients;
	
	private ServerController() {
		clients = new ArrayList<>();
	}
	
	public boolean addClient(ConnectionToClient newClient) {
		if (!clients.contains(newClient)) {
			clients.add(newClient);
			return true;
		}
		return false;
	}
	
	public boolean removeClient(ConnectionToClient c) {
		return clients.remove(c);
	}
	
	//remove null clients
	public void cleanClients() {
		for (int i =0; i < clients.size(); i++) 
			if (clients.get(i).toString() == null) 
					clients.remove(i);
	}
	
	public static ServerController getInstance() {
		if (controller == null)
			controller = new ServerController();
		return controller;
	}
	
	public ArrayList<ConnectionToClient> getClients(){
		cleanClients();
		return clients;
	}
	
}
