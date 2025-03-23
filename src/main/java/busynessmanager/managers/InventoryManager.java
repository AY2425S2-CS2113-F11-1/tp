package busynessmanager.managers;

import busynessmanager.product.Product;
import busynessmanager.ui.UI;

import static busynessmanager.constants.Constants.NEWLINE;
import static busynessmanager.constants.Constants.MINIMUM_VALUE;
import static busynessmanager.constants.Constants.PRODUCT_NOT_FOUND_FORMAT;
import static busynessmanager.constants.Constants.IM_LIST;
import static busynessmanager.constants.Constants.IM_EMPTY_MESSAGE;
import static busynessmanager.constants.Constants.IM_ADD_FORMAT;
import static busynessmanager.constants.Constants.IM_REMOVE_FORMAT;
import static busynessmanager.constants.Constants.IM_UPDATED_FORMAT;
import static busynessmanager.constants.Constants.IM_NAME_EXISTS_FORMAT;
import static busynessmanager.constants.Constants.IM_QTY_EXCEED_ERROR_MESSAGE;

import java.util.HashMap;
import java.util.Map;


/**
 * Manages the inventory of products, allowing operations such as adding, updating, and removing products.
 */
public class InventoryManager {
    private final HashMap<String, Product> productList;


    /**
     * Constructs an empty inventory manager.
     */
    public InventoryManager() {
        this.productList = new HashMap<>();
    }


    /**
     * Adds a new product to the inventory.
     *
     * @param name  The name of the product.
     * @param qty   The initial quantity of the product.
     * @param price The price of the product.
     */
    public void addProduct(String name, int qty, double price) {
        for (Product existingProduct : productList.values()) {
            if (existingProduct.getName().equalsIgnoreCase(name)) {
                UI.printFormattedMessage(IM_NAME_EXISTS_FORMAT + NEWLINE, name);
                return;
            }
        }

        Product product = new Product(name, qty, price);
        String productId = product.getId();

        productList.put(productId, product);
        UI.printFormattedMessage(IM_ADD_FORMAT + NEWLINE, product.toString());
    }

    /**
     * Deletes a product from the inventory by its unique ID.
     *
     * @param id The unique ID of the product to be removed.
     */
    public void deleteProduct(String id) {
        if (productList.containsKey(id)) {
            Product removedProduct = productList.remove(id);
            UI.printFormattedMessage(IM_REMOVE_FORMAT + NEWLINE, removedProduct.toString());
        } else {
            UI.printFormattedMessage(PRODUCT_NOT_FOUND_FORMAT+ NEWLINE, id);
        }
    }

    /**
     * Updates the details of an existing product.
     *
     * @param id    The unique ID of the product.
     * @param name  The new name of the product.
     * @param qty   The new quantity of the product.
     * @param price The new price of the product.
     */
    public void updateProduct(String id, String name, int qty, double price) {
        if (productList.containsKey(id)) {
            Product product = productList.get(id);

            product.setName(name);
            product.setQuantity(qty);
            product.setPrice(price);

            UI.printFormattedMessage(IM_UPDATED_FORMAT + NEWLINE, product.toString());

        } else {
            UI.printFormattedMessage(PRODUCT_NOT_FOUND_FORMAT + NEWLINE, id);
        }
    }

    /**
     * Prints all products in the inventory.
     * If the inventory is empty, displays an appropriate message.
     */
    public void printProducts() {
        if (productList.isEmpty()) {
            UI.printMessage(IM_EMPTY_MESSAGE);
            return;
        }

        UI.printMessage(IM_LIST);

        productList.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println(entry.getValue()));
    }

    /**
     * Updates the quantity of a product after a sale.
     * Ensures the quantity does not drop below the minimum allowed value.
     *
     * @param id       The unique ID of the product.
     * @param qtySold  The quantity sold.
     */
    protected boolean updateProductQuantity(String id, int qtySold) {
        Product product;

        if (productList.containsKey(id)) {
            product = productList.get(id);

            int currentQty = product.getQuantity();

            if (qtySold > currentQty) {
                UI.printMessage(IM_QTY_EXCEED_ERROR_MESSAGE);
                return false;
            } else {
                product.setQuantitySold(qtySold);
                product.setQuantity(Math.max(MINIMUM_VALUE, currentQty - qtySold));
                return true;
            }
        } else {
            UI.printFormattedMessage(PRODUCT_NOT_FOUND_FORMAT + NEWLINE, id);
            return false;
        }
    }

    /**
     * Resets the sales count of a product.
     *
     * @param id The unique ID of the product.
     */
    protected boolean resetProductSales(String id) {
        if (productList.containsKey(id)) {
            productList.get(id).setQuantitySold(MINIMUM_VALUE);
            return true;
        } else {
            UI.printFormattedMessage(PRODUCT_NOT_FOUND_FORMAT + NEWLINE, id);
            return false;
        }
    }

    /**
     * Returns the current list of products in the inventory.
     *
     * @return A HashMap containing product IDs and their corresponding Product objects.
     */
    public HashMap<String, Product> returnProductList() {
        return productList;
    }
}
