package entities;

import java.io.Serializable;

/**
 * Class InboxMessage saves all data related to a message sent to a visitor.
 * The message is sent when the visitor makes a reservation or when he enters a waiting list.
 * The class contains a couple of message contents like title, the content the id and if it was deleted.
 */
public class InboxMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	private String title; // The message title.
	private String content; // The message content.
	private int id; // The message id.
	private boolean deleted; // Flag indicating if the message is deleted. 
	
	/**
	 * Constructs a new InboxMessage with the id, title and content.
	 * @param id
	 * @param title
	 * @param content
	 */
	public InboxMessage(int id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}
	
	/**
	 * Constructs a new InboxMessage with the message title and content. 
	 * @param title
	 * @param content
	 */
	public InboxMessage(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	/**
	 * @return The message title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Sets the message title.
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return The message content.
	 */
	public String getContent() {
		return content;
	}
	/**
	 * Sets the message content.
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return The message id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the message id.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return True if the message is deleted false otherwise. 
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets true if the message is deleted false otherwise.
	 * @param deleted
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}	
}
