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
import static busynessmanager.UI_Constants.Constants.IM_ID_EXISTS_FORMAT;
import static busynessmanager.UI_Constants.Constants.IM_PRODUCT_NOT_FOUND_FORMAT;

import busynessmanager.product.Product;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private final HashMap<String, Product> productList;

    public InventoryManager() {
        this.productList = new HashMap<>();
    }

    // Add a new product
    public void addProduct(String name, int qty, double price) {

        for (Product existingProduct : productList.values()) {
            if (existingProduct.getName().equalsIgnoreCase(name)) { // Case-insensitive check
                System.out.println("Error: A product with the name '" + name + "' already exists.");
                return;
            }
        }

        Product product = new Product(name, qty, price);
        String productId = product.getId();
        productList.put(productId, product);
        System.out.println("Product added: " + product);
    }

    // Delete a product by ID
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

    // Update product details
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

    // Print all products in inventory
    public void printProducts() {
        if (productList.isEmpty()) {
            System.out.println("No products in inventory.");
            return;
        }
        System.out.println("Product List:");
        productList.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println(entry.getValue()));
    }

    // Rozalie's portion
    protected void updateProductQuantity(String id, int qtySold) {
        Product product = productList.get(id);
        int currentQty = product.getQuantity();
        product.setQuantitySold(qtySold);
        product.setQuantity(Math.max(MINIMUM_VALUE, currentQty - qtySold));
    }

    protected void resetProductSales(String id) {
        productList.get(id).setQuantitySold(MINIMUM_VALUE);
    }

    // SY's portion
    public HashMap<String, Product> returnProductList() {
        return productList;
    }
}
