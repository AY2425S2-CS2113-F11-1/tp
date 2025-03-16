import java.util.concurrent.atomic.AtomicInteger;

public class Product {
    private static final AtomicInteger idCounter = new AtomicInteger(1);
    private final String id;
    private String name;
    private int quantity;
    private int quantitySold;
    private double price;

    // Constructor
    public Product(String name, int quantity, double price) {
        this.id = String.format("ID_%04d", idCounter.getAndIncrement()); // Generates unique ID like ID_0001
        this.name = name;
        this.quantity = quantity;
        this.quantitySold = 0;
        this.price = price;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public double getPrice() {
        return price;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // toString method for formatted product details
    @Override
    public String toString() {
        return String.format("%s: %s | Qty: %d | Sold: %d | Price: $%.2f",
                id, name, quantity, quantitySold, price);
    }
}
