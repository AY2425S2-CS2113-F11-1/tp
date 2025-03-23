package busynessmanager;

import busynessmanager.managers.InventoryManager;
import busynessmanager.managers.SalesManager;
import busynessmanager.managers.SearchManager;
import busynessmanager.revenue.RevenueCalculator;
import busynessmanager.parser.CommandParser;
import busynessmanager.ui.UI;

import static busynessmanager.constants.Constants.BM_UPPERCASE_REGEX;
import static busynessmanager.constants.Constants.BM_BUSINESSTYPE_FNB;
import static busynessmanager.constants.Constants.BM_BUSINESSTYPE_RETAIL;
import static busynessmanager.constants.Constants.BM_FIRST_SETUP_APPROVAL;
import static busynessmanager.constants.Constants.BM_WELCOME_MESSAGE;
import static busynessmanager.constants.Constants.BM_NO_INPUT_ERROR_MESSAGE;
import static busynessmanager.constants.Constants.BM_FIRST_SETUP_CHECK_MESSAGE;
import static busynessmanager.constants.Constants.BM_ENTER_BUSINESS_ID_MESSAGE;
import static busynessmanager.constants.Constants.BM_ENTER_PASSWORD_MESSAGE;
import static busynessmanager.constants.Constants.BM_ENTER_PASSWORD_MESSAGE_2;
import static busynessmanager.constants.Constants.BM_SUCCESSFUL_LOGIN_MESSAGE;
import static busynessmanager.constants.Constants.BM_INVALID_CREDENTIALS_MESSAGE;
import static busynessmanager.constants.Constants.BM_ENTER_NAME_MESSAGE;
import static busynessmanager.constants.Constants.BM_ENTER_BUSINESS_TYPE_MESSAGE;
import static busynessmanager.constants.Constants.BM_INVALID_BUSINESSTYPE_ERROR_MESSAGE;
import static busynessmanager.constants.Constants.BM_SETUP_COMPLETE_MESSAGE;
import static busynessmanager.constants.Constants.BM_READY_MESSAGE;
import static busynessmanager.constants.Constants.BM_WAITING_INPUT_MESSAGE;
import static busynessmanager.constants.Constants.BM_EXIT_KEYWORD;
import static busynessmanager.constants.Constants.BM_EXIT_MESSAGE;

import java.util.Scanner;
import java.util.HashMap;


/**
 * The main class for the Busyness Manager application.
 * Handles user authentication, business setup, and command execution.
 */
public class BusynessManager {
    // Stores business ID & passwords
    private static final HashMap<String, String> credentials = new HashMap<>();

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
    private void start() {
        Scanner scanner = new Scanner(System.in);

        UI.printMessage(BM_WELCOME_MESSAGE);
        UI.printMessageWithoutNewline(BM_ENTER_BUSINESS_ID_MESSAGE);

        if (!scanner.hasNextLine()) {
            UI.printErrorMessage(BM_NO_INPUT_ERROR_MESSAGE);
            return;
        }

        String id = scanner.nextLine().trim();

        if (credentials.containsKey(id)) {
            login(scanner, id);
        } else {
            String reply;

            UI.printMessageWithoutNewline(BM_FIRST_SETUP_CHECK_MESSAGE);

            if (!scanner.hasNextLine()) {
                UI.printErrorMessage(BM_NO_INPUT_ERROR_MESSAGE);
                return;
            } else {
                reply = scanner.nextLine();
            }

            if (reply.equalsIgnoreCase(BM_FIRST_SETUP_APPROVAL)) {
                firstTimeSetup(scanner, id);
                run(scanner);
            } else {
                UI.printMessage(BM_EXIT_MESSAGE);
            }
        }
    }

    /**
     * Handles user login.
     *
     * @param scanner The Scanner object for user input.
     * @param id The business ID entered by the user.
     */
    private void login(Scanner scanner, String id) {
        UI.printMessageWithoutNewline(BM_ENTER_PASSWORD_MESSAGE);

        if (!scanner.hasNextLine()) {
            UI.printErrorMessage(BM_NO_INPUT_ERROR_MESSAGE);
            return;
        }

        String password = scanner.nextLine().trim();

        if (validPassword(id, password)) {
            UI.printMessage(BM_SUCCESSFUL_LOGIN_MESSAGE);
            run(scanner);
        } else {
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
        businessName = extractName(scanner);
        businessPassword = extractPassword(scanner);
        businessType = extractBusinessType(scanner);
        businessID = id;

        credentials.put(businessID, businessPassword);

        UI.printMessage(BM_SETUP_COMPLETE_MESSAGE);
    }

    /**
     * Runs the main application loop, waiting for user commands.
     *
     * @param scanner The Scanner object for user input.
     */
    private void run(Scanner scanner) {
        UI.printMessage(BM_READY_MESSAGE);

        while (true) {
            UI.printMessageWithoutNewline(BM_WAITING_INPUT_MESSAGE);

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase(BM_EXIT_KEYWORD)) {
                UI.printMessage(BM_EXIT_MESSAGE);
                break;
            }

            commandParser.parseCommand(input);
        }

        scanner.close();
    }

    /**
     * Validates the entered password for login.
     *
     * @param id The business ID.
     * @param password The password entered by the user.
     * @return True if the password matches, false otherwise.
     */
    private boolean validPassword(String id, String password) {
        return credentials.containsKey(id) && credentials.get(id).equals(password);
    }

    /**
     * Extracts the name of the business from the user input.
     *
     * @param scanner The scanner object for user input.
     * @return The business name inputted by the user.
     */
    private String extractName(Scanner scanner) {
        String businessName = "";

        while (businessName.isEmpty()) {
            UI.printMessageWithoutNewline(BM_ENTER_NAME_MESSAGE);

            if (!scanner.hasNextLine()) {
                UI.printErrorMessage(BM_NO_INPUT_ERROR_MESSAGE);
            } else {
                businessName = scanner.nextLine().trim();
            }

            if (businessName.isEmpty()) {
                UI.printMessage(BM_NO_INPUT_ERROR_MESSAGE);
            }
        }

        return businessName;
    }

    /**
     * Extracts the password from the user input.
     *
     * @param scanner The scanner object for user input.
     * @return The password inputted by the user.
     */
    private String extractPassword(Scanner scanner) {
        String businessPassword = "";

        while (businessPassword.isEmpty()) {
            UI.printMessageWithoutNewline(BM_ENTER_PASSWORD_MESSAGE_2);

            if (!scanner.hasNextLine()) {
                UI.printErrorMessage(BM_NO_INPUT_ERROR_MESSAGE);
            } else {
                businessPassword = scanner.nextLine().trim();
            }

            if (businessPassword.isEmpty()) {
                UI.printMessage(BM_NO_INPUT_ERROR_MESSAGE);
            }
        }

        return businessPassword;
    }

    /**
     * Extracts the BusinessType from the user input.
     *
     * @param scanner The scanner object for user input.
     * @return The BusinessType inputted by the user.
     */
    private BusinessType extractBusinessType(Scanner scanner) {
        BusinessType businessType = null;

        while (businessType == null) {
            UI.printMessageWithoutNewline(BM_ENTER_BUSINESS_TYPE_MESSAGE);

            if (!scanner.hasNextLine()) {
                UI.printErrorMessage(BM_NO_INPUT_ERROR_MESSAGE);
            } else {
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
        }

        return businessType;
    }
}
