package busynessmanager.managers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        sm.recordSale("ID_0009", 80);

        assertEquals(
            "ID_0009: biangblong | Qty: 340 | Sold: 80 | Price: $2100.00",
            im.returnProductList().get("ID_0009").toString()
        );
    }

    @Test
    public void resetProductSales_resetSalesTest_success() {
        im.addProduct("blangbliong", 420, 2100);
        sm.recordSale("ID_0008", 80);
        im.resetProductSales("ID_0008");

        assertEquals(
            "ID_0008: blangbliong | Qty: 340 | Sold: 0 | Price: $2100.00",
            im.returnProductList().get("ID_0008").toString()
        );
    }
}
