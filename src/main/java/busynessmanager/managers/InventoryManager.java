//@@author himethcodes
package busynessmanager.managers;

import busynessmanager.product.Product;
import busynessmanager.ui.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static busynessmanager.constants.Constants.INDEX_0;
import static busynessmanager.constants.Constants.INDEX_1;
import static busynessmanager.constants.Constants.INDEX_2;
import static busynessmanager.constants.Constants.INDEX_3;
import static busynessmanager.constants.Constants.INDEX_4;
import static busynessmanager.constants.Constants.INDEX_5;
import static busynessmanager.constants.Constants.NEWLINE;
import static busynessmanager.constants.Constants.MINIMUM_VALUE;
import static busynessmanager.constants.Constants.PRODUCT_NOT_FOUND_FORMAT;
import static busynessmanager.constants.Constants.FILE_REGEX;
import static busynessmanager.constants.Constants.IM_LIST;
import static busynessmanager.constants.Constants.IM_EMPTY_MESSAGE;
import static busynessmanager.constants.Constants.IM_ADD_FORMAT;
import static busynessmanager.constants.Constants.IM_REMOVE_FORMAT;
import static busynessmanager.constants.Constants.IM_UPDATED_FORMAT;
import static busynessmanager.constants.Constants.IM_NAME_EXISTS_FORMAT;
import static busynessmanager.constants.Constants.IM_NEGATIVE_QUANTITY_PRICE_MESSAGE;
import static busynessmanager.constants.Constants.IM_QTY_EXCEED_ERROR_MESSAGE;


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

        Product product;

        if (qty < MINIMUM_VALUE || price <= MINIMUM_VALUE) {
            UI.printMessage(IM_NEGATIVE_QUANTITY_PRICE_MESSAGE);
            return;
        } else {
            product = new Product(name, qty, price);
        }

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

            if (qty < MINIMUM_VALUE || price <= MINIMUM_VALUE) {
                UI.printMessage(IM_NEGATIVE_QUANTITY_PRICE_MESSAGE);
                return;
            } else {
                product.setName(name);
                product.setQuantity(qty);
                product.setPrice(price);
            }

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


    //@@author rozaliesmit
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
            int currentQtySold = product.getQuantitySold();

            if (qtySold > currentQty) {
                UI.printMessage(IM_QTY_EXCEED_ERROR_MESSAGE);
                return false;
            } else {
                product.setQuantitySold(currentQtySold + qtySold);
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

    //@@author LEESY02
    /**
     * Returns the current list of products in the inventory.
     *
     * @return A HashMap containing product IDs and their corresponding Product objects.
     */
    public HashMap<String, Product> returnProductList() {
        return productList;
    }

    //@@author amirhusaini06
    /**
     * Retrieves the inventory data as a string for saving to a file.
     *
     * @return A formatted string representing the inventory data.
     */
    public String getInventoryData() {
        StringBuilder data = new StringBuilder();

        for (Product product : productList.values()) {
            data.append(product.getId()).append(FILE_REGEX)
                    .append(product.getName()).append(FILE_REGEX)
                    .append(product.getQuantity()).append(FILE_REGEX)
                    .append(product.getQuantitySold()).append(FILE_REGEX)
                    .append(product.getPrice()).append(NEWLINE);
        }

        return data.toString();
    }

    /**
     * Loads inventory data from a buffered reader.
     *
     * @param reader The buffered reader containing inventory data.
     * @throws IOException If an I/O error occurs while reading.
     */
    public void loadInventory(BufferedReader reader) throws IOException {
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(FILE_REGEX);

            if (parts.length == INDEX_5) {
                String id = parts[INDEX_0];
                String name = parts[INDEX_1];
                int quantity = Integer.parseInt(parts[INDEX_2]);
                int quantitySold = Integer.parseInt(parts[INDEX_3]);
                double price = Double.parseDouble(parts[INDEX_4]);

                productList.put(id, new Product(name, quantity, quantitySold, price));
            }
        }
    }
}
