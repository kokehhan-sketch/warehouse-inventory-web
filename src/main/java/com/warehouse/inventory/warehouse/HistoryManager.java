package com.warehouse.inventory.warehouse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryManager {

    private final List<MovementRecord> records = new ArrayList<>();

    public void recordMovement(String action, String productId, int quantity) {
        records.add(new MovementRecord(action, productId, quantity));
    }

    public List<MovementRecord> getAllRecords() {
        return Collections.unmodifiableList(records);
    }

    public List<MovementRecord> getRecordsForProduct(String productId) {
        List<MovementRecord> result = new ArrayList<>();

        for (MovementRecord record : records) {
            if (record.getProductId().equalsIgnoreCase(productId)) {
                result.add(record);
            }
        }

        return result;
    }

    public void printHistory() {
        if (records.isEmpty()) {
            System.out.println("No stock movement history available.");
            return;
        }

        System.out.println("=== Stock Movement History ===");
        for (MovementRecord record : records) {
            System.out.println(record);
        }
    }
}
