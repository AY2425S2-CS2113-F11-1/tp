//@@author LEESY02
package busynessmanager.constants;


/**
 * Constants class for usage.
 * Note:
 * 1. prefix XX_ refers to the class the constant is likely to be used in. (i.e. IM_ stands for InventoryManager)
 * 2. suffix _MESSAGE means the constant is to be printed out directly as a message to the user. (UI Class)
 * 3. suffix _FORMAT means the constant is a Formatted Message, similar to point 2,
 *    but different values need to be inserted into the String.
 */
public class Constants {
    // Misc.
    public static final String EMPTY_STRING = "";
    public static final String WHITESPACE = " ";
    public static final String NEWLINE = "\n";
    public static final String FILE_REGEX = ",";
    public static final int MINIMUM_VALUE = 0;
    public static final int MAXIMUM_VALUE = 9999;
    public static final long MAXIMUM_AMOUNT = 999999;
    public static final int INDEX_0 = 0;
    public static final int INDEX_1 = 1;
    public static final int INDEX_2 = 2;
    public static final int INDEX_3 = 3;
    public static final int INDEX_4 = 4;
    public static final int INDEX_5 = 5;
    public static final int INDEX_6 = 6;
    public static final String DATA_FOLDER = "data";
    public static final String BUSINESS_INFO_FILE = "data/%s.txt";
    public static final String PRODUCT_NOT_FOUND_FORMAT = "Error: The product with ID %s is not found.";
    public static final String TRY_AGAIN_MESSAGE = " Please try again.";
    public static final String HELP_LIST = """
            add         add <Name> <Quantity> <Price>                   (Add new product to list)
            delete      delete <ID number>                              (Delete existing product)
            update      update <ID number> <Flag> <Updated value>       (Update product, flags: /name /quantity /price)
            list        list                                            (Print list of products)
            sold        sold <ID number> <Quantity sold>                (Mark some products as sold)
            clear       clear <ID number>                               (Clear current sales)
            revenue     revenue OR revenue <ID number>                  (Check revenue)
            search      search /name <Name> OR search /id <ID number>   (Look for specific product)
            exit        exit                                            (Exit application)""";


    // BusynessManager
    public static final String BM_UPPERCASE_REGEX = "[A-Z]+";
    public static final String BM_DIGIT_REGEX = "\\d+";
    public static final String BM_BUSINESSTYPE_FNB = "FNB";
    public static final String BM_BUSINESSTYPE_RETAIL = "RETAIL";
    public static final String BM_APPROVAL = "yes";
    public static final String BM_EXIT_APPROVAL = "exit";
    public static final String BM_WAITING_INPUT = ">";
    public static final String BM_INVENTORY_TITLE = "---INVENTORY---";

    public static final String BM_WELCOME_MESSAGE = "Welcome to Busyness Manager!";
    public static final String BM_LOGIN_MESSAGE = "Please enter your business name: ";
    public static final String BM_FIRST_SETUP_CHECK_MESSAGE = "This business name does not exist in Busyness Manager. "
            + "Do you want to add your business?"
            + "\n(Enter \"yes\" to create a new account / Enter any other input to exit Busyness Manager) ";
    public static final String BM_ENTER_BUSINESS_ID_MESSAGE = "Enter Business ID: ";
    public static final String BM_ENTER_PASSWORD_MESSAGE = "Enter Password: ";
    public static final String BM_ENTER_PASSWORD_MESSAGE_2 = "Enter Business Password: ";
    public static final String BM_ENTER_BUSINESS_TYPE_MESSAGE = "Enter Business Type (FNB/RETAIL): ";
    public static final String BM_SUCCESSFUL_LOGIN_MESSAGE = "Login successful!";
    public static final String BM_SETUP_COMPLETE_MESSAGE = "Business setup complete!";
    public static final String BM_READY_MESSAGE = "Busyness Manager is ready. Type 'help' for commands.";
    public static final String BM_EXIT_MESSAGE = "Exiting Busyness Manager...";

    public static final String BM_NO_INPUT_MESSAGE = "Error: There is no input detected."
            + TRY_AGAIN_MESSAGE;
    public static final String BM_INVALID_ID_MESSAGE = "Error: The business ID provided must only contain numbers."
            + TRY_AGAIN_MESSAGE;
    public static final String BM_INVALID_BUSINESSTYPE_MESSAGE = "Error: The business type provided is invalid."
            + TRY_AGAIN_MESSAGE;

    public static final String BM_ID_RECOVERY = "The ID is: ";
    public static final String BM_PASSWORD_RECOVERY = "The password is: ";
    public static final String BM_RECOVERY_MESSAGE = "Please restart Busyness Manager and try logging in again.";
    public static final String BM_RECOVERY_ERROR_MESSAGE = "Error: The ID / password cannot be retrieved.";
    public static final String BM_FORGOT_PASSWORD_MESSAGE = "The ID and/or password is incorrect. Do you want to "
            + "retrieve these details?" + "\nEnter \"yes\" to retrieve ID and password / Enter any other input "
            + "to exit Busyness Manager) ";

    public static final String BM_ID_ASSERTION_FAIL_MESSAGE = "Business ID should not be null or empty.";
    public static final String BM_NAME_ASSERTION_FAIL_MESSAGE = "Business name should not be null or empty.";
    public static final String BM_PASSWORD_ASSERTION_FAIL_MESSAGE = "Business password should not be null or empty.";
    public static final String BM_PASSWORD_NULL_ASSERTION_FAIL_MESSAGE = "Business password should not be null.";
    public static final String BM_BUSINESSTYPE_ASSERTION_FAIL_MESSAGE = "Business type should not be null.";

    public static final String BM_NO_DATA_MESSAGE = "No previous business data found.";
    public static final String BM_SAVE_FAIL_MESSAGE = "Error saving business data: ";
    public static final String BM_LOAD_SUCCESS_MESSAGE = "Business data loaded successfully!";
    public static final String BM_LOAD_FAIL_MESSAGE = "Error loading business data: ";


    // CommandParser
    public static final int CP_COMMAND_SEPARATOR_INDEX = -1;
    public static final int CP_ASSERTION_FAIL_INDEX = -2;
    public static final String CP_NAME = "CommandParser";
    public static final String CP_SPLIT_REGEX = "\\s+";

    public static final String CP_HELP_COMMAND = "help";
    public static final String CP_ADD_COMMAND = "add";
    public static final String CP_DELETE_COMMAND = "delete";
    public static final String CP_UPDATE_COMMAND = "update";
    public static final String CP_LIST_COMMAND = "list";
    public static final String CP_SOLD_COMMAND = "sold";
    public static final String CP_CLEAR_COMMAND = "clear";
    public static final String CP_REVENUE_COMMAND = "revenue";
    public static final String CP_SEARCH_COMMAND = "search";

    public static final String CP_NAME_FLAG = "/name";
    public static final String CP_QUANTITY_FLAG = "/qty";
    public static final String CP_PRICE_FLAG = "/price";
    public static final String CP_ID_FLAG = "/id";

    public static final String CP_INVALID_FLAG_MESSAGE_ADD = "Error: Invalid format. "
            + "add <name> <quantity> <price>.";
    public static final String CP_INVALID_FLAG_MESSAGE_SOLD = "Error: Invalid format. "
            + "sold <ID number> <Quantity sold>.";
    public static final String CP_INVALID_FLAG_MESSAGE_SEARCH = "Error: Invalid format. "
            + "search /name <name> OR search /id <number>.";
    public static final String CP_INVALID_FLAG_MESSAGE_UPDATE = """
            Invalid flags.
            update <ID number> <flag> <updated value>,
            where <flag> is either /name, /qty, or /price.""";
    public static final String CP_INVALID_COMMAND_MESSAGE = "Error: The command provided does not exist."
            + TRY_AGAIN_MESSAGE;
    public static final String CP_INVALID_NUMERAL_MESSAGE = "Error: The quantity and/or price provided "
            + "is not a proper number." + TRY_AGAIN_MESSAGE;
    public static final String CP_INVALID_NUMERAL_MESSAGE_2 = "Error: The quantity provided must be a whole number."
            + TRY_AGAIN_MESSAGE;
    public static final String CP_INVALID_PRICE_MESSAGE = "Error: The price provided is invalid."
            + TRY_AGAIN_MESSAGE + " (Input is more than 2 decimal places)";
    public static final String CP_INVALID_ID_MESSAGE = "Error: The product ID provided is invalid."
            + TRY_AGAIN_MESSAGE;
    public static final String CP_ID_ABSENT_MESSAGE = "Error: The product ID provided is not present in the inventory."
            + TRY_AGAIN_MESSAGE;
    public static final String CP_ID_MISSING_MESSAGE = "Error: There is no product ID provided."
            + TRY_AGAIN_MESSAGE;
    public static final String CP_NAME_MISSING_MESSAGE = "Error: There is no name provided."
            + TRY_AGAIN_MESSAGE;

    public static final String CP_LOG_MESSAGE = "String splitting failure.";


    // InventoryManager
    public static final String IM_LIST = "Product list:";
    public static final String IM_ID_FORMAT = "Product ID: %s";
    public static final String IM_ADD_FORMAT = "Product added: %s";
    public static final String IM_REMOVE_FORMAT = "Product removed: %s";
    public static final String IM_UPDATE_FORMAT = "Product updated: %s";
    public static final String IM_NAME_EXISTS_FORMAT = "Error: A product with the name '%s' already exists.";
    public static final String IM_QTY_SOLD_ZERO_FORMAT = "Error: The quantity sold for product with ID %s "
            + "is currently 0.";

    public static final String IM_EMPTY_MESSAGE = "Error: There are no products in the inventory.";
    public static final String IM_ZERO_PRICE_MESSAGE = "Error: The price provided cannot be zero."
            + TRY_AGAIN_MESSAGE;
    public static final String IM_NEGATIVE_QTY_PRICE_MESSAGE = "Error: The quantity and/or price provided "
            + "cannot be a negative number." + TRY_AGAIN_MESSAGE;
    public static final String IM_MAX_QTY_PRICE_MESSAGE = "Error: The quantity and/or price provided exceeds the "
            + "maximum set by Busyness Manager." + TRY_AGAIN_MESSAGE;
    public static final String IM_QTY_EXCEED_MESSAGE = "Error: The quantity sold provided exceeds the quantity "
            + "of product with ID %s.";


    // SalesManager
    public static final String SM_CLEAR_FORMAT = "Sales cleared: " + IM_ID_FORMAT;
    public static final String SM_RECORD_FORMAT = "Sales recorded: " + IM_ID_FORMAT + ", Quantity Sold: %d";

    public static final String SM_MIN_QTY_SOLD_MESSAGE = "Error: The quantity sold provided must be " +
            "greater than 0.";


    // RevenueCalculator
    public static final String RC_TOTAL_REVENUE_FORMAT = "Total Revenue: %.2f";
    public static final String RC_INDIVIDUAL_REVENUE_FORMAT = "Revenue of %s: %.2f";


    // SearchManager
    public static final String SRM_PRODUCT_NOT_FOUND_FORMAT = "Error: Product with name %s not found.";


    // Product
    public static final String ID_FORMAT = "ID_%04d";
    public static final String PRODUCT_FORMAT = "%s: %s | Qty: %d | Sold: %d | Price: $%.2f";
}
