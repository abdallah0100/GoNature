package main.entities;

import java.util.Objects;

import ocsf.server.src.ConnectionToClient;

public class ClientConnection {
	
	private String clientIp, hostName, connectionStatus;
	
	public ClientConnection(ConnectionToClient client) {
		this.clientIp = client.getInetAddress().getHostAddress();
		this.hostName = client.getInetAddress().getHostName();
		connectionStatus = "Connected";
	}

	public ClientConnection(String string, String string2) {
		clientIp = string;
		hostName = string2;
		connectionStatus = "sss";
	}


	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(String connectionStatus) {
		this.connectionStatus = connectionStatus;
	}
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ClientConnection))
			return false;
		ClientConnection otherCon = (ClientConnection) other;
		
		return otherCon.getClientIp().equals(clientIp) && otherCon.getHostName().equals(hostName);
	}
	
	@Override
	public String toString() {
		return "[Host] " + this.hostName + ", [IP] " + this.clientIp + ", [Status] " + this.connectionStatus;
	}

}
