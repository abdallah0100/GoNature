package main.entities;

import entities.InboxMessage;
import entities.Order;

/**
 * The ConfirmationMessage class represents a message confirming an order.
 * It extends the InboxMessage class and includes an Order object.
 */
public class ConfirmationMessage extends InboxMessage {
    
    /**
     * Serial Version UID for serialization.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * The order associated with the confirmation message.
     */
    private Order order;

    /**
     * Constructs a ConfirmationMessage object with the specified title, content, and order.
     * @param title   The title of the confirmation message.
     * @param content The content of the confirmation message.
     * @param order   The order associated with the confirmation message.
     */
    public ConfirmationMessage(String title, String content, Order order) {
        super(-1, title, content);
        this.setOrder(order);
    }

    /**
     * Retrieves the order associated with the confirmation message.
     * @return The order associated with the confirmation message.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets the order associated with the confirmation message.
     * @param order The order to be associated with the confirmation message.
     */
    public void setOrder(Order order) {
        this.order = order;
    }
}
