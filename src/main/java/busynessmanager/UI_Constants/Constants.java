package busynessmanager.UI_Constants;

/**
 * Constants class for usage
 * Note:
 * 1. prefix XX_ refers to the class the constant is likely to be used in (i.e. IM_ stands for InventoryManager)
 * 2. suffix _MESSAGE means the constant is to be printed out directly as a message to user (UI Class)
 * 3. suffix _FORMAT means the constant is a Formatted Message, similar to point 2,
 *    but different values need to be inserted into the String
 */
public class Constants {
    // Misc
    public static final String EMPTY_STRING = "";
    public static final String WHITESPACE = " ";
    public static final String NEWLINE = "\n";
    public static final int MINIMUM_VALUE = 0;
    public static final int INDEX_0 = 0;
    public static final int INDEX_1 = 1;
    public static final int INDEX_2 = 2;
    public static final int INDEX_3 = 3;
    public static final int INDEX_4 = 4;
    public static final int INDEX_5 = 5;
    public static final int INDEX_6 = 6;
    public static final int INDEX_7 = 7;
    public static final int INDEX_8 = 8;
    public static final int INDEX_9 = 9;
    public static final String INVALID_NAME = "INVALID_NAME";

    // BusynessManager
    public static final String BM_WELCOME_MESSAGE = "Welcome to Busyness Manager!";
    public static final String BM_NO_INPUT_ERROR_MESSAGE = "Error: No input detected. Exiting...";
    public static final String BM_ENTER_BUSINESS_ID_MESSAGE = "Enter Business ID: ";
    public static final String BM_ENTER_PASSWORD_MESSAGE = "Enter Password: ";
    public static final String BM_ENTER_PASSWORD_MESSAGE_2 = "Enter Business Password: ";
    public static final String BM_SUCCESSFUL_LOGIN_MESSAGE = "Login successful!";
    public static final String BM_INVALID_CREDENTIALS_MESSAGE = "Invalid credentials. Exiting.";
    public static final String BM_FIRST_SETUP_MESSAGE = "First-time setup required.";
    public static final String BM_ENTER_NAME_MESSAGE = "Enter Business Name: ";
    public static final String BM_ENTER_BUSINESS_TYPE_MESSAGE = "Enter Business Type (FNB/RETAIL): ";
    public static final String BM_SETUP_COMPLETE_MESSAGE = "Business setup complete!";
    public static final String BM_READY_MESSAGE = "Busyness Manager is ready. Type 'help' for commands.";
    public static final String BM_WAITING_INPUT_MESSAGE = ">";
    public static final String BM_EXIT_KEYWORD = "exit";
    public static final String BM_EXIT_MESSAGE = "Exiting Busyness Manager...";

    // CommandParser
    public static final int CP_COMMAND_SEPARATOR_INDEX = -1;
    public static final String CP_ID_REGEX = "ID_\\d{4}";
    public static final String CP_SPLIT_REGEX = "\\s+";

    public static final String CP_ADD_COMMAND = "add";
    public static final String CP_DELETE_COMMAND = "delete";
    public static final String CP_UPDATE_COMMAND = "update";
    public static final String CP_PRINT_COMMAND = "print";
    public static final String CP_SOLD_COMMAND = "sold";
    public static final String CP_CLEAR_COMMAND = "clear";
    public static final String CP_REVENUE_COMMAND = "revenue";
    public static final String CP_SEARCH_COMMAND = "search";

    public static final String CP_NAME_FLAG = "/name";
    public static final String CP_QUANTITY_FLAG = "/qty";
    public static final String CP_PRICE_FLAG = "/price";
    public static final String CP_ID_FLAG = "/id";

    public static final String CP_INVALID_COMMAND_MESSAGE = "Command does not exist. Please try again.";
    public static final String CP_INVALID_FLAG_MESSAGE = "Invalid format. /name /qty /price.";
    public static final String CP_INVALID_FLAG_MESSAGE_2 = "Invalid format. /id /qty.";
    public static final String CP_INVALID_FLAG_MESSAGE_3 = "Invalid format. /id.";
    public static final String CP_INVALID_FLAG_MESSAGE_4 = "Invalid format. /id or keep empty for total.";
    public static final String CP_INVALID_FLAG_MESSAGE_5 = "Invalid format. /name OR /id.";
    public static final String CP_INVALID_FLAG_MESSAGE_6 = "Invalid format. /id /name /qty /price.";
    public static final String CP_INVALID_NUMERAL_MESSAGE = "Quantity or price is not a number. Please try again.";
    public static final String CP_INVALID_NUMERAL_MESSAGE_2 = "Quantity is not a number. Please try again.";
    public static final String CP_INVALID_ID_FORMAT_MESSAGE = "Invalid format. /id.";
    public static final String CP_INVALID_ID_MESSAGE = "ID is invalid. Please try again.";

    // Product
    public static final String ID_FORMAT = "ID_%04d";
    public static final String PRODUCT_FORMAT = "%s: %s | Qty: %d | Sold: %d | Price: $%.2f";

    // InventoryManager
    public static final String PRODUCT = "Product";
    public static final String IM_ADD_FORMAT = PRODUCT + WHITESPACE + "added: %s";
    public static final String IM_ID_FORMAT = PRODUCT + WHITESPACE + "ID: %s";
    public static final String IM_REMOVE_FORMAT = PRODUCT + WHITESPACE + "removed: %s";
    public static final String IM_UPDATED_FORMAT = PRODUCT + WHITESPACE + "updated: %s";
    public static final String IM_LIST = PRODUCT + WHITESPACE + "list: ";
    public static final String IM_EMPTY_MESSAGE = "No products in inventory";
    public static final String IM_ID_EXISTS_FORMAT = "Product with ID %s already exists.";
    public static final String IM_PRODUCT_NOT_FOUND_FORMAT = "Product with ID %s not found.";

    // Sale
    public static final String SM_SALES = "Sales";
    public static final String SM_RECORDED_FORMAT = SM_SALES + WHITESPACE +
        "recorded: " + IM_ID_FORMAT + ", Quantity Sold: %d";
    public static final String SM_MINIMUM_QTY_SOLD_MESSAGE = "Quantity sold must be greater than 0";
    public static final String SM_CLEARED_FORMAT = SM_SALES + WHITESPACE + "cleared: " + IM_ID_FORMAT;

}
