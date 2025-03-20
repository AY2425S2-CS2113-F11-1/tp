package busynessmanager;

import busynessmanager.parser.CommandParser;
import java.util.Scanner;
import java.util.HashMap;

import busynessmanager.managers.InventoryManager;
import busynessmanager.revenue.RevenueCalculator;
import busynessmanager.managers.SalesManager;
import busynessmanager.managers.SearchManager;
import busynessmanager.UI_Constants.UI;

import static busynessmanager.UI_Constants.Constants.BM_UPPERCASE_REGEX;
import static busynessmanager.UI_Constants.Constants.BM_BUSINESSTYPE_FNB;
import static busynessmanager.UI_Constants.Constants.BM_BUSINESSTYPE_RETAIL;
import static busynessmanager.UI_Constants.Constants.BM_WELCOME_MESSAGE;
import static busynessmanager.UI_Constants.Constants.BM_NO_INPUT_ERROR_MESSAGE;
import static busynessmanager.UI_Constants.Constants.BM_ENTER_BUSINESS_ID_MESSAGE;
import static busynessmanager.UI_Constants.Constants.BM_ENTER_PASSWORD_MESSAGE;
import static busynessmanager.UI_Constants.Constants.BM_ENTER_PASSWORD_MESSAGE_2;
import static busynessmanager.UI_Constants.Constants.BM_SUCCESSFUL_LOGIN_MESSAGE;
import static busynessmanager.UI_Constants.Constants.BM_INVALID_CREDENTIALS_MESSAGE;
import static busynessmanager.UI_Constants.Constants.BM_FIRST_SETUP_MESSAGE;
import static busynessmanager.UI_Constants.Constants.BM_ENTER_NAME_MESSAGE;
import static busynessmanager.UI_Constants.Constants.BM_ENTER_BUSINESS_TYPE_MESSAGE;
import static busynessmanager.UI_Constants.Constants.BM_INVALID_BUSINESSTYPE_ERROR_MESSAGE;
import static busynessmanager.UI_Constants.Constants.BM_SETUP_COMPLETE_MESSAGE;
import static busynessmanager.UI_Constants.Constants.BM_READY_MESSAGE;
import static busynessmanager.UI_Constants.Constants.BM_WAITING_INPUT_MESSAGE;
import static busynessmanager.UI_Constants.Constants.BM_EXIT_KEYWORD;
import static busynessmanager.UI_Constants.Constants.BM_EXIT_MESSAGE;

/**
 * The main class for the Busyness Manager application.
 * Handles user authentication, business setup, and command execution.
 */
public class BusynessManager {
    private static final HashMap<String, String> credentials = new HashMap<>(); // Stores business ID & passwords

    private enum BusinessType {
        FNB, RETAIL
    }

    private String businessID;
    private String businessName;
    private String businessPassword;
    private BusinessType businessType;

    private final CommandParser commandParser;

    /**
     * Constructs a BusynessManager instance and initializes the necessary managers.
     */
    public BusynessManager() {
        InventoryManager inventoryManager = new InventoryManager();
        SalesManager salesManager = new SalesManager(inventoryManager);
        RevenueCalculator revenueCalculator = new RevenueCalculator(salesManager);
        SearchManager searchManager = new SearchManager(inventoryManager);
        commandParser = new CommandParser(inventoryManager, salesManager, revenueCalculator, searchManager);
    }


    /**
     * The entry point of the program.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        BusynessManager manager = new BusynessManager();
        manager.start();
    }

    /**
     * Starts the application by handling user login or setup.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Welcome to Busyness Manager!");
        UI.printMessage(BM_WELCOME_MESSAGE);

        //System.out.print("Enter Business ID: ");
        UI.printMessage(BM_ENTER_BUSINESS_ID_MESSAGE);
        if (!scanner.hasNextLine()) {
            //System.err.println("Error: No input detected. Exiting...");
            UI.printErrorMessage(BM_NO_INPUT_ERROR_MESSAGE);
            return;
        }

        String id = scanner.nextLine().trim();

        if (credentials.containsKey(id)) {
            login(scanner, id);
        } else {
            firstTimeSetup(scanner, id);
            run(scanner);
        }
    }

    /**
     * Handles user login.
     *
     * @param scanner The Scanner object for user input.
     * @param id The business ID entered by the user.
     */
    public void login(Scanner scanner, String id) {
        //System.out.print("Enter Password: ");
        UI.printMessage(BM_ENTER_PASSWORD_MESSAGE);
        if (!scanner.hasNextLine()) {
            //System.err.println("Error: No input detected. Exiting...");
            UI.printErrorMessage(BM_NO_INPUT_ERROR_MESSAGE);
            return;
        }
        String password = scanner.nextLine().trim();
        if (validPassword(id, password)) {
            //System.out.println("Login successful!");
            UI.printMessage(BM_SUCCESSFUL_LOGIN_MESSAGE);
            run(scanner);
        } else {
            //System.out.println("Invalid credentials. Exiting.");
            UI.printMessage(BM_INVALID_CREDENTIALS_MESSAGE);
        }
    }

    /**
     * Handles first-time business setup.
     *
     * @param scanner The Scanner object for user input.
     * @param id The business ID entered by the user.
     */
    public void firstTimeSetup(Scanner scanner, String id) {
        //System.out.println("First-time setup required.");
        UI.printMessage(BM_FIRST_SETUP_MESSAGE);

        //System.out.print("Enter Business Name: ");
        UI.printMessage(BM_ENTER_NAME_MESSAGE);
        if (!scanner.hasNextLine()) {
            //System.err.println("Error: No input detected. Exiting...");
            UI.printErrorMessage(BM_NO_INPUT_ERROR_MESSAGE);
            return;
        }
        businessName = scanner.nextLine().trim();

        //System.out.print("Enter Business Password: ");
        UI.printMessage(BM_ENTER_PASSWORD_MESSAGE_2);
        if (!scanner.hasNextLine()) {
            //System.err.println("Error: No input detected. Exiting...");
            UI.printErrorMessage(BM_NO_INPUT_ERROR_MESSAGE);
            return;
        }
        businessPassword = scanner.nextLine().trim();

        //System.out.print("Enter Business Type (FNB/RETAIL): ");
        UI.printMessage(BM_ENTER_BUSINESS_TYPE_MESSAGE);
        if (!scanner.hasNextLine()) {
            //System.err.println("Error: No input detected. Exiting...");
            UI.printErrorMessage(BM_NO_INPUT_ERROR_MESSAGE);
            return;
        }

        BusinessType businessType = null;

        while (businessType == null) {
            String businessTypeString = scanner.nextLine().trim();

            if (businessTypeString.matches(BM_UPPERCASE_REGEX)
                    && businessTypeString.equals(BM_BUSINESSTYPE_FNB)) {
                businessType = BusinessType.FNB;
            } else if (businessTypeString.matches(BM_UPPERCASE_REGEX)
                    && businessTypeString.equals(BM_BUSINESSTYPE_RETAIL)) {
                businessType = BusinessType.RETAIL;
            } else {
                UI.printMessage(BM_INVALID_BUSINESSTYPE_ERROR_MESSAGE);
            }
        }

        businessID = id;
        credentials.put(businessID, businessPassword);

        //System.out.println("Business setup complete!");
        UI.printMessage(BM_SETUP_COMPLETE_MESSAGE);
    }

    /**
     * Validates the entered password for login.
     *
     * @param id The business ID.
     * @param password The password entered by the user.
     * @return True if the password matches, false otherwise.
     */
    public boolean validPassword(String id, String password) {
        return credentials.containsKey(id) && credentials.get(id).equals(password);
    }

    /**
     * Runs the main application loop, waiting for user commands.
     *
     * @param scanner The Scanner object for user input.
     */
    public void run(Scanner scanner) {
        //System.out.println("Busyness Manager is ready. Type 'help' for commands.");
        UI.printMessage(BM_READY_MESSAGE);
        while (true) {
            //System.out.print(">");
            UI.printMessageWithoutNewline(BM_WAITING_INPUT_MESSAGE);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase(BM_EXIT_KEYWORD)) {
                //System.out.println("Exiting Busyness Manager...");
                UI.printMessage(BM_EXIT_MESSAGE);
                break;
            }

            commandParser.parseCommand(input);
        }

        scanner.close();
    }
}
