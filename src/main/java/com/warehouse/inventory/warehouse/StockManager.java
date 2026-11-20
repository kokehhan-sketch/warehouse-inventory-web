package com.warehouse.inventory.warehouse;

import java.util.*;

public class StockManager {

    private Map<String, Product> products;
    private HistoryManager historyManager;
    private List<LowStockObserver> observers;

    public StockManager() {
        this(null);
    }

    public StockManager(HistoryManager historyManager, LowStockObserver... initialObservers) {
        products = new HashMap<>();
        this.historyManager = historyManager;
        observers = new ArrayList<>();

        if (initialObservers != null) {
            observers.addAll(Arrays.asList(initialObservers));
        }
    }

    public void setHistoryManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }

    public void addObserver(LowStockObserver observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    private void notifyAllObservers(Product product) {
        for (LowStockObserver o : observers) {
            o.update(product);
        }
    }

     //File I/O Related
    public void setProducts(Map<String, Product> loadedProducts) {
        if (loadedProducts != null) {
            this.products = new HashMap<>(loadedProducts);
        }
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    //CRUD
    public boolean addProduct(Product product) {
        if (product == null
                || product.getQuantity() < 0
                || products.containsKey(product.getId())){
            return false;
        }

        products.put(product.getId(), product);

        recordHistory("ADD", product.getId(), product.getQuantity());
        notifyAllObservers(product);

        return true;
    }

    public Product viewProductById(String productId) {
        return products.get(productId);
    }

    public Collection<Product> viewAllProducts() {
        return products.values();
    }

    public boolean deleteProduct(String productId) {
        Product removed = products.remove(productId);

        if (removed != null) {
            recordHistory("DELETE", productId, removed.getQuantity());
        }

        return removed != null;
    }

    public boolean updateProduct(String id, String name, double price, int minStock) {
        Product product = products.get(id);
        if (product == null) return false;

        product.setName(name);
        product.setPrice(price);
        product.setMinStock(minStock);

        recordHistory("UPDATE", id, product.getQuantity());
        notifyAllObservers(product);

        return true;
    }

    public boolean assignType(String id, String type) {
        Product product = products.get(id);
        if (product == null) return false;

        product.setType(type);
        recordHistory("ASSIGN_TYPE", id, 0);

        return true;
    }

    //Stock Related
    public boolean stockIn(String productId, int quantity) {
        if (quantity <= 0) return false;
        return applyStockChange(productId, quantity, "STOCK_IN");
    }

    public boolean stockOut(String productId, int quantity) {
        if (quantity <= 0) return false;
        return applyStockChange(productId, -quantity, "STOCK_OUT");
    }

    public boolean reverseStockIn(String productId, int quantity) {
        return applyStockChange(productId, -quantity, "UNDO_STOCK_IN");
    }

    public boolean reverseStockOut(String productId, int quantity) {
        return applyStockChange(productId, quantity, "UNDO_STOCK_OUT");
    }

    //Search
    public Product searchByProductId(String productId) {
        return products.get(productId); // O(1)
    }

    public List<Product> searchByName(String name) {
        List<Product> result = new ArrayList<>();

        for (Product product : products.values()) {
            if (product.getName().equalsIgnoreCase(name)) {
                result.add(product);
            }
        }

        return result;
    }

    public List<Product> searchByType(String type) {
        List<Product> result = new ArrayList<>();

        for (Product product : products.values()) {
            if (product.getType() != null &&
                product.getType().equalsIgnoreCase(type)) {
                result.add(product);
            }
        }

        return result;
    }

    //Low Stock Products
    public List<Product> getLowStockProducts() {
        List<Product> result = new ArrayList<>();

        for (Product product : products.values()) {
            if (product.isLowStock()) {
                result.add(product);
            }
        }

        return result;
    }

    public void printLowStockReport() {
        List<Product> lowStockProducts = getLowStockProducts();

        if (lowStockProducts.isEmpty()) {
            System.out.println("No low stock products.");
        } else {
            System.out.println("=== Low Stock Report ===");
            for (Product product : lowStockProducts) {
                System.out.println(product);
            }
        }
    }


    //Valuation
    public double calculateTotalInventoryValue() {
        double total = 0;

        for (Product product : products.values()) {
            total += product.getQuantity() * product.getPrice();
        }

        return total;
    }

    private boolean applyStockChange(String productId, int quantityDelta, String action) {
        if (quantityDelta == 0) return false;

        Product product = products.get(productId);
        if (product == null) return false;

        int newQuantity = product.getQuantity() + quantityDelta;
        if (newQuantity < 0) return false;

        product.setQuantity(newQuantity);
        recordHistory(action, productId, Math.abs(quantityDelta));
        notifyAllObservers(product);

        return true;
    }

    private void recordHistory(String action, String productId, int quantity) {
        if (historyManager != null) {
            historyManager.recordMovement(action, productId, quantity);
        }
    }
}
