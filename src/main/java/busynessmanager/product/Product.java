//@@author himethcodes
package busynessmanager.product;

import static busynessmanager.constants.Constants.INDEX_0;
import static busynessmanager.constants.Constants.INDEX_1;
import static busynessmanager.constants.Constants.ID_FORMAT;
import static busynessmanager.constants.Constants.PRODUCT_FORMAT;

/**
 * Represents a product in the inventory.
 * Each product has a unique ID, name, quantity, quantity sold, and price.
 */
public class Product {
    private static int idCounter = INDEX_1; // Simple counter for unique IDs
    private final String id;
    private String name;
    private int quantity;
    private int quantitySold;
    private double price;

    /**
     * Constructs a new Product with the specified name, quantity, and price.
     * A unique ID is automatically generated.
     *
     * @param name     The name of the product.
     * @param quantity The initial stock quantity of the product.
     * @param price    The price of the product.
     */
    public Product(String name, int quantity, double price) {
        this.id = String.format(ID_FORMAT, idCounter++); // Generates unique ID like ID_0001
        this.name = name;
        this.quantity = quantity;
        this.quantitySold = INDEX_0;
        this.price = price;
    }

    /**
     * Constructs a new Product with the specified ID, name, quantity, quantity sold and price.
     * This constructor is used when loading from the text file.
     *
     * @param id     The ID of the product.
     * @param name     The name of the product.
     * @param quantity The initial stock quantity of the product.
     * @param quantitySold The amount of the product that was sold.
     * @param price    The price of the product.
     */
    public Product(String id, String name, int quantity, int quantitySold, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.quantitySold = quantitySold;
        this.price = price;
    }

    /**
     * Sets the idCounter value to the given input
     *
     * @param newCounter The new idCounter value
     */
    public static void setIdCounter(int newCounter) {
        idCounter = newCounter;
    }

    /**
     * Gets the unique product ID.
     *
     * @return The product ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the name of the product.
     *
     * @return The product name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the current stock quantity of the product.
     *
     * @return The quantity in stock.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the total quantity sold of the product.
     *
     * @return The quantity sold.
     */
    public int getQuantitySold() {
        return quantitySold;
    }

    /**
     * Gets the price of the product.
     *
     * @return The price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the name of the product.
     *
     * @param name The new name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the stock quantity of the product.
     *
     * @param quantity The new stock quantity.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the total quantity sold of the product.
     *
     * @param quantitySold The updated quantity sold.
     */
    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    /**
     * Sets the price of the product.
     *
     * @param price The new price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns a formatted string representation of the product.
     *
     * @return A formatted string containing product details.
     */
    @Override
    public String toString() {
        return String.format(PRODUCT_FORMAT, id, name, quantity, quantitySold, price);
    }
}
