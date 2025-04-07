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
    public static final int INDEX_7 = 7;
    public static final String PRODUCT_NOT_FOUND_FORMAT = "Product with ID %s not found.";
    public static final String TRY_AGAIN_MESSAGE = " Please try again.";
    public static final String HELP_LIST = """
            add         add /name <name> /qty <number> /price <number>
            delete      delete /id <number>
            update      update /id <number> /name <name> /qty <number> /price <number>
            print       print
            sold        sold /id <number> /qty <number>
            clear       clear /id <number>
            revenue     revenue OR revenue /id <number>
            search      search /name <name> OR search /id <number>""";


    // BusynessManager
    public static final String BM_UPPERCASE_REGEX = "[A-Z]+";
    public static final String BM_BUSINESSTYPE_FNB = "FNB";
    public static final String BM_BUSINESSTYPE_RETAIL = "RETAIL";
    public static final String BM_FIRST_SETUP_APPROVAL = "yes";
    public static final String BM_NAME_TITLE = "Business Name: ";
    public static final String BM_TYPE_TITLE = "Business Type: ";
    public static final String BM_ID_TITLE = "Business ID: ";
    public static final String BM_INVENTORY_TITLE = "---INVENTORY---";

    public static final String BM_WELCOME_MESSAGE = "Welcome to Busyness Manager!";
    public static final String BM_NO_INPUT_ERROR_MESSAGE = "Error: No input detected.";
    public static final String BM_INVALID_ID_ERROR_MESSAGE = "Business ID should only be digits." + TRY_AGAIN_MESSAGE;
    public static final String BM_INVALID_BUSINESSTYPE_ERROR_MESSAGE = "Invalid business type." + TRY_AGAIN_MESSAGE;
    public static final String BM_FIRST_SETUP_CHECK_MESSAGE = "No existing ID. Do you want to add your business? ";
    public static final String BM_ENTER_BUSINESS_ID_MESSAGE = "Enter Business ID: ";
    public static final String BM_ENTER_PASSWORD_MESSAGE = "Enter Password: ";
    public static final String BM_ENTER_PASSWORD_MESSAGE_2 = "Enter Business Password: ";
    public static final String BM_SUCCESSFUL_LOGIN_MESSAGE = "Login successful!";
    public static final String BM_INVALID_CREDENTIALS_MESSAGE = "Invalid credentials. Exiting.";
    public static final String BM_ENTER_NAME_MESSAGE = "Enter Business Name: ";
    public static final String BM_ENTER_BUSINESS_TYPE_MESSAGE = "Enter Business Type (FNB/RETAIL): ";
    public static final String BM_SETUP_COMPLETE_MESSAGE = "Business setup complete!";
    public static final String BM_READY_MESSAGE = "Busyness Manager is ready. Type 'help' for commands.";
    public static final String BM_WAITING_INPUT_MESSAGE = ">";
    public static final String BM_EXIT_KEYWORD = "exit";
    public static final String BM_EXIT_MESSAGE = "Exiting Busyness Manager...";

    public static final String BM_ID_ASSERTION_FAIL_MESSAGE = "Business ID should not be null or empty.";
    public static final String BM_NAME_ASSERTION_FAIL_MESSAGE = "Business name should not be null or empty.";
    public static final String BM_PASSWORD_ASSERTION_FAIL_MESSAGE = "Business password should not be null or empty.";
    public static final String BM_BUSINESSTYPE_ASSERTION_FAIL_MESSAGE = "Business type should not be null.";
    public static final String BM_PASSWORD_NULL_ASSERTION_FAIL_MESSAGE = "Business password should not be null.";

    public static final String BM_NO_CREDENTIALS_MESSAGE = "No business credentials available.";
    public static final String BM_NO_DATA_MESSAGE = "No previous business data found.";
    public static final String BM_SAVE_FAIL_MESSAGE = "Error saving business data: ";
    public static final String BM_LOAD_SUCCESS_MESSAGE = "Business data loaded successfully!";
    public static final String BM_LOAD_FAIL_MESSAGE = "Error loading business data: ";


    // CommandParser
    public static final int CP_COMMAND_SEPARATOR_INDEX = -1;
    public static final int CP_ASSERTION_FAIL_INDEX = -2;
    public static final String CP_NAME = "CommandParser";
    public static final String CP_SPLIT_REGEX = "\\s+";

    public static final String CP_CREDENTIALS_TITLE = "Business Credentials:\n";
    public static final String CP_INVENTORY_TITLE = "Current Inventory:";

    public static final String CP_HELP_COMMAND = "help";
    public static final String CP_ADD_COMMAND = "add";
    public static final String CP_DELETE_COMMAND = "delete";
    public static final String CP_UPDATE_COMMAND = "update";
    public static final String CP_PRINT_COMMAND = "print";
    public static final String CP_SOLD_COMMAND = "sold";
    public static final String CP_CLEAR_COMMAND = "clear";
    public static final String CP_REVENUE_COMMAND = "revenue";
    public static final String CP_SEARCH_COMMAND = "search";
    public static final String CP_READ_BUSINESS_INFO_COMMAND = "readbusinessinfo";

    public static final String CP_NAME_FLAG = "/name";
    public static final String CP_QUANTITY_FLAG = "/qty";
    public static final String CP_PRICE_FLAG = "/price";
    public static final String CP_ID_FLAG = "/id";

    public static final String CP_INVALID_COMMAND_MESSAGE = "Command does not exist." + TRY_AGAIN_MESSAGE;
    public static final String CP_INVALID_FLAG_MESSAGE_ADD = "Invalid format. /name /qty /price.";
    public static final String CP_INVALID_FLAG_MESSAGE_SOLD = "Invalid format. /id /qty.";
    public static final String CP_INVALID_FLAG_MESSAGE_REVENUE = "Invalid format. /id or keep empty for total.";
    public static final String CP_INVALID_FLAG_MESSAGE_SEARCH = "Invalid format. /name OR /id.";
    public static final String CP_INVALID_FLAG_MESSAGE_UPDATE = "Invalid format. /id /name /qty /price.";
    public static final String CP_INVALID_ID_FORMAT_MESSAGE = "Invalid format. /id.";
    public static final String CP_INVALID_NUMERAL_MESSAGE = "Quantity or price is not a proper number." +
            TRY_AGAIN_MESSAGE;
    public static final String CP_INVALID_NUMERAL_MESSAGE_2 = "Quantity is not a proper number." +
            TRY_AGAIN_MESSAGE;
    public static final String CP_INVALID_ID_MESSAGE = "ID is invalid." + TRY_AGAIN_MESSAGE;
    public static final String CP_ID_MISSING_MESSAGE = "ID is missing." + TRY_AGAIN_MESSAGE;
    public static final String CP_NAME_MISSING_MESSAGE = "Name is missing." + TRY_AGAIN_MESSAGE;
    public static final String CP_LOG_MESSAGE = "String splitting failure.";


    // Product
    public static final String ID_FORMAT = "ID_%04d";
    public static final String PRODUCT_FORMAT = "%s: %s | Qty: %d | Sold: %d | Price: $%.2f";


    // InventoryManager
    public static final String PRODUCT = "Product";


    public static final String IM_ADD_FORMAT = PRODUCT + WHITESPACE + "added: %s";
    public static final String IM_ID_FORMAT = PRODUCT + WHITESPACE + "ID: %s";
    public static final String IM_REMOVE_FORMAT = PRODUCT + WHITESPACE + "removed: %s";
    public static final String IM_UPDATED_FORMAT = PRODUCT + WHITESPACE + "updated: %s";
    public static final String IM_NAME_EXISTS_FORMAT = "Error: A product with the name '%s' already exists.";

    public static final String IM_LIST = PRODUCT + WHITESPACE + "list:";
    public static final String IM_EMPTY_MESSAGE = "No products in inventory";
    public static final String IM_NEGATIVE_QUANTITY_PRICE_MESSAGE = "Quantity and/or price is a negative number.";
    public static final String IM_MAXIMUM_QUANTITY_PRICE_MESSAGE = "Quantity and/or price exceeds the maximum number.";
    public static final String IM_QTY_EXCEED_ERROR_MESSAGE = "Quantity sold exceeds the quantity of product."
            + TRY_AGAIN_MESSAGE;


    // SalesManager
    public static final String SM_SALES = "Sales";

    public static final String SM_CLEARED_FORMAT = SM_SALES + WHITESPACE + "cleared: " + IM_ID_FORMAT;
    public static final String SM_RECORDED_FORMAT = SM_SALES + WHITESPACE +
            "recorded: " + IM_ID_FORMAT + ", Quantity Sold: %d";

    public static final String SM_MINIMUM_QTY_SOLD_MESSAGE = "Quantity sold must be greater than 0";


    // RevenueCalculator
    public static final String RC_TOTAL_REVENUE_FORMAT = "Total Revenue: %.2f";
    public static final String RC_INDIVIDUAL_REVENUE_FORMAT = "Revenue of %s: %.2f";


    // SearchManager
    public static final String SRM_PRODUCT_NOT_FOUND_FORMAT = "Product with name %s not found.";
    public static final String SRM_ID_QUERY_FORMAT = "Product ID of %s: %s";
    public static final String SRM_NAME_QUERY_FORMAT = "Product name of %s: %s";
}
