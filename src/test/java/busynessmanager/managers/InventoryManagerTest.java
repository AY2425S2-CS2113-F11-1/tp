package busynessmanager.managers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class InventoryManagerTest {

    @Test
    public void InventoryManager_addItem_test() {
        InventoryManager im_add = new InventoryManager();

        // "Product added: ID_0001: beans | Qty: 20 | Sold: 0 | Price: $0.60"
        im_add.addProduct("bean", 100, 0.6);
        assertEquals(
            "ID_0002: bean | Qty: 100 | Sold: 0 | Price: $0.60",
            im_add.returnProductList().get("ID_0002").toString()
        );

        im_add.addProduct("donkey", 6000, 900);
        assertEquals(
            "ID_0003: donkey | Qty: 6000 | Sold: 0 | Price: $900.00",
            im_add.returnProductList().get("ID_0003").toString()
        );

        im_add.addProduct("bingbangbong", 1, 10000);
        assertEquals(
            "ID_0004: bingbangbong | Qty: 1 | Sold: 0 | Price: $10000.00",
            im_add.returnProductList().get("ID_0004").toString()
        );

    }

    @Test
    public void InventoryManager_removeItem_nonExistingID_test() {
        InventoryManager im_delete = new InventoryManager();
        im_delete.addProduct("bingbangbong", 1, 10000);
        im_delete.deleteProduct("ID_0001");

        try {
            assertEquals(
                "ID_0001: bingbangbong | Qty: 1 | Sold: 0 | Price: $10000.00",
                im_delete.returnProductList().get("ID_0001").toString()
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