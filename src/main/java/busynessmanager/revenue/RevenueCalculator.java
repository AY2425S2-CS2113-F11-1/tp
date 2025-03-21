package busynessmanager.revenue;

import busynessmanager.product.Product;
import busynessmanager.managers.InventoryManager;
import busynessmanager.managers.SalesManager;
import busynessmanager.ui.UI;

import static busynessmanager.constants.Constants.MINIMUM_VALUE;
import static busynessmanager.constants.Constants.NEWLINE;
import static busynessmanager.constants.Constants.RC_INDIVIDUAL_REVENUE_FORMAT;
import static busynessmanager.constants.Constants.RC_TOTAL_REVENUE_FORMAT;

import java.util.HashMap;


/**
 * RevenueCalculator is used to calculate the various revenues that is produced in BusynessManager.
 */
public class RevenueCalculator {
    private final SalesManager sm;


    /**
     * Constructs the RevenueCalculator class using an existing SalesManager.
     *
     * @param sm Pre-existing SalesManager to be used for instantiation.
     */
    public RevenueCalculator(SalesManager sm) {
        this.sm = sm;
    }


    /**
     * Sums up the total revenue of all Products in the SalesManager (which has an InventoryManager).
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
     * Returns the revenue of a given product.
     *
     * @param id The String ID of the product to be queried.
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
