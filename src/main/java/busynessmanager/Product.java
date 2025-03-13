package busynessmanager;

/**
 * Placeholder class for SY's tasks, please remove during merge conflict
 */
public class Product {
    private String id;
    private String name;
    private int quantity;
    private int quantitySold;
    private double price;


    public Product(String name, int quantity, double price) {}  // Constructor

    @Override
    public String toString() {
        return "";
    }  // Returns formatted product details

    /**
     * required for SY's RevenueCalculator to function
     * @return price
     */
    protected double returnPrice() {
        return price;
    }

    /**
     * required for SY's RevenueCalculator to function
     * @return quantitySold
     */
    protected int returnQuantitySold() {
        return quantitySold;
    }

    /**
     * required for SY's SearchManager to function
     * @return quantitySold
     */
    protected String returnName() {
        return name;
    }

}
