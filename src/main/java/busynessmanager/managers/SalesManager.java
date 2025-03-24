package busynessmanager.managers;

import busynessmanager.ui.UI;

import static busynessmanager.constants.Constants.NEWLINE;
import static busynessmanager.constants.Constants.SM_MINIMUM_QTY_SOLD_MESSAGE;
import static busynessmanager.constants.Constants.SM_CLEARED_FORMAT;
import static busynessmanager.constants.Constants.SM_RECORDED_FORMAT;


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
        if (qtySold <= 0) {
            UI.printMessage(SM_MINIMUM_QTY_SOLD_MESSAGE);
            return;
        }

        boolean isSuccess = inventory.updateProductQuantity(id, qtySold);

        if (isSuccess) {
            UI.printFormattedMessage(SM_RECORDED_FORMAT + NEWLINE, id, qtySold);
        }
    }

    /**
     * Clears sales for a product by its ID.
     *
     * @param id product ID.
     */
    public void clearSales(String id) {
        boolean isSuccess = inventory.resetProductSales(id);

        if (isSuccess) {
            UI.printFormattedMessage(SM_CLEARED_FORMAT + NEWLINE, id);
        }
    }

    /**
     * Returns the InventoryManager used in the SalesManager class.
     *
     * @return the InventoryManager used by the business.
     */
    public InventoryManager getInventory() {
        return inventory;
    }
}
