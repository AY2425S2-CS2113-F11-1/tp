// @@author LEESY02
package busynessmanager.revenue;

import busynessmanager.product.Product;
import busynessmanager.managers.InventoryManager;
import busynessmanager.managers.SalesManager;
import busynessmanager.ui.UI;

import java.util.HashMap;

import static busynessmanager.constants.Constants.MINIMUM_VALUE;
import static busynessmanager.constants.Constants.NEWLINE;
import static busynessmanager.constants.Constants.PRODUCT_NOT_FOUND_FORMAT;
import static busynessmanager.constants.Constants.RC_INDIVIDUAL_REVENUE_FORMAT;
import static busynessmanager.constants.Constants.RC_TOTAL_REVENUE_FORMAT;


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
        HashMap<String, Product> currentProductList = returnProductList();

        double totalRevenue = MINIMUM_VALUE;

        for (Product product : currentProductList.values()) {
            double individualRevenue = computeIndividualRevenue(product);

            printProductRevenue(product, individualRevenue);

            totalRevenue += individualRevenue;
        }

        assert totalRevenue >= MINIMUM_VALUE;

        UI.printFormattedMessage(RC_TOTAL_REVENUE_FORMAT + NEWLINE, totalRevenue);

        return totalRevenue;
    }

    /**
     * Returns the revenue of a given product.
     *
     * @param id The String ID of the product to be queried.
     */
    public double computeProductRevenue(String id) {
        HashMap<String, Product> currentProductList = returnProductList();

        Product product;

        if (currentProductList.containsKey(id)) {
            product = currentProductList.get(id);
        } else {
            UI.printFormattedMessage(PRODUCT_NOT_FOUND_FORMAT + NEWLINE, id);
            return MINIMUM_VALUE;
        }

        double individualRevenue = computeIndividualRevenue(product);

        assert individualRevenue >= MINIMUM_VALUE;

        printProductRevenue(product, individualRevenue);

        return individualRevenue;
    }

    /**
     * Returns the Hashmap containing the String ID (key) and Product (value) of the current SalesManager instantiated
     * in the project
     *
     * @return The Hashmap of String IDs and Product products
     */
    private HashMap<String, Product> returnProductList() {
        InventoryManager currentInventory = sm.getInventory();

        return currentInventory.returnProductList();
    }

    /**
     * Sub-method of computeProductRevenue
     * Calculates the revenue of a given product
     *
     * @param product The product to be calculated
     * @return The revenue of the product
     */
    private double computeIndividualRevenue(Product product) {
        return product.getRevenue();
    }

    /**
     * Sub-method of computeProductRevenue
     * Prints the revenue earned from the specified product
     *
     * @param product The product to be printed
     * @param individualRevenue The revenue earned from the product
     */
    private void printProductRevenue(Product product, double individualRevenue) {
        UI.printFormattedMessage(RC_INDIVIDUAL_REVENUE_FORMAT + NEWLINE,
            product.getName(), individualRevenue);
    }
}
