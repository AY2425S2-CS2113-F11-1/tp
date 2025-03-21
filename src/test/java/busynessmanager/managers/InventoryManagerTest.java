package busynessmanager.managers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class InventoryManagerTest {
    private InventoryManager im;

    @BeforeEach
    public void setUp() throws Exception {
        this.im = new InventoryManager();
    }

    @Test
    public void InventoryManager_removeItem_nonExistingID_test() {
        im.addProduct("bingbangbong", 1, 10000);
        im.deleteProduct("ID_0001");

        try {
            assertEquals(
                "ID_0001: bingbangbong | Qty: 1 | Sold: 0 | Price: $10000.00",
                im.returnProductList().get("ID_0001").toString()
            );
            fail();
        } catch (Exception e) {
            assertEquals("Cannot invoke \"busynessmanager.product.Product.toString()\" " +
                    "because the return value of \"java.util.HashMap.get(Object)\" is null",
                e.getMessage()
            );
        }
    }

    @Test
    public void InventoryManager_resetSales_test() {
        im.addProduct("gong", 1000, 100);
        im.updateProductQuantity("ID_0002", 100);
        im.resetProductSales("ID_0002");

        assertEquals(
            "ID_0002: gong | Qty: 900 | Sold: 0 | Price: $100.00",
            im.returnProductList().get("ID_0002").toString()
        );
    }

    @Test
    public void InventoryManager_addItem_test() {
        // "Product added: ID_0001: beans | Qty: 20 | Sold: 0 | Price: $0.60"
        im.addProduct("bean", 100, 0.6);
        assertEquals(
            "ID_0003: bean | Qty: 100 | Sold: 0 | Price: $0.60",
            im.returnProductList().get("ID_0003").toString()
        );

        im.addProduct("donkey", 6000, 900);
        assertEquals(
            "ID_0004: donkey | Qty: 6000 | Sold: 0 | Price: $900.00",
            im.returnProductList().get("ID_0004").toString()
        );

        im.addProduct("bingbangbong", 1, 10000);
        assertEquals(
            "ID_0005: bingbangbong | Qty: 1 | Sold: 0 | Price: $10000.00",
            im.returnProductList().get("ID_0005").toString()
        );

    }

    @Test
    public void InventoryManager_updateItem_test() {
        im.addProduct("bo", 20, 10000);
        im.updateProduct("ID_0006", "bro", 10, 200);
        assertEquals(
            "ID_0006: bro | Qty: 10 | Sold: 0 | Price: $200.00",
            im.returnProductList().get("ID_0006").toString()
        );
    }

    @Test
    public void InventoryManager_update_Qty_QtySold_test() {
        im.addProduct("bing", 200, 1);
        im.updateProductQuantity("ID_0007", 100);
        assertEquals(
            "ID_0007: bing | Qty: 100 | Sold: 100 | Price: $1.00",
            im.returnProductList().get("ID_0007").toString()
        );
    }

}