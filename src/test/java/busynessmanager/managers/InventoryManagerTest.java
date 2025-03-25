//@@author LEESY02
package busynessmanager.managers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class InventoryManagerTest {
    private InventoryManager im;

    @BeforeEach
    public void setUp() throws Exception {
        this.im = new InventoryManager();
    }

    @Test
    public void updateProductQuantity_updateQtyAndQtySoldTest_success() {
        im.addProduct("bing", 200, 1);
        im.updateProductQuantity("ID_0001", 100);

        assertEquals(
            "ID_0001: bing | Qty: 100 | Sold: 100 | Price: $1.00",
            im.returnProductList().get("ID_0001").toString()
        );
    }

    @Test
    public void addProduct_addItemTest_success() {
        im.addProduct("bean", 100, 0.6);
        assertEquals(
            "ID_0002: bean | Qty: 100 | Sold: 0 | Price: $0.60",
            im.returnProductList().get("ID_0002").toString()
        );

        im.addProduct("donkey", 6000, 900);
        assertEquals(
            "ID_0003: donkey | Qty: 6000 | Sold: 0 | Price: $900.00",
            im.returnProductList().get("ID_0003").toString()
        );

        im.addProduct("bingbangbong", 1, 10000);
        assertEquals(
            "ID_0004: bingbangbong | Qty: 1 | Sold: 0 | Price: $10000.00",
            im.returnProductList().get("ID_0004").toString()
        );

    }

    @Test
    public void resetProductSales_managerResetSalesTest_success() {
        im.addProduct("gong", 1000, 100);
        im.updateProductQuantity("ID_0005", 100);
        im.resetProductSales("ID_0005");

        assertEquals(
            "ID_0005: gong | Qty: 900 | Sold: 0 | Price: $100.00",
            im.returnProductList().get("ID_0005").toString()
        );
    }

    @Test
    public void updateProduct_updateItemTest_success() {
        im.addProduct("bo", 20, 10000);
        im.updateProduct("ID_0006", "bro", 10, 200);

        assertEquals(
            "ID_0006: bro | Qty: 10 | Sold: 0 | Price: $200.00",
            im.returnProductList().get("ID_0006").toString()
        );
    }

    @Test
    public void deleteProduct_removeItemAndNonExistingIDTest_exceptionThrown() {
        im.addProduct("bingbangbong", 1, 10000);
        im.deleteProduct("ID_0007");

        try {
            assertEquals(
                "ID_0007: bingbangbong | Qty: 1 | Sold: 0 | Price: $10000.00",
                im.returnProductList().get("ID_0007").toString()
            );
            fail();
        } catch (Exception e) {
            assertEquals("Cannot invoke \"busynessmanager.product.Product.toString()\" " +
                    "because the return value of \"java.util.HashMap.get(Object)\" is null",
                e.getMessage()
            );
        }
    }

}
