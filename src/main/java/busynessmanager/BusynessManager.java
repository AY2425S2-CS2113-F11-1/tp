//@@author amirhusaini06
package busynessmanager;

import busynessmanager.managers.InventoryManager;
import busynessmanager.managers.SalesManager;
import busynessmanager.managers.SearchManager;
import busynessmanager.revenue.RevenueCalculator;
import busynessmanager.parser.CommandParser;
import busynessmanager.ui.UI;
import busynessmanager.credentials.Credentials;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static busynessmanager.constants.Constants.NEWLINE;
import static busynessmanager.constants.Constants.INDEX_0;
import static busynessmanager.constants.Constants.INDEX_1;
import static busynessmanager.constants.Constants.INDEX_2;
import static busynessmanager.constants.Constants.INDEX_3;
import static busynessmanager.constants.Constants.INDEX_4;
import static busynessmanager.constants.Constants.FILE_REGEX;
import static busynessmanager.constants.Constants.BM_UPPERCASE_REGEX;
import static busynessmanager.constants.Constants.BM_BUSINESSTYPE_FNB;
import static busynessmanager.constants.Constants.BM_BUSINESSTYPE_RETAIL;
import static busynessmanager.constants.Constants.BM_FIRST_SETUP_APPROVAL;
import static busynessmanager.constants.Constants.BM_NAME_TITLE;
import static busynessmanager.constants.Constants.BM_TYPE_TITLE;
import static busynessmanager.constants.Constants.BM_ID_TITLE;
import static busynessmanager.constants.Constants.BM_INVENTORY_TITLE;
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
import static busynessmanager.constants.Constants.BM_INVALID_ID_ERROR_MESSAGE;
import static busynessmanager.constants.Constants.BM_INVALID_BUSINESSTYPE_ERROR_MESSAGE;
import static busynessmanager.constants.Constants.BM_SETUP_COMPLETE_MESSAGE;
import static busynessmanager.constants.Constants.BM_READY_MESSAGE;
import static busynessmanager.constants.Constants.BM_WAITING_INPUT_MESSAGE;
import static busynessmanager.constants.Constants.BM_EXIT_KEYWORD;
import static busynessmanager.constants.Constants.BM_EXIT_MESSAGE;
import static busynessmanager.constants.Constants.BM_ID_ASSERTION_FAIL_MESSAGE;
import static busynessmanager.constants.Constants.BM_PASSWORD_NULL_ASSERTION_FAIL_MESSAGE;
import static busynessmanager.constants.Constants.BM_NO_CREDENTIALS_MESSAGE;
import static busynessmanager.constants.Constants.BM_NO_DATA_MESSAGE;
import static busynessmanager.constants.Constants.BM_SAVE_FAIL_MESSAGE;
import static busynessmanager.constants.Constants.BM_LOAD_SUCCESS_MESSAGE;
import static busynessmanager.constants.Constants.BM_LOAD_FAIL_MESSAGE;


/**
 * The main class for the Busyness Manager application.
 * Handles user authentication, business setup, and command execution.
 */
public class BusynessManager {
    private static final String DATA_FOLDER = "data";
    private static final String BUSINESS_INFO_FILE = DATA_FOLDER + "/BusinessInfo.txt";

    public enum BusinessType {
        FNB, RETAIL
    }

    private Credentials credentials;
    private final InventoryManager inventoryManager;
    private final CommandParser commandParser;


    /**
     * Constructs a BusynessManager instance and initializes the necessary managers.
     * Also loads business credentials if available.
     */
    public BusynessManager() {
        inventoryManager = new InventoryManager();
        SalesManager salesManager = new SalesManager(inventoryManager);
        RevenueCalculator revenueCalculator = new RevenueCalculator(salesManager);
        SearchManager searchManager = new SearchManager(inventoryManager);
        commandParser = new CommandParser(inventoryManager, salesManager, revenueCalculator, searchManager);
        loadBusinessData();
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
     * Starts the application by handling user authentication or first-time setup.
     */
    private void start() {
        Scanner scanner = new Scanner(System.in);
        UI.printMessage(BM_WELCOME_MESSAGE);

        if (credentials == null) {
            UI.printMessageWithoutNewline(BM_FIRST_SETUP_CHECK_MESSAGE);
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase(BM_FIRST_SETUP_APPROVAL)) {
                firstTimeSetup(scanner);
            } else {
                UI.printMessage(BM_EXIT_MESSAGE);
                return;
            }
        } else {
            login(scanner);
        }

        run(scanner);
    }

    /**
     * Handles user login.
     *
     * @param scanner The Scanner object for user input.
     */
    protected void login(Scanner scanner) {
        UI.printMessageWithoutNewline(BM_ENTER_BUSINESS_ID_MESSAGE);
        String id = scanner.nextLine().trim();

        if (!id.matches("\\d+")) {
            UI.printMessage(BM_INVALID_ID_ERROR_MESSAGE);
            System.exit(INDEX_0);
        }

        UI.printMessageWithoutNewline(BM_ENTER_PASSWORD_MESSAGE);
        String password = scanner.nextLine().trim();

        if (credentials != null && credentials.getBusinessID().equals(id) &&
                credentials.getBusinessPassword().equals(password)) {
            UI.printMessage(BM_SUCCESSFUL_LOGIN_MESSAGE);
        } else {
            UI.printMessage(BM_INVALID_CREDENTIALS_MESSAGE);
            System.exit(INDEX_0);
        }
    }

    /**
     * Handles first-time business setup, allowing user to register their business.
     *
     * @param scanner The Scanner object for user input.
     */
    protected void firstTimeSetup(Scanner scanner) {
        String id = extractID(scanner);
        String name = extractName(scanner);
        String password = extractPassword(scanner);
        BusinessType type = extractBusinessType(scanner);

        credentials = new Credentials(id, name, password, type);
        saveBusinessData();
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
            saveBusinessData();
        }
        scanner.close();
    }

    /**
     * Saves business credentials and inventory data to a file.
     */
    private void saveBusinessData() {
        File dataFolder = new File(DATA_FOLDER);

        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BUSINESS_INFO_FILE))) {
            if (credentials != null) {
                writer.write(credentials.getBusinessID() + FILE_REGEX +
                        credentials.getBusinessName() + FILE_REGEX +
                        credentials.getBusinessPassword() + FILE_REGEX +
                        credentials.getBusinessType() + NEWLINE);
            }

            writer.write(BM_INVENTORY_TITLE + NEWLINE);
            writer.write(inventoryManager.getInventoryData());
        } catch (IOException e) {
            UI.printMessage(BM_SAVE_FAIL_MESSAGE + e.getMessage());
        }
    }


    /**
     * Loads business credentials and inventory data from a file.
     */
    private void loadBusinessData() {
        File file = new File(BUSINESS_INFO_FILE);

        if (!file.exists()) {
            UI.printMessage(BM_NO_DATA_MESSAGE);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            if (line != null && !line.startsWith(BM_INVENTORY_TITLE)) {
                String[] parts = line.split(FILE_REGEX);

                if (parts.length == INDEX_4) {
                    credentials = new Credentials(parts[INDEX_0], parts[INDEX_1], parts[INDEX_2],
                            BusinessType.valueOf(parts[INDEX_3]));
                }
            }

            while ((line = reader.readLine()) != null) {
                if (line.equals(BM_INVENTORY_TITLE)) {
                    inventoryManager.loadInventory(reader);
                }
            }

            UI.printMessage(BM_LOAD_SUCCESS_MESSAGE);
        } catch (IOException e) {
            UI.printMessage(BM_LOAD_FAIL_MESSAGE + e.getMessage());
        }
    }

    /**
     * Extracts the name of the business from the user input.
     *
     * @param scanner The scanner object for user input.
     * @return The business name inputted by the user.
     */
    private String extractID(Scanner scanner) {
        String businessID = "";

        while (businessID.isEmpty()) {
            UI.printMessageWithoutNewline(BM_ENTER_BUSINESS_ID_MESSAGE);

            if (!scanner.hasNextLine()) {
                UI.printErrorMessage(BM_NO_INPUT_ERROR_MESSAGE);
            } else {
                businessID = scanner.nextLine().trim();
            }

            if (businessID.isEmpty()) {
                UI.printMessage(BM_NO_INPUT_ERROR_MESSAGE);
            }
        }

        return businessID;
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
    protected BusinessType extractBusinessType(Scanner scanner) {
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

    /**
     * Validates the entered password for login.
     *
     * @param id       The business ID.
     * @param password The password entered by the user.
     * @return True if the credentials match, false otherwise.
     */
    protected boolean validPassword(String id, String password) {
        assert id != null && !id.isEmpty() : BM_ID_ASSERTION_FAIL_MESSAGE;
        assert password != null : BM_PASSWORD_NULL_ASSERTION_FAIL_MESSAGE;

        return credentials != null &&
                credentials.getBusinessID().equals(id) &&
                credentials.getBusinessPassword().equals(password);
    }

    /**
     * Retrieves business details as a formatted string.
     *
     * @return A string containing business credentials.
     */
    public String getBusinessDetails() {
        if (credentials == null) {
            return BM_NO_CREDENTIALS_MESSAGE;
        }

        return BM_ID_TITLE + credentials.getBusinessID() + "\n"
                + BM_NAME_TITLE + credentials.getBusinessName() + "\n"
                + BM_TYPE_TITLE + credentials.getBusinessType();
    }

    /**
     * Retrieves the inventory manager instance.
     *
     * @return The {@code InventoryManager} instance managing the business inventory.
     */
    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }
}
