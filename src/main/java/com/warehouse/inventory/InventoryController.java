package com.warehouse.inventory;

import org.springframework.web.bind.annotation.*;
import com.warehouse.inventory.warehouse.*;
import java.util.Collection;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") 
public class InventoryController {

    private final StockManager stockManager;
    private final HistoryManager historyManager;
    private final CommandManager commandManager;

    public InventoryController() {
        this.historyManager = new HistoryManager();
        this.stockManager = new StockManager(historyManager, new LowStockAlert());
        this.commandManager = new CommandManager();
        
        stockManager.addProduct(new Product("P100", "Rice", 40, 3.50, "Food", 10));
        stockManager.addProduct(new Product("P200", "Soap", 18, 2.20, "Household", 5));
    }

    @GetMapping("/products")
    public Collection<Product> getProducts() {
        return stockManager.viewAllProducts();
    }

 

    @PostMapping("/stockin")
    public String stockIn(@RequestParam String id, @RequestParam int quantity) {
        Command cmd = new StockInCommand(stockManager, id, quantity);
        boolean success = commandManager.executeCommand(cmd);
        return success ? "Success" : "Failed";
    }

    @PostMapping("/stockout")
    public String stockOut(@RequestParam String id, @RequestParam int quantity) {
        Command cmd = new StockOutCommand(stockManager, id, quantity);
        boolean success = commandManager.executeCommand(cmd);
        return success ? "Success" : "Failed";
    }

    @PostMapping("/undo")
    public String undo() {
        boolean success = commandManager.undoLastCommand();
        return success ? "Success" : "Failed";
    }
}