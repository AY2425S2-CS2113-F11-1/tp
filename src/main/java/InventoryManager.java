import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private final HashMap<String, Product> productList;

    public InventoryManager() {
        this.productList = new HashMap<>();
    }

    // Add a new product
    public void addProduct(String id, String name, int qty, double price) {
        if (productList.containsKey(id)) {
            System.out.println("Product with ID " + id + " already exists.");
            return;
        }
        Product product = new Product(id, name, qty, price);
        productList.put(id, product);
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
}
