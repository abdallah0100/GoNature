package main.handlers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import entities.InboxMessage;
import main.Constants;
import main.MainServer;

public class InboxRequestHandler {

	public static void addMessage(String id, InboxMessage msg) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return;
		}
		try {
			String str = "INSERT INTO inbox (visitorID, title, content) VALUES(?,?,?)";
			PreparedStatement pr = MainServer.dbConnection.prepareStatement(str);
			pr.setString(1, id);
			pr.setString(2, msg.getTitle());
			pr.setString(3, msg.getContent());
			if (!(pr.executeUpdate() > 0))
				System.out.println("[InboxRequestHandler] - failed to execute addMessage");
		}catch(Exception ex) {
			System.out.println("[InboxRequestHandler] - failed to execute addMessage");
			ex.printStackTrace();
			return;
		}
	}
	
	public static InboxMessage[] getAllMessages(String id) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return null;
		}
		ArrayList<InboxMessage> arr = new ArrayList<>();
		try {
			String str = "SELECT * FROM inbox WHERE visitorID='"+id+"'";
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery(str);
			
			while(rs.next()) {
				arr.add(new InboxMessage(rs.getInt("msgId"), rs.getString("title"), rs.getString("content")));
			}
			return (InboxMessage[])arr.toArray(new InboxMessage[arr.size()]);  
		}catch(Exception ex) {
			System.out.println("[VisitorRequestHandler] - failed to execute query");
			ex.printStackTrace();
			return null;
		}
	}
	
	public static boolean deleteMsg(int id) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {
			String str = "DELETE FROM inbox WHERE msgId='"+id+"'";
			Statement st = MainServer.dbConnection.createStatement();
			return st.executeUpdate(str) > 0;
		}catch(Exception ex) {
			System.out.println("[InboxRequestHandler] - failed to execute deleteMsg");
			ex.printStackTrace();
			return false;
		}
	}
	
}
