package busynessmanager.managers;

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
            System.out.println("Product with ID " + productId + " already exists.");
            return;
        }

        productList.put(productId, product);
        System.out.println("Product added: " + product);
    }

    // Delete a product by ID
    public void deleteProduct(String id) {
        if (productList.containsKey(id)) {
            Product removedProduct = productList.remove(id);
            System.out.println("Product removed: " + removedProduct);
        } else {
            System.out.println("Product with ID " + id + " not found.");
        }
    }

    // Update product details
    public void updateProduct(String id, String name, int qty, double price) {
        if (productList.containsKey(id)) {
            Product product = productList.get(id);

            product.setName(name);
            product.setQuantity(qty);
            product.setPrice(price);

            System.out.println("Product updated: " + product);
        } else {
            System.out.println("Product with ID " + id + " not found.");
        }
    }

    // Print all products in inventory
    public void printProducts() {
        if (productList.isEmpty()) {
            System.out.println("No products in inventory.");
            return;
        }
        System.out.println("Product List:");

        for (Map.Entry<String, Product> entry : productList.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    // Rozalie's portion
    protected void updateProductQuantity(String id, int qtySold) {
        Product product = productList.get(id);
        int currentQty = product.getQuantity();

        product.setQuantitySold(qtySold);
        product.setQuantity(Math.max(0, currentQty - qtySold));
    }

    protected void resetProductSales(String id) {
        productList.get(id).setQuantity(0);
    }

    // SY's portion
    public HashMap<String, Product> returnProductList() {
        return productList;
    }
}
