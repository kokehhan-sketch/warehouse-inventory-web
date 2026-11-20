package com.warehouse.inventory.warehouse;

public class StockOutCommand implements Command {

    private final StockManager stockManager;
    private final String productId;
    private final int quantity;

    public StockOutCommand(StockManager stockManager, String productId, int quantity) {
        this.stockManager = stockManager;
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public boolean execute() {
        return stockManager.stockOut(productId, quantity);
    }

    @Override
    public boolean undo() {
        return stockManager.reverseStockOut(productId, quantity);
    }

    @Override
    public String getName() {
        return "Stock Out";
    }
}
