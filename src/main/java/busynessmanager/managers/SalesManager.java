//@@author rozaliesmit
package busynessmanager.managers;

import busynessmanager.ui.UI;

import static busynessmanager.constants.Constants.MINIMUM_VALUE;
import static busynessmanager.constants.Constants.NEWLINE;
import static busynessmanager.constants.Constants.SM_MIN_QTY_SOLD_MESSAGE;
import static busynessmanager.constants.Constants.SM_CLEAR_FORMAT;
import static busynessmanager.constants.Constants.SM_RECORD_FORMAT;


/**
 * Updates sales transactions, decrements inventory and increases quantity sold
 */
public class SalesManager {
    private final InventoryManager inventory;


    /**
     * Constructs the SalesManager class using an existing InventoryManager.
     *
     * @param inventory Existing InventoryManager for the business.
     */
    public SalesManager(InventoryManager inventory) {
        this.inventory = inventory;
    }


    /**
     * Records a sale of a quantity of a product by its ID.
     *
     * @param id Product ID.
     * @param qtySold Quantity of product sold.
     */
    public void recordSale(String id, int qtySold) {
        if (checkIfInvalidQuantity(qtySold)) {
            return;
        }

        boolean isSuccess = inventory.updateProductQuantity(id, qtySold);

        if (isSuccess) {
            UI.printFormattedMessage(SM_RECORD_FORMAT + NEWLINE, id, qtySold);
            inventory.updateRevenue(id, qtySold);
        }
    }

    //@@author LEESY02
    /**
     * Checks if the input integer is invalid (Lesser or equals to zero).
     *
     * @param qtySold The integer to be tested.
     * @return Returns true if integer is below or equals 0.
     */
    private boolean checkIfInvalidQuantity(int qtySold) {
        if (qtySold <= MINIMUM_VALUE) {
            UI.printMessage(SM_MIN_QTY_SOLD_MESSAGE);
            return true;
        }

        return false;
    }

    //@@author rozaliesmit
    /**
     * Clears sales for a product by its ID.
     *
     * @param id product ID.
     */
    public void clearSales(String id) {
        boolean isSuccess = inventory.resetProductSales(id);

        if (isSuccess) {
            UI.printFormattedMessage(SM_CLEAR_FORMAT + NEWLINE, id);
        }
    }

    //@@author LEESY02
    /**
     * Returns the InventoryManager used in the SalesManager class.
     *
     * @return the InventoryManager used by the business.
     */
    public InventoryManager getInventory() {
        return inventory;
    }
}
