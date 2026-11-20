package com.warehouse.inventory.warehouse;

public class StockInCommand implements Command {

    private final StockManager stockManager;
    private final String productId;
    private final int quantity;

    public StockInCommand(StockManager stockManager, String productId, int quantity) {
        this.stockManager = stockManager;
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public boolean execute() {
        return stockManager.stockIn(productId, quantity);
    }

    @Override
    public boolean undo() {
        return stockManager.reverseStockIn(productId, quantity);
    }

    @Override
    public String getName() {
        return "Stock In";
    }
}
