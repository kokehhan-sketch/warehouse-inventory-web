package com.warehouse.inventory.warehouse;

import java.util.HashSet;
import java.util.Set;

public class LowStockAlert implements LowStockObserver {

    private final Set<String> activeAlerts = new HashSet<>();

    @Override
    public void update(Product product) {
        if (product == null) {
            return;
        }

        if (product.isLowStock()) {
            if (activeAlerts.add(product.getId())) {
                System.out.println("[Low Stock Alert] "
                        + product.getName()
                        + " (" + product.getId() + ") is below minimum stock. Current: "
                        + product.getQuantity()
                        + ", Minimum: "
                        + product.getMinStock());
            }
        } else if (activeAlerts.remove(product.getId())) {
            System.out.println("[Stock Restored] "
                    + product.getName()
                    + " (" + product.getId() + ") is back above the minimum stock level.");
        }
    }
}
