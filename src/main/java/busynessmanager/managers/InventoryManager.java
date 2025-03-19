package busynessmanager.managers;

import busynessmanager.UI_Constants.UI;
import static busynessmanager.UI_Constants.Constants.*;
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
        Product product = new Product(name, qty, price);  // Generates unique ID internally
        String productId = product.getId();  // Get the unique ID of the product
        if (productList.containsKey(productId)) {
            //System.out.println("Product with ID " + productId + " already exists.");
            UI.printFormattedMessage(IM_ID_EXISTS_FORMAT + NEWLINE, productId);
            return;
        }
        productList.put(productId, product);
        //System.out.println("Product added: " + product);
        UI.printFormattedMessage(IM_ADD_FORMAT + NEWLINE, product.toString());
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
            //System.out.println("No products in inventory.");
            UI.printMessage(IM_EMPTY_MESSAGE);
            return;
        }
        //System.out.println("Product List:");
        UI.printMessage(IM_LIST);
        for (Map.Entry<String, Product> entry : productList.entrySet()) {
            //System.out.println(entry.getValue());
            UI.printMessage(entry.getValue().toString());
        }
    }

    // Rozalie's portion
    protected void updateProductQuantity(String id, int qtySold) {
        Product product = productList.get(id);
        int currentQty = product.getQuantity();
        product.setQuantitySold(qtySold);
        product.setQuantity(Math.max(MINIMUM_VALUE, currentQty - qtySold));
    }

    protected void resetProductSales(String id) {
        productList.get(id).setQuantity(MINIMUM_VALUE);
    }

    // SY's portion
    public HashMap<String, Product> returnProductList() {
        return productList;
    }
}
