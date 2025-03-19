package busynessmanager.UI_Constants;

/**
 * Constants class for usage
 * Note:
 * 1. prefix XX_ refers to the class the constant is likely to be used in (i.e. IM_ stands for InventoryManager)
 * 2. suffix _MESSAGE means the constant is to be printed out directly as a message to user (UI Class)
 * 3. suffix _FM means the constant is a Formatted Message, similar to point 2,
 *    but different values need to be inserted into the String
 */
public class Constants {
    // Misc
    static final String WHITESPACE = " ";

    // Product
    static final double MINIMUM_QUANTITY = 0;

    // InventoryManager
    static final String PRODUCT = "Product";
    static final String IM_ADD = PRODUCT + WHITESPACE + "added: ";
    static final String IM_ID = PRODUCT + WHITESPACE + "ID: ";
    static final String IM_REMOVE = PRODUCT + WHITESPACE + "removed: ";
    static final String IM_LIST = PRODUCT + WHITESPACE + "list: ";
    static final String IM_EMPTY_MESSAGE = "No products in inventory";

    // Sale
    static final String SM_SALES = "Sales";
    static final String SM_RECORDED_FM = SM_SALES + WHITESPACE + "recorded: " + IM_ID + " %s, Quantity Sold: %d";
    static final String SM_MINIMUM_QTY_SOLD_MESSAGE = "Quantity sold must be greater than 0";
    static final String SM_CLEARED_MESSAGE = SM_SALES + WHITESPACE + "cleared: " + IM_ID + " %s";

}
