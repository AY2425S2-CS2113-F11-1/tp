package busynessmanager.parser;

public class CommandParser {

    /**
     * Constructs the CommandParser class.
     * NOTE: temporary constructor for JUnit testing.
     */
    public CommandParser() {}

    /**
     * Parses the user input for execution.
     *
     * @param input Input from the user.
     */
    public void parseCommand(String input) {
        // These two Strings are placeholders. (to be replaced by method calling in another PR)
        String command = input.substring(0, 5);
        String info = input.substring(5);

        executeCommand(command, info);
    }

    /**
     * Executes the appropriate method based on the command keyword.
     *
     * @param command Command keyword from the user input.
     * @param info Information related to the command keyword.
     */
    protected void executeCommand(String command, String info) {
        switch (command) {
        case "add":
            addProduct(info);
            break;

        case "delete":
            deleteProduct(info);
            break;

        case "update":
            updateProduct(info);
            break;
        case "print":
            printProducts();
            break;
        case "sold":
            recordSale(info);
            break;
        case "clear":
            clearSales(info);
            break;
        case "revenue":
            computeRevenue(info);
            break;
        case "search":
            searchForProduct(info);
            break;
        default:
            break;
        }
    }

    /**
     * Splits the information String into the product name, quantity and price.
     * It then calls addProduct() from the InventoryManager class.
     *
     * @param info Information related to the command keyword.
     */
    protected void addProduct(String info) {
        String[] components = splitInfo(info);

        String productName = components[1];
        int productQuantity = parseInt(components[3]);
        double productPrice = parseDouble(components[5]);

        //InventoryManager.addProduct(productName, productQuantity, productPrice);
    }

    /**
     * Extracts the product ID from the information String.
     * It then calls deleteProduct() from the InventoryManager class.
     *
     * @param info Information related to the command keyword.
     */
    protected void deleteProduct(String info) {
        String[] components = splitInfo(info);

        String productID = components[1];

        //InventoryManager.deleteProduct(productID);
    }

    /**
     * Splits the information String into the product ID, updated name, updated quantity and updated price.
     * It then calls updateProduct() from the InventoryManager class.
     *
     * @param info Information related to the command keyword.
     */
    protected void updateProduct(String info) {
        String[] components = splitInfo(info);

        String productID = components[1];
        String productNewName = components[3];
        int productNewQuantity = parseInt(components[5]);
        double productNewPrice = parseDouble(components[7]);

        //InventoryManager.updateProduct(productID, productNewName, productNewQuantity, productNewPrice);
    }

    /**
     * Calls printProducts() from the InventoryManager class.
     */
    protected void printProducts() {
        //InventoryManager.printProducts();
    }

    /**
     * Splits the information String into the product ID, and amount of product that was sold.
     * It then calls recordSale() from the SalesManager class.
     *
     * @param info Information related to the command keyword.
     */
    protected void recordSale(String info) {
        String[] components = splitInfo(info);

        String productID = components[1];
        int quantitySold = parseInt(components[3]);

        //SalesManager.recordSale(productID, quantitySold);
    }

    /**
     * Extracts the product ID from the information String.
     * It then calls clearSales() from the SalesManager class.
     *
     * @param info Information related to the command keyword.
     */
    protected void clearSales(String info) {
        String[] components = splitInfo(info);

        String productID = components[1];

        //SalesManager.clearSales(productID);
    }

    /**
     * Calls computeTotalRevenue() from the RevenueCalculator class if no product ID is present.
     * If the product ID is present, it will extract the product ID from the information String.
     * It will then call computeProductRevenue() from the RevenueCalculator class.
     *
     * @param info Information related to the command keyword.
     */
    protected void computeRevenue(String info) {
        if (info.isEmpty()) {
            int i = 0; // Placeholder to silence the 'empty if body' warning.

            //RevenueCalculator.computeTotalRevenue();
        } else {
            String[] components = splitInfo(info);

            String productID = components[1];

            //RevenueCalculator.computeProductRevenue();
        }
    }

    /**
     * Extracts the product name or ID from the information String.
     * If the product name is present, it will call searchByName() from the SearchManager class.
     * If the product ID is present, it will call searchById() from the SearchManager class.
     *
     * @param info Information related to the command keyword.
     */
    protected void searchForProduct(String info) {
        String[] components = splitInfo(info);

        if (components[0].equals("/name")) {
            String productName = components[1];

            //SearchManager.searchByName(productName);
        } else if (components[0].equals("/id")) {
            String productID = components[1];

            //SearchManager.searchById(productID);
        }
    }

    /**
     * Splits the information String into individual components.
     *
     * @param info Information related to the command keyword.
     * @return An array of Strings containing the individual components.
     *         (such as name, ID, quantity and price etc.)
     */
    protected String[] splitInfo(String info) {
        return info.split("\\s+");
    }

    /**
     * Parses a String containing a number to the type int.
     *
     * @param number String containing the number.
     * @return The number with type int.
     */
    protected int parseInt(String number) {
        return Integer.parseInt(number);
    }

    /**
     * Parses a String containing a number to the type double.
     *
     * @param number String containing the number.
     * @return The number with type double.
     */
    protected double parseDouble(String number) {
        return Double.parseDouble(number);
    }

}
