//@@author LEESY02
package busynessmanager.managers;

import busynessmanager.product.Product;
import busynessmanager.ui.UI;

import static busynessmanager.constants.Constants.NEWLINE;
import static busynessmanager.constants.Constants.PRODUCT_NOT_FOUND_FORMAT;
import static busynessmanager.constants.Constants.SRM_ID_QUERY_FORMAT;
import static busynessmanager.constants.Constants.SRM_NAME_QUERY_FORMAT;
import static busynessmanager.constants.Constants.SRM_PRODUCT_NOT_FOUND_FORMAT;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


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
     * Returns ID of the given product Name.
     *
     * @param name Name of the product to query.
     */
    public void searchByName(String name) {
        HashMap<String, Product> currentProductList = this.inventory.returnProductList();
        Set<Map.Entry<String, Product>> mapSet = currentProductList.entrySet();

        for (Map.Entry<String, Product> entry : mapSet) {
            if (entry.getValue().getName().equalsIgnoreCase(name)) {
                String id = entry.getKey();

                UI.printFormattedMessage(SRM_ID_QUERY_FORMAT + NEWLINE, name, id);
                return;
            }
        }

        UI.printFormattedMessage(SRM_PRODUCT_NOT_FOUND_FORMAT + NEWLINE, name);
    }

    /**
     * Returns the product Name of the given product ID.
     *
     * @param id ID value of the Product to query.
     */
    public void searchById(String id) {
        HashMap<String, Product> currentProductList = this.inventory.returnProductList();

        if (currentProductList.containsKey(id)) {
            Product product = currentProductList.get(id);
            String name = product.getName();

            UI.printFormattedMessage(SRM_NAME_QUERY_FORMAT + NEWLINE, id, name);
        } else {
            UI.printFormattedMessage(PRODUCT_NOT_FOUND_FORMAT + NEWLINE, id);
        }
    }
}
