//@@author LEESY02
package busynessmanager.managers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SalesManagerTest {
    InventoryManager im;
    SalesManager sm;

    @BeforeEach
    public void setUp() throws Exception {
        this.im = new InventoryManager();
        this.sm = new SalesManager(im);
    }

    @Test
    public void recordSale_recordSalesTest_success() {
        im.addProduct("biangblong", 420, 2100);
        sm.recordSale("ID_0002", 80);

        assertEquals(
            "ID_0002: biangblong | Qty: 340 | Sold: 80 | Price: $2100.00",
            im.returnProductList().get("ID_0002").toString()
        );
    }

    @Test
    public void resetProductSales_resetSalesTest_success() {
        im.addProduct("blangbliong", 420, 2100);
        sm.recordSale("ID_0001", 80);
        im.resetProductSales("ID_0001");

        assertEquals(
            "ID_0001: blangbliong | Qty: 340 | Sold: 0 | Price: $2100.00",
            im.returnProductList().get("ID_0001").toString()
        );
    }
}
