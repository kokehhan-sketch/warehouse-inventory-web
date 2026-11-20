package com.warehouse.inventory.warehouse;

public interface LowStockObserver {
    void update(Product product);
}
