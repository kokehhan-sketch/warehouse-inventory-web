package com.warehouse.inventory.warehouse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MovementRecord {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final String action;
    private final String productId;
    private final int quantity;
    private final LocalDateTime timestamp;

    public MovementRecord(String action, String productId, int quantity) {
        this.action = action;
        this.productId = productId;
        this.quantity = quantity;
        this.timestamp = LocalDateTime.now();
    }

    public String getAction() {
        return action;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return FORMATTER.format(timestamp)
                + " | " + action
                + " | Product: " + productId
                + " | Qty: " + quantity;
    }
}
