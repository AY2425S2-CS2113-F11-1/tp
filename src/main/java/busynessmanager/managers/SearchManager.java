//@@author LEESY02
package busynessmanager.managers;

import busynessmanager.product.Product;
import busynessmanager.ui.UI;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static busynessmanager.constants.Constants.NEWLINE;
import static busynessmanager.constants.Constants.PRODUCT_NOT_FOUND_FORMAT;
import static busynessmanager.constants.Constants.SRM_ID_QUERY_FORMAT;
import static busynessmanager.constants.Constants.SRM_NAME_QUERY_FORMAT;
import static busynessmanager.constants.Constants.SRM_PRODUCT_NOT_FOUND_FORMAT;


/**
 * SearchManager is used to query through the InventoryManager and return different results based on the given queries.
 */
public class SearchManager {
    private final InventoryManager inventory;


    /**
     * Constructs the SearchManager class using an existing InventoryManager.
     *
     * @param inventory Pre-existing inventory to be used for instantiation
     */
    public SearchManager(InventoryManager inventory) {
        this.inventory = inventory;
    }


    /**
     * Prints the details of a product, given the Product name.
     *
     * @param name Name of the product to query.
     */
    public void searchByName(String name) {
        HashMap<String, Product> currentProductList = this.inventory.returnProductList();
        Set<Map.Entry<String, Product>> mapSet = currentProductList.entrySet();

        if (checkForProductByName(name, mapSet)) {
            return;
        }

        UI.printFormattedMessage(SRM_PRODUCT_NOT_FOUND_FORMAT + NEWLINE, name);
    }

    /**
     * Prints the details of a product, given the Product ID.
     *
     * @param id ID value of the Product to query.
     */
    public void searchById(String id) {
        HashMap<String, Product> currentProductList = this.inventory.returnProductList();

        checkForProductById(id, currentProductList);
    }

    /**
     * Sub method of searchByName.
     * Checks if a Product is present in the map, and prints a message to the user if it is.
     *
     * @param name The name of the Product to be queried.
     * @param mapSet The mapSet to query through.
     * @return Returns true if Product of String name is found in the mapSet.
     */
    private boolean checkForProductByName(String name, Set<Map.Entry<String, Product>> mapSet) {
        for (Map.Entry<String, Product> entry : mapSet) {
            boolean areNamesMatching = entry.getValue().getName().equalsIgnoreCase(name);

            if (areNamesMatching) {
                String id = entry.getKey();

                UI.printFormattedMessage(SRM_ID_QUERY_FORMAT + NEWLINE, name, id);
                UI.printMessage(entry.getValue().toString());
                return true;
            }
        }

        return false;
    }

    /**
     * Sub method of searchById.
     * Checks if a Product is present in the map, and prints a message to the user if it is.
     *
     * @param id The ID of the Product to be queried.
     * @param currentProductList The HashMap to query through.
     */
    private void checkForProductById(String id, HashMap<String, Product> currentProductList) {
        if (currentProductList.containsKey(id)) {
            Product product = currentProductList.get(id);
            String name = product.getName();

            UI.printFormattedMessage(SRM_NAME_QUERY_FORMAT + NEWLINE, id, name);
            UI.printMessage(product.toString());
        } else {
            UI.printFormattedMessage(PRODUCT_NOT_FOUND_FORMAT + NEWLINE, id);
        }
    }
}
