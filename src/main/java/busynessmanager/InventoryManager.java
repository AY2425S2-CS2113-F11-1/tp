package busynessmanager;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    /**
     * InventoryManager class but just the updated quantity through the sales part
     */
    private Map<String, Integer> productQuantity = new HashMap<>();

    public void updateProductQuantity(String id, int qtySold) {
        int currentQty = productQuantity.getOrDefault(id, 0);
        productQuantity.put(id, Math.max(0, currentQty - qtySold)); // Prevent negative quantities
    }

    public void resetProductSales(String id) {
        productQuantity.put(id, 0);
    }
}

