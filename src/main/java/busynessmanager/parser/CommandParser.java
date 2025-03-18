package busynessmanager.parser;

import busynessmanager.SalesManager;
import busynessmanager.InventoryManager;

import busynessmanager.exceptions.InvalidStringException;
import busynessmanager.exceptions.InvalidCommandException;

/**
 * Class for parsing the user input, and executing the appropriate methods.
 */
public class CommandParser {


    private InventoryManager inventoryManager;
    private SalesManager salesManager;
    /*
    private RevenueCalculator revenueCalculator;
    private SearchManager searchManager;
    */

    /**
     * Constructs the CommandParser class when the other systems have not been created.
     * This constructor also instantiates the other systems.
     */
    public CommandParser() {
        this.inventoryManager = new InventoryManager();
        this.salesManager = new SalesManager(inventoryManager);
        /*
        this.revenueCalculator = new RevenueCalculator();
        this.searchManager = new SearchManager();
        */

    }


    /**
     * Constructs the CommandParser class given existing systems for the business.
     *
     * @param inventoryManager existing InventoryManager for the business.
     * @param salesManager existing SalesManager for the business.
     * //@param revenueCalculator existing RevenueCalculator for the business.
     * //@param searchManager existing SearchManager for the business.
     */

    public CommandParser(InventoryManager inventoryManager, SalesManager salesManager/*,
                         RevenueCalculator revenueCalculator, SearchManager searchManager*/) {
        this.inventoryManager = inventoryManager;
        this.salesManager = salesManager;
        /*
        this.revenueCalculator = revenueCalculator;
        this.searchManager = searchManager;
        */
    }

    /**
     * Parses the user input for execution.
     *
     * @param input Input from the user.
     */
    public void parseCommand(String input) {
        try {
            int commandSeparatorIndex = getCommandSeparatorIndex(input.trim());

            String command = extractCommand(commandSeparatorIndex, input.trim());
            String info = extractInfo(commandSeparatorIndex, input.trim());

            executeCommand(command, info);
        } catch (InvalidStringException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets the index where the command keyword ends.
     *
     * @param input Input from the user.
     * @return Index where the command keyword ends.
     * @throws InvalidStringException If the user input is null.
     */
    protected int getCommandSeparatorIndex(String input) throws InvalidStringException {
        if (input == null) {
            throw new InvalidStringException();
        } else {
            return input.indexOf(' ');
        }
    }

    /**
     * Returns a String of the command keyword for interpretation.
     *
     * @param commandSeparatorIndex Index where the command keyword ends.
     * @param input Input from the user.
     * @return String containing the command keyword.
     * @throws InvalidStringException If commandSeparatorIndex < -1 or exceeds the length of input.
     */
    protected String extractCommand(int commandSeparatorIndex, String input) throws InvalidStringException {
        if (commandSeparatorIndex == -1) {
            return input;
        } else if (commandSeparatorIndex >= 0 && commandSeparatorIndex < input.length()) {
            return input.substring(0, commandSeparatorIndex);
        } else {
            throw new InvalidStringException();
        }
    }

    /**
     * Returns a String of the information associated with the command keyword.
     *
     * @param commandSeparatorIndex Index where the command keyword ends.
     * @param input Input from the user.
     * @return String containing the information related to the command keyword.
     * @throws InvalidStringException If commandSeparatorIndex < -1 or exceeds the length of input.
     */
    protected String extractInfo(int commandSeparatorIndex, String input) throws InvalidStringException {
        if (commandSeparatorIndex == -1) {
            return "";
        } else if (commandSeparatorIndex >= 0 && commandSeparatorIndex < input.length()) {
            return input.substring(commandSeparatorIndex + 1);
        } else {
            throw new InvalidStringException();
        }
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
     * @param info Information related to the command keyword.\
     */
    protected void recordSale(String info) {
        String[] components = splitInfo(info);

        String productID = components[1];
        int quantitySold = parseInt(components[3]);

        salesManager.recordSale(productID, quantitySold);
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

        salesManager.clearSales(productID);
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
