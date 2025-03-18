package busynessmanager;

import java.util.Scanner;
import java.util.HashMap;

public class BusynessManager {
    private static HashMap<String, String> credentials = new HashMap<>(); // Stores business ID & passwords

    private String businessID;
    private String businessName;
    private String businessPassword;
    private String businessType; // Enum: FNB / RETAIL

    /*
    private CommandParser commandParser;
    private InventoryManager inventoryManager;
    private SalesManager salesManager;
    private RevenueCalculator revenueCalculator;
    private SearchManager searchManager;
    */

    public BusynessManager() {
        /*
        this.inventoryManager = new InventoryManager();
        this.salesManager = new SalesManager(inventoryManager);
        this.revenueCalculator = new RevenueCalculator(inventoryManager);
        this.searchManager = new SearchManager(inventoryManager);
        this.commandParser = new CommandParser(inventoryManager, salesManager, revenueCalculator, searchManager);
        */
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Busyness Manager!");

        System.out.print("Enter Business ID: ");
        if (!scanner.hasNextLine()) {
            System.err.println("Error: No input detected. Exiting...");
            return;
        }
        String id = scanner.nextLine().trim();

        if (credentials.containsKey(id)) {
            System.out.print("Enter Password: ");
            if (!scanner.hasNextLine()) {
                System.err.println("Error: No input detected. Exiting...");
                return;
            }
            String password = scanner.nextLine().trim();
            if (login(id, password)) {
                System.out.println("Login successful!");
                run(scanner);
            } else {
                System.out.println("Invalid credentials. Exiting.");
            }
        } else {
            System.out.println("First-time setup required.");

            System.out.print("Enter Business Name: ");
            if (!scanner.hasNextLine()) {
                System.err.println("Error: No input detected. Exiting...");
                return;
            }
            businessName = scanner.nextLine().trim();

            System.out.print("Enter Business Password: ");
            if (!scanner.hasNextLine()) {
                System.err.println("Error: No input detected. Exiting...");
                return;
            }
            businessPassword = scanner.nextLine().trim();

            System.out.print("Enter Business Type (FNB/RETAIL): ");
            if (!scanner.hasNextLine()) {
                System.err.println("Error: No input detected. Exiting...");
                return;
            }
            businessType = scanner.nextLine().trim().toUpperCase();

            businessID = id;
            credentials.put(businessID, businessPassword);

            System.out.println("Business setup complete!");
            run(scanner);
        }
    }


    public boolean login(String id, String password) {
        return credentials.containsKey(id) && credentials.get(id).equals(password);
    }

    public void run(Scanner scanner) {
        System.out.println("Busyness Manager is ready. Type 'help' for commands.");
        while (true) {
            System.out.print(">");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting Busyness Manager...");
                break;
            }

            //commandParser.parseCommand(input);
        }
        scanner.close();
    }

    public static void main(String[] args) {
        BusynessManager manager = new BusynessManager();
        manager.start();
    }
}
