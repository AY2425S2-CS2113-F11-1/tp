package busynessmanager.parser;

import busynessmanager.SalesManager;
import busynessmanager.InventoryManager;

import busynessmanager.exceptions.InvalidStringException;
import busynessmanager.exceptions.InvalidCommandException;
import busynessmanager.exceptions.NumberParsingFailedException;

/**
 * Class for parsing the user input, and executing the appropriate methods.
 */
public class CommandParser {


    private final InventoryManager inventoryManager;
    private final SalesManager salesManager;
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
        String command;
        String info;

        try {
            int commandSeparatorIndex = getCommandSeparatorIndex(input.trim());

            command = extractCommand(commandSeparatorIndex, input.trim());
            info = extractInfo(commandSeparatorIndex, input.trim());

            try {
                executeCommand(command, info);
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
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
    protected void executeCommand(String command, String info) throws InvalidCommandException {
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
            throw new InvalidCommandException("Command does not exist. Please try again.");
        }
    }

    /**
     * Splits the information String into the product name, quantity and price.
     * It then calls addProduct() from the InventoryManager class.
     *
     * @param info Information related to the command keyword.
     * @throws InvalidCommandException If the user input is of the wrong format.
     */
    protected void addProduct(String info) throws InvalidCommandException {
        String[] components = splitInfo(info);

        if (!components[0].equals("/name") || !components[2].equals("/qty") || !components[4].equals("/price")) {
            throw new InvalidCommandException("Invalid format. /name /qty /price.");
        }

        String productName;
        int productQuantity;
        double productPrice;

        try {
            productName = components[1];

            try {
                productQuantity = parseInt(components[3]);
                productPrice = parseDouble(components[5]);
            } catch (NumberParsingFailedException e) {
                throw new InvalidCommandException("Quantity or price is not a number. Please try again.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid format. /name /qty /price.");
        }

        //InventoryManager.addProduct(productName, productQuantity, productPrice);
    }

    /**
     * Extracts the product ID from the information String.
     * It then calls deleteProduct() from the InventoryManager class.
     *
     * @param info Information related to the command keyword.
     * @throws InvalidCommandException If the user input is of the wrong format.
     */
    protected void deleteProduct(String info) throws InvalidCommandException {
        String[] components = splitInfo(info);

        if (!components[0].equals("/id")) {
            throw new InvalidCommandException("Invalid format. /id.");
        }

        String productID;

        try {
            productID = components[1];

            if (!productID.matches("ID_\\d{4}")) {
                throw new InvalidCommandException("ID is invalid. Please try again.");
            } else {
                //InventoryManager.deleteProduct(productID);
            }
        } catch (IndexOutOfBoundsException e) {
                throw new InvalidCommandException("Invalid format. /id.");
        }
    }

    /**
     * Splits the information String into the product ID, updated name, updated quantity and updated price.
     * It then calls updateProduct() from the InventoryManager class.
     *
     * @param info Information related to the command keyword.
     * @throws InvalidCommandException If the user input is of the wrong format.
     */
    protected void updateProduct(String info) throws InvalidCommandException {
        String[] components = splitInfo(info);

        if (!components[0].equals("/id") || !components[2].equals("/name") || !components[4].equals("/qty") ||
                !components[6].equals("/price")) {
            throw new InvalidCommandException("Invalid format. /id /name /qty /price.");
        }

        String productID;
        String productNewName;
        int productNewQuantity;
        double productNewPrice;

        try {
            productID = components[1];
            productNewName = components[3];

            try {
                productNewQuantity = parseInt(components[5]);
                productNewPrice = parseDouble(components[7]);
            } catch (NumberParsingFailedException e) {
                throw new InvalidCommandException("Quantity or price is not a number. Please try again.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid format. /id /name /qty /price.");
        }

        if (!productID.matches("ID_\\d{4}")) {
            throw new InvalidCommandException("ID is invalid. Please try again.");
        } else {
            //InventoryManager.updateProduct(productID, productNewName, productNewQuantity, productNewPrice);
        }
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
     * @param info Information related to the command keyword.
     * @throws InvalidCommandException If the user input is of the wrong format.
     */
    protected void recordSale(String info) throws InvalidCommandException {
        String[] components = splitInfo(info);

        if (!components[0].equals("/id") || !components[2].equals("/qty")) {
            throw new InvalidCommandException("Invalid format. /id /qty.");
        }

        String productID;
        int quantitySold;

        try {
            productID = components[1];

            try {
                quantitySold = parseInt(components[3]);
            } catch (NumberParsingFailedException e) {
                throw new InvalidCommandException("Quantity is not a number. Please try again.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid format. /id /qty.");
        }

        if (!productID.matches("ID_\\d{4}")) {
            throw new InvalidCommandException("ID is invalid. Please try again.");
        } else {
            salesManager.recordSale(productID, quantitySold);
        }
    }

    /**
     * Extracts the product ID from the information String.
     * It then calls clearSales() from the SalesManager class.
     *
     * @param info Information related to the command keyword.
     * @throws InvalidCommandException If the user input is of the wrong format.
     */
    protected void clearSales(String info) throws InvalidCommandException {
        String[] components = splitInfo(info);

        if (!components[0].equals("/id")) {
            throw new InvalidCommandException("Invalid format. /id.");
        }

        String productID;

        try {
            productID = components[1];

            if (!productID.matches("ID_\\d{4}")) {
                throw new InvalidCommandException("ID is invalid. Please try again.");
            } else {
                salesManager.clearSales(productID);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid format. /id.");
        }
    }

    /**
     * Calls computeTotalRevenue() from the RevenueCalculator class if no product ID is present.
     * If the product ID is present, it will extract the product ID from the information String.
     * It will then call computeProductRevenue() from the RevenueCalculator class.
     *
     * @param info Information related to the command keyword.
     * @throws InvalidCommandException If the user input is of the wrong format.
     */
    protected void computeRevenue(String info) throws InvalidCommandException {
        if (info.isEmpty()) {
            //RevenueCalculator.computeTotalRevenue();
        } else {
            String[] components = splitInfo(info);

            if (!components[0].equals("/id")) {
                throw new InvalidCommandException("Invalid format. /id or keep empty for total.");
            }

            String productID;

            try {
                productID = components[1];

                if (!productID.matches("ID_\\d{4}")) {
                    throw new InvalidCommandException("ID is invalid. Please try again.");
                } else {
                    //RevenueCalculator.computeProductRevenue(productID);
                }
            } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid format. /id or keep empty for total.");
            }
        }
    }

    /**
     * Extracts the product name or ID from the information String.
     * If the product name is present, it will call searchByName() from the SearchManager class.
     * If the product ID is present, it will call searchById() from the SearchManager class.
     *
     * @param info Information related to the command keyword.
     * @throws InvalidCommandException If the user input is of the wrong format.
     */
    protected void searchForProduct(String info) throws InvalidCommandException {
        String[] components = splitInfo(info);

        if (components[0].equals("/name")) {
            String productName;

            try {
                productName = components[1];
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidCommandException("Invalid format. /name OR /id.");
            }

            //SearchManager.searchByName(productName);
        } else if (components[0].equals("/id")) {
            String productID;

            try {
                productID = components[1];

                if (!productID.matches("ID_\\d{4}")) {
                    throw new InvalidCommandException("ID is invalid. Please try again.");
                } else {
                    //SearchManager.searchById(productID);
                }
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidCommandException("Invalid format. /name OR /id.");
            }
        } else {
            throw new InvalidCommandException("Invalid format. /name OR /id.");
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
     * @throws NumberParsingFailedException If parsing into an int has failed.
     */
    protected int parseInt(String number) throws NumberParsingFailedException {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new NumberParsingFailedException();
        }
    }

    /**
     * Parses a String containing a number to the type double.
     *
     * @param number String containing the number.
     * @return The number with type double.
     * @throws NumberParsingFailedException If parsing into a double has failed.
     */
    protected double parseDouble(String number) throws NumberParsingFailedException {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e) {
            throw new NumberParsingFailedException();
        }
    }
}
