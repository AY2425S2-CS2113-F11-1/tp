package busynessmanager;

public class SalesManager {
    private InventoryManager inventory;

    public SalesManager(InventoryManager inventory) {
            this.inventory = inventory;
        }

    /**
     *  Record a sale of a quantity of a product by its ID
      */
    public void recordSale(String id, int qtySold) {
        if (qtySold <= 0) {
            System.out.println("Quantity sold must be greater than 0.");
            return;
        }
        inventory.updateProductQuantity(id, qtySold);
        System.out.println("Sale recorded: Product ID " + id + ", Quantity Sold: " + qtySold);
    }

    /**
     * Clear sales for a product by its ID
     * @param id product ID
     */
    public void clearSales(String id) {
        inventory.resetProductSales(id);
        System.out.println("Sales cleared: Product ID " + id);
        }
    }
