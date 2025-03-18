package busynessmanager;

import java.util.Scanner;

public class BusynessManager {
    /**
     * Main entry-point for Busyness Manager
     * Currently holding arbitrary test code
     */
    public static void main(String[] args) {


        // test code
        // Himeth's portion
        //Product product1 = new Product("bean", 100, 0.6);
        //Product product2 = new Product("donkey", 6000, 900);
        //Product product3 = new Product("bingbangbong", 1, 10000);

        InventoryManager im = new InventoryManager();
        SearchManager searchManager = new SearchManager(im);

        im.addProduct("bean", 100, 0.6);
        im.addProduct("donkey", 6000, 900);

        im.printProducts();

        im.addProduct("bingbangbong", 1, 10000);

        im.printProducts(); // Ensure items a re sorted in order of ID for readability

        im.deleteProduct(searchManager.searchByName("bingbangbong"));

        im.printProducts();

        // Rozalie's portion
        SalesManager sm = new SalesManager(im);
        sm.recordSale(searchManager.searchByName("bean"), 55);

        im.printProducts();

        sm.clearSales(searchManager.searchByName("bean"));

        im.printProducts();

        sm.recordSale(searchManager.searchByName("bean"), 35);
        sm.recordSale(searchManager.searchByName("donkey"), 2);

        // SY's portion
        RevenueCalculator rc = new RevenueCalculator(sm);

        double beanRevenue = rc.computeProductRevenue(searchManager.searchByName("bean"));

        double totalRevenue = rc.computeTotalRevenue();

        System.out.println(beanRevenue);
        System.out.println(totalRevenue);

        System.out.println(searchManager.searchById("ID_0001")); // Returns Product object
        System.out.println(searchManager.searchByName("bean")); // Returns String object
        
    }
}

