package busynessmanager;

/**
 * Placeholder class for SY's tasks, please remove during merge conflict
 */
public class SalesManager {
    private InventoryManager inventory;

    public void recordSale(String id, int qtySold) {}
    public void clearSales(String id) {}

    /**
     * required for SY's RevenueCalculator to function
     * @return the inventory
     */
    protected InventoryManager getInventory() {
        return inventory;
    }
}
