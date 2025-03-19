package busynessmanager.parser;

import busynessmanager.UI_Constants.UI;
import busynessmanager.managers.InventoryManager;
import busynessmanager.managers.SalesManager;
import busynessmanager.managers.SearchManager;
import busynessmanager.revenue.RevenueCalculator;

import static busynessmanager.UI_Constants.Constants.WHITESPACE;
import static busynessmanager.UI_Constants.Constants.EMPTY_STRING;
import static busynessmanager.UI_Constants.Constants.INDEX_0;
import static busynessmanager.UI_Constants.Constants.INDEX_1;
import static busynessmanager.UI_Constants.Constants.INDEX_2;
import static busynessmanager.UI_Constants.Constants.INDEX_3;
import static busynessmanager.UI_Constants.Constants.INDEX_4;
import static busynessmanager.UI_Constants.Constants.INDEX_5;
import static busynessmanager.UI_Constants.Constants.INDEX_6;
import static busynessmanager.UI_Constants.Constants.INDEX_7;
import static busynessmanager.UI_Constants.Constants.CP_COMMAND_SEPARATOR_INDEX;
import static busynessmanager.UI_Constants.Constants.CP_ADD_COMMAND;
import static busynessmanager.UI_Constants.Constants.CP_DELETE_COMMAND;
import static busynessmanager.UI_Constants.Constants.CP_UPDATE_COMMAND;
import static busynessmanager.UI_Constants.Constants.CP_PRINT_COMMAND;
import static busynessmanager.UI_Constants.Constants.CP_SOLD_COMMAND;
import static busynessmanager.UI_Constants.Constants.CP_CLEAR_COMMAND;
import static busynessmanager.UI_Constants.Constants.CP_REVENUE_COMMAND;
import static busynessmanager.UI_Constants.Constants.CP_SEARCH_COMMAND;
import static busynessmanager.UI_Constants.Constants.CP_ID_REGEX;
import static busynessmanager.UI_Constants.Constants.CP_SPLIT_REGEX;
import static busynessmanager.UI_Constants.Constants.CP_ID_FLAG;
import static busynessmanager.UI_Constants.Constants.CP_NAME_FLAG;
import static busynessmanager.UI_Constants.Constants.CP_PRICE_FLAG;
import static busynessmanager.UI_Constants.Constants.CP_QUANTITY_FLAG;
import static busynessmanager.UI_Constants.Constants.CP_INVALID_COMMAND_MESSAGE;
import static busynessmanager.UI_Constants.Constants.CP_INVALID_FLAG_MESSAGE;
import static busynessmanager.UI_Constants.Constants.CP_INVALID_FLAG_MESSAGE_2;
import static busynessmanager.UI_Constants.Constants.CP_INVALID_FLAG_MESSAGE_3;
import static busynessmanager.UI_Constants.Constants.CP_INVALID_FLAG_MESSAGE_4;
import static busynessmanager.UI_Constants.Constants.CP_INVALID_FLAG_MESSAGE_5;
import static busynessmanager.UI_Constants.Constants.CP_INVALID_FLAG_MESSAGE_6;
import static busynessmanager.UI_Constants.Constants.CP_INVALID_ID_MESSAGE;
import static busynessmanager.UI_Constants.Constants.CP_INVALID_ID_FORMAT_MESSAGE;
import static busynessmanager.UI_Constants.Constants.CP_INVALID_NUMERAL_MESSAGE;
import static busynessmanager.UI_Constants.Constants.CP_INVALID_NUMERAL_MESSAGE_2;

import busynessmanager.exceptions.InvalidStringException;
import busynessmanager.exceptions.InvalidCommandException;
import busynessmanager.exceptions.NumberParsingFailedException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for parsing the user input, and executing the appropriate methods.
 */
public class CommandParser {


    private static final Logger logger = Logger.getLogger("CommandParser");
    private final InventoryManager inventoryManager;
    private final SalesManager salesManager;
    private final RevenueCalculator revenueCalculator;
    private final SearchManager searchManager;


    /**
     * Constructs the CommandParser class when the other systems have not been created.
     * This constructor also instantiates the other systems.
     */
    public CommandParser() {
        this.inventoryManager = new InventoryManager();
        this.salesManager = new SalesManager(inventoryManager);
        this.revenueCalculator = new RevenueCalculator(salesManager);
        this.searchManager = new SearchManager(inventoryManager);
    }


    /**
     * Constructs the CommandParser class given existing systems for the business.
     *
     * @param inventoryManager existing InventoryManager for the business.
     * @param salesManager existing SalesManager for the business.
     * @param revenueCalculator existing RevenueCalculator for the business.
     * @param searchManager existing SearchManager for the business.
     */

    public CommandParser(InventoryManager inventoryManager, SalesManager salesManager,
                         RevenueCalculator revenueCalculator, SearchManager searchManager) {
        this.inventoryManager = inventoryManager;
        this.salesManager = salesManager;
        this.revenueCalculator = revenueCalculator;
        this.searchManager = searchManager;
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
                UI.printMessage(e.getMessage());
            }
        } catch (InvalidStringException e) {
            logger.log(Level.SEVERE, "Exception thrown.", e);
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
            return input.indexOf(WHITESPACE);
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
        if (commandSeparatorIndex == CP_COMMAND_SEPARATOR_INDEX) {
            return input;
        } else if (commandSeparatorIndex >= INDEX_0 && commandSeparatorIndex < input.length()) {
            return input.substring(INDEX_0, commandSeparatorIndex);
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
        if (commandSeparatorIndex == CP_COMMAND_SEPARATOR_INDEX) {
            return EMPTY_STRING;
        } else if (commandSeparatorIndex >= INDEX_0 && commandSeparatorIndex < input.length()) {
            return input.substring(commandSeparatorIndex + INDEX_1);
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
        case CP_ADD_COMMAND:
            addProduct(info);
            break;
        case CP_DELETE_COMMAND:
            deleteProduct(info);
            break;
        case CP_UPDATE_COMMAND:
            updateProduct(info);
            break;
        case CP_PRINT_COMMAND:
            printProducts();
            break;
        case CP_SOLD_COMMAND:
            recordSale(info);
            break;
        case CP_CLEAR_COMMAND:
            clearSales(info);
            break;
        case CP_REVENUE_COMMAND:
            computeRevenue(info);
            break;
        case CP_SEARCH_COMMAND:
            searchForProduct(info);
            break;
        default:
            throw new InvalidCommandException(CP_INVALID_COMMAND_MESSAGE);
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

        if (!components[INDEX_0].equals(CP_NAME_FLAG) ||
            !components[INDEX_2].equals(CP_QUANTITY_FLAG) ||
            !components[INDEX_4].equals(CP_PRICE_FLAG)) {
            throw new InvalidCommandException(CP_INVALID_FLAG_MESSAGE);
        }

        String productName;
        int productQuantity;
        double productPrice;

        try {
            productName = components[INDEX_1];

            try {
                productQuantity = parseInt(components[INDEX_3]);
                productPrice = parseDouble(components[INDEX_5]);

                assert productQuantity >= 0 : "productQuantity is a negative number.";
                assert productPrice > 0 : "productPrice is not a positive number.";
            } catch (NumberParsingFailedException e) {
                throw new InvalidCommandException(CP_INVALID_NUMERAL_MESSAGE);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException(CP_INVALID_FLAG_MESSAGE);
        }

        inventoryManager.addProduct(productName, productQuantity, productPrice);
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

        if (!components[INDEX_0].equals(CP_ID_FLAG)) {
            throw new InvalidCommandException(CP_INVALID_ID_FORMAT_MESSAGE);
        }

        String productID;

        try {
            productID = components[INDEX_1];

            if (!productID.matches(CP_ID_REGEX)) {
                throw new InvalidCommandException(CP_INVALID_ID_MESSAGE);
            } else {
                inventoryManager.deleteProduct(productID);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException(CP_INVALID_ID_FORMAT_MESSAGE);
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

        if (!components[INDEX_0].equals(CP_ID_FLAG) ||
            !components[INDEX_2].equals(CP_NAME_FLAG) ||
            !components[INDEX_4].equals(CP_QUANTITY_FLAG) ||
            !components[INDEX_6].equals(CP_PRICE_FLAG)) {
            throw new InvalidCommandException(CP_INVALID_FLAG_MESSAGE_6);
        }

        String productID;
        String productNewName;
        int productNewQuantity;
        double productNewPrice;

        try {
            productID = components[INDEX_1];
            productNewName = components[INDEX_3];

            try {
                productNewQuantity = parseInt(components[INDEX_5]);
                productNewPrice = parseDouble(components[INDEX_7]);

                assert productNewQuantity >= 0 : "productNewQuantity is a negative number.";
                assert productNewPrice > 0 : "productNewPrice is not a positive number.";
            } catch (NumberParsingFailedException e) {
                throw new InvalidCommandException(CP_INVALID_NUMERAL_MESSAGE);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException(CP_INVALID_FLAG_MESSAGE_6);
        }

        if (!productID.matches(CP_ID_REGEX)) {
            throw new InvalidCommandException(CP_INVALID_ID_MESSAGE);
        } else {
            inventoryManager.updateProduct(productID, productNewName, productNewQuantity, productNewPrice);
        }
    }

    /**
     * Calls printProducts() from the InventoryManager class.
     */
    protected void printProducts() {
        inventoryManager.printProducts();
    }

    /**
     * Splits the information String into the product ID, and amount of product that was sold.
     * It then calls recordSale() from the SalesManager class.
     * @param info Information related to the command keyword.
     * @throws InvalidCommandException If the user input is of the wrong format.
     */
    protected void recordSale(String info) throws InvalidCommandException {
        String[] components = splitInfo(info);

        if (!components[INDEX_0].equals(CP_ID_FLAG) || !components[INDEX_2].equals(CP_QUANTITY_FLAG)) {
            throw new InvalidCommandException(CP_INVALID_FLAG_MESSAGE_2);
        }

        String productID;
        int quantitySold;

        try {
            productID = components[INDEX_1];

            try {
                quantitySold = parseInt(components[INDEX_3]);

                assert quantitySold >= 0 : "quantitySold is a negative number.";
            } catch (NumberParsingFailedException e) {
                throw new InvalidCommandException(CP_INVALID_NUMERAL_MESSAGE_2);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException(CP_INVALID_FLAG_MESSAGE_2);
        }

        if (!productID.matches(CP_ID_REGEX)) {
            throw new InvalidCommandException(CP_INVALID_ID_MESSAGE);
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

        if (!components[INDEX_0].equals(CP_ID_FLAG)) {
            throw new InvalidCommandException(CP_INVALID_FLAG_MESSAGE_3);
        }

        String productID;

        try {
            productID = components[INDEX_1];

            if (!productID.matches(CP_ID_REGEX)) {
                throw new InvalidCommandException(CP_INVALID_ID_MESSAGE);
            } else {
                salesManager.clearSales(productID);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException(CP_INVALID_FLAG_MESSAGE_3);
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
            revenueCalculator.computeTotalRevenue();
        } else {
            String[] components = splitInfo(info);

            if (!components[INDEX_0].equals(CP_ID_FLAG)) {
                throw new InvalidCommandException(CP_INVALID_FLAG_MESSAGE_4);
            }

            String productID;

            try {
                productID = components[INDEX_1];

                if (!productID.matches(CP_ID_REGEX)) {
                    throw new InvalidCommandException(CP_INVALID_ID_MESSAGE);
                } else {
                    revenueCalculator.computeProductRevenue(productID);
                }
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidCommandException(CP_INVALID_FLAG_MESSAGE_4);
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

        if (components[INDEX_0].equals(CP_NAME_FLAG)) {
            String productName;

            try {
                productName = components[INDEX_1];
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidCommandException(CP_INVALID_FLAG_MESSAGE_5);
            }

            searchManager.searchByName(productName);
        } else if (components[INDEX_0].equals(CP_ID_FLAG)) {
            String productID;

            try {
                productID = components[INDEX_1];

                if (!productID.matches(CP_ID_REGEX)) {
                    throw new InvalidCommandException(CP_INVALID_ID_MESSAGE);
                } else {
                    searchManager.searchById(productID);
                }
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidCommandException(CP_INVALID_FLAG_MESSAGE_5);
            }
        } else {
            throw new InvalidCommandException(CP_INVALID_FLAG_MESSAGE_5);
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
        return info.split(CP_SPLIT_REGEX);
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
