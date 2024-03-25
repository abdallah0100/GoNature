package main.entities;

import ocsf.server.src.ConnectionToClient;

/**
 * The ClientConnection class represents a client's connection within a server-client architecture.
 * It contains details about a client's connection, including the IP address, host name,
 * and current connection status. This class is used to manage and track client connections.
 */
public class ClientConnection {
	
	private String clientIp, hostName, connectionStatus;
	
	/**
	 * Initializes a new instance of ClientConnection using a ConnectionToClient object.
	 * @param client An instance of ConnectionToClient representing an active client connection.
	 */
	public ClientConnection(ConnectionToClient client) {
		this.clientIp = client.getInetAddress().getHostAddress();
		this.hostName = client.getInetAddress().getHostName();
		connectionStatus = "Connected";
	}

	/**
	 * Initializes a new instance of ClientConnection using explicit values for the client's IP address and host name.
	 * 
	 * @param string The IP address of the client.
	 * @param string2 The host name of the client.
	 */
	public ClientConnection(String string, String string2) {
		clientIp = string;
		hostName = string2;
		connectionStatus = "Connected";
	}

	/**
	 * @return A String representing the client's IP address.
	 */
	public String getClientIp() {
		return clientIp;
	}

	/**
	 * Sets the IP address of the client.
	 * 
	 * @param clientIp The new IP address for the client.
	 */
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	/**
	 * @return A String representing the client's host name.
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * Sets the host name of the client.
	 * 
	 * @param hostName The new host name for the client.
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * @return A String representing the connection status, typically "Connected".
	 */
	public String getConnectionStatus() {
		return connectionStatus;
	}

	/**
	 * Sets the current connection status of the client.
	 * 
	 * @param connectionStatus The new connection status for the client.
	 */
	public void setConnectionStatus(String connectionStatus) {
		this.connectionStatus = connectionStatus;
	}	

	/**
	 * Compares this ClientConnection instance with another object to determine equality.
	 * Two ClientConnection instances are considered equal if their IP addresses and host names are equal.
	 *
	 * @param other The object to compare with this instance.
	 * @return true if the specified object is equal to this instance; false otherwise
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ClientConnection))
			return false;
		ClientConnection otherCon = (ClientConnection) other;
		
		return otherCon.getClientIp().equals(clientIp) && otherCon.getHostName().equals(hostName);
	}
	
	/**
	 * @return A String representation of this instance.
	 */
	@Override
	public String toString() {
		return "[Host] " + this.hostName + ", [IP] " + this.clientIp + ", [Status] " + this.connectionStatus;
	}

}
