package busynessmanager;

import java.util.List;
import java.util.HashMap;

/**
 * Placeholder class for my tasks, please remove during merge conflict
 */
public class InventoryManager {
    private HashMap<String, Product> productList;

    public void addProduct(String name, int qty, double price) {}
    public void deleteProduct(String id) {}
    public void updateProduct(String id, String name, int qty, double price) {}
    public void printProducts() {}

    protected HashMap<String, Product> returnProductList() {
        return productList;
    }
}
