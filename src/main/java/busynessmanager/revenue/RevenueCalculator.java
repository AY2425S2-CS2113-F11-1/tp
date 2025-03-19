package busynessmanager.revenue;

import busynessmanager.UI_Constants.UI;
import busynessmanager.managers.InventoryManager;
import busynessmanager.managers.SalesManager;
import busynessmanager.product.Product;

import java.util.HashMap;

import static busynessmanager.UI_Constants.Constants.*;

/**
 * RevenueCalculator is used to calculate the various revenues that is produced in the Busyness
 */
public class RevenueCalculator {
    private final SalesManager sm;

    /**
     * Constructor
     * @param sm Pre-existing SalesManager to be used for instantiation
     */
    public RevenueCalculator(SalesManager sm) {
        this.sm = sm;
    }

    /**
     * Sums up the total revenue of all Products in the SalesManager (which has an InventoryManager)
     * @return total revenue at this point of time
     */
    public double computeTotalRevenue() {
        InventoryManager currentInventory = sm.getInventory();
        HashMap<String, Product> currentProductList = currentInventory.returnProductList();

        double totalRevenue = MINIMUM_VALUE;
        for (Product product : currentProductList.values()) {
            double individualRevenue = product.getPrice() * product.getQuantitySold();
            UI.printFormattedMessage(RC_INDIVIDUAL_REVENUE_FORMAT + NEWLINE,
                product.getName(), individualRevenue);
            totalRevenue += individualRevenue;
        }

        UI.printFormattedMessage(RC_TOTAL_REVENUE_FORMAT + NEWLINE, totalRevenue);

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

        double individualRevenue = product.getPrice() * product.getQuantitySold();
        UI.printFormattedMessage(RC_INDIVIDUAL_REVENUE_FORMAT + NEWLINE,
            product.getName(), individualRevenue);

        return individualRevenue;
    }
}
