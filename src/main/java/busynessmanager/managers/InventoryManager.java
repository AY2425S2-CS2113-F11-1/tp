package busynessmanager.managers;

import busynessmanager.UI_Constants.UI;
//import static busynessmanager.UI_Constants.Constants.*; // This line will fail style checks
import static busynessmanager.UI_Constants.Constants.NEWLINE;
import static busynessmanager.UI_Constants.Constants.MINIMUM_VALUE;
import static busynessmanager.UI_Constants.Constants.IM_LIST;
import static busynessmanager.UI_Constants.Constants.IM_EMPTY_MESSAGE;
import static busynessmanager.UI_Constants.Constants.IM_ADD_FORMAT;
import static busynessmanager.UI_Constants.Constants.IM_REMOVE_FORMAT;
import static busynessmanager.UI_Constants.Constants.IM_UPDATED_FORMAT;
import static busynessmanager.UI_Constants.Constants.IM_NAME_EXISTS_FORMAT;
import static busynessmanager.UI_Constants.Constants.IM_PRODUCT_NOT_FOUND_FORMAT;

import busynessmanager.product.Product;

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
                //System.out.println("Error: A product with the name '" + name + "' already exists.");
                UI.printFormattedMessage(IM_NAME_EXISTS_FORMAT + NEWLINE, name);
                return;
            }
        }

        Product product = new Product(name, qty, price);
        String productId = product.getId();
        productList.put(productId, product);
        //System.out.println("Product added: " + product);
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
            //System.out.println("Product removed: " + removedProduct);
            UI.printFormattedMessage(IM_REMOVE_FORMAT + NEWLINE, removedProduct.toString());
        } else {
            //System.out.println("Product with ID " + id + " not found.");
            UI.printFormattedMessage(IM_PRODUCT_NOT_FOUND_FORMAT+ NEWLINE, id);
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
            //System.out.println("Product updated: " + product);
            UI.printFormattedMessage(IM_UPDATED_FORMAT + NEWLINE, product.toString());

        } else {
            //System.out.println("Product with ID " + id + " not found.");
            UI.printFormattedMessage(IM_PRODUCT_NOT_FOUND_FORMAT + NEWLINE, id);
        }
    }

    /**
     * Prints all products in the inventory.
     * If the inventory is empty, displays an appropriate message.
     */
    public void printProducts() {
        if (productList.isEmpty()) {
            //System.out.println("No products in inventory.");
            UI.printMessage(IM_EMPTY_MESSAGE);
            return;
        }
        //System.out.println("Product List:");
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
    protected void updateProductQuantity(String id, int qtySold) {
        Product product = productList.get(id);
        int currentQty = product.getQuantity();
        product.setQuantitySold(qtySold);
        product.setQuantity(Math.max(MINIMUM_VALUE, currentQty - qtySold));
    }

    /**
     * Resets the sales count of a product.
     *
     * @param id The unique ID of the product.
     */
    protected void resetProductSales(String id) {
        productList.get(id).setQuantitySold(MINIMUM_VALUE);
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
