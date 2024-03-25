package main.entities;

import entities.InboxMessage;
import entities.Order;

public class ConfirmationMessage extends InboxMessage{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Order order;

	public ConfirmationMessage(String title, String content, Order order) {
		super(-1, title, content);
		this.setOrder(order);
		
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	

}
