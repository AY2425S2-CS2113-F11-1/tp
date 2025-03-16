package busynessmanager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * SearchManager is used to query through the InventoryManager and return different results based on the given queries
 */
public class SearchManager {
    private InventoryManager inventory;

    /**
     * Constructor
     */
    public SearchManager() {
        inventory = new InventoryManager();
    }

    /**
     * Constructor
     * @param inventory Pre-existing inventory to be used for instantiation
     */
    public SearchManager(InventoryManager inventory) {
        this.inventory = inventory;
    }

    /**
     * Returns ID of the given product Name
     * @param name Name of the product to query
     * @return The key(ID) value of the given name (returns "INVALID_NAME" if an invalid name is provided)
     */
    public String searchByName(String name) {
        HashMap<String, Product> currentProductList = this.inventory.returnProductList();
        Set<Map.Entry<String, Product>> mapSet = currentProductList.entrySet();
        for (Map.Entry<String, Product> entry : mapSet) {
            if (entry.getValue().returnName().equals(name)) {
                return entry.getKey();
            }
        }
        // Possible avenue for error (Invalid name provided)
        return "INVALID_NAME";
    }

    /**
     * Returns the product Name of the given product ID
     * @param id ID value of the Product to query
     * @return The Product object with the matching ID value in the HashMap
     */
    public Product searchById(String id) {
        HashMap<String, Product> currentProductList = this.inventory.returnProductList();
        return currentProductList.get(id);
    }

}
