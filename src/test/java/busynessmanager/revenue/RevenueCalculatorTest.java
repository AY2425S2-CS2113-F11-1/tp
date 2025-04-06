//@@author LEESY02
package busynessmanager.revenue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import busynessmanager.managers.InventoryManager;
import busynessmanager.managers.SalesManager;


public class RevenueCalculatorTest {
    InventoryManager im;
    SalesManager sm;
    RevenueCalculator rc;

    @BeforeEach
    public void setUp() throws Exception {
        im = new InventoryManager();
        sm = new SalesManager(im);
        rc = new RevenueCalculator(sm);
    }

    @Test
    public void computeProductRevenue_individualRevenueTest_success() {
        im.addProduct("bean", 100, 0.6);
        im.addProduct("donkey", 6000, 900);
        im.addProduct("bingbangbong", 1, 10000);
        sm.recordSale("ID_0001", 50);
        sm.recordSale("ID_0002", 10);
        sm.recordSale("ID_0003", 1);

        im.printProducts();

        assertEquals(
            30,
            rc.computeProductRevenue("ID_0001")
        );

        assertEquals(
            9000,
            rc.computeProductRevenue("ID_0002")
        );

        assertEquals(
            10000,
            rc.computeProductRevenue("ID_0003")
        );
    }

    @Test
    public void computeTotalRevenue_totalRevenueTest_success() {
        im.addProduct("bean", 100, 0.6); // ID_0010
        im.addProduct("donkey", 6000, 900);
        im.addProduct("bingbangbong", 1, 10000);
        sm.recordSale("ID_0004", 50);
        sm.recordSale("ID_0005", 10);
        sm.recordSale("ID_0006", 1);

        assertEquals(
            19030,
            rc.computeTotalRevenue()
        );
    }
}
