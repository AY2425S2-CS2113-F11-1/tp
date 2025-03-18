package busynessmanager;


import java.util.HashMap;

/**
 * RevenueCalculator is used to calculate the various revenues that is produced in the Busyness
 */
public class RevenueCalculator {
    private SalesManager sm;

    /**
     * Constructor
     * @param sm Pre-existing SalesManager to be used for instantiation
     */
    RevenueCalculator(SalesManager sm) {
        this.sm = sm;
    }

    /**
     * Sums up the total revenue of all Products in the SalesManager (which has an InventoryManager)
     * @return total revenue at this point of time
     */
    public double computeTotalRevenue() {
        InventoryManager currentInventory = sm.getInventory();
        HashMap<String, Product> currentProductList = currentInventory.returnProductList();

        double totalRevenue = 0;
        for (Product product : currentProductList.values()) {
            totalRevenue += product.getPrice() * product.getQuantitySold();
        }

        return totalRevenue;
    }

    /**
     * Returns the revenue of a given product
     * @param id The String ID of the product to be queried
     * @return Revenue from selling the given product
     */
    public double computeProductRevenue(String id) {
        InventoryManager currentInventory = sm.getInventory();
        HashMap<String, Product> currentProductList = currentInventory.returnProductList();

        // Possible error for invalid ID (product will be null)
        Product product = currentProductList.get(id);

        return product.getPrice() * product.getQuantitySold();
    }
}
