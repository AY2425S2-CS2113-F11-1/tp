import java.util.Scanner;
import java.util.HashMap;

public class BusynessManager {
    private String businessID;
    private String businessName;
    private String businessPassword;
    private String businessType; // Enum: FNB / RETAIL
    private static HashMap<String, String> credentials = new HashMap<>(); // Stores business ID & passwords

    private CommandParser commandParser;
    private InventoryManager inventoryManager;
    private SalesManager salesManager;
    private RevenueCalculator revenueCalculator;
    private SearchManager searchManager;

    public BusynessManager() {
        // Initialize components
        this.inventoryManager = new InventoryManager();
        this.salesManager = new SalesManager(inventoryManager);
        this.revenueCalculator = new RevenueCalculator(inventoryManager);
        this.searchManager = new SearchManager(inventoryManager);
        this.commandParser = new CommandParser(inventoryManager, salesManager, revenueCalculator, searchManager);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Busyness Manager!");

        System.out.print("Enter Business ID: ");
        String id = scanner.nextLine();

        if (credentials.containsKey(id)) {
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();
            if (login(id, password)) {
                System.out.println("Login successful!");
                run(); // Start accepting commands
                return;
            } else {
                System.out.println("Invalid credentials. Exiting.");
                return;
            }
        }

        System.out.println("First-time setup required.");
        System.out.print("Enter Business Name: ");
        businessName = scanner.nextLine();

        System.out.print("Enter Business Password: ");
        businessPassword = scanner.nextLine();

        System.out.print("Enter Business Type (FNB/RETAIL): ");
        businessType = scanner.nextLine().toUpperCase();

        businessID = id;
        credentials.put(businessID, businessPassword);

        System.out.println("Business setup complete!");
        run();
    }

    public boolean login(String id, String password) {
        return credentials.containsKey(id) && credentials.get(id).equals(password);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Busyness Manager is ready. Type 'help' for commands.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting Busyness Manager...");
                break;
            }
            commandParser.parseCommand(input);
        }
        scanner.close();
    }

    public static void main(String[] args) {
        BusynessManager manager = new BusynessManager();
        manager.start();
    }
}