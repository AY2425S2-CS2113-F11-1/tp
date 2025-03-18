package busynessmanager;

import java.util.Scanner;

public class BusynessManager {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        // test code
        Product product1 = new Product("beans", 1, 0.6);
        Product product2 = new Product("donkey", 6, 200);

        InventoryManager im = new InventoryManager();
        im.addProduct(product1.getName(), product1.getQuantity(), product1.getPrice());
        im.addProduct(product2.getName(), product2.getQuantity(), product2.getPrice());


        im.printProducts();



        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}

