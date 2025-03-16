package busynessmanager.parser;

public class CommandParser {

    /*
    private InventoryManager inventoryManager;
    private SalesManager salesManager;
    private RevenueCalculator revenueCalculator;
    private SearchManager searchManager;
    */

    // Javadoc comment
    /*
     * Constructs the CommandParser class when the other systems have not been created.
     * This constructor also instantiates the other systems.
     */
    /*
    public CommandParser() {
        this.inventoryManager = new InventoryManager();
        this.salesManager = new SalesManager();
        this.revenueCalculator = new RevenueCalculator();
        this.searchManager = new SearchManager();

    }
    */

    // Javadoc comment
    /*
     * Constructs the CommandParser class given existing systems for the business.
     *
     * @param inventoryManager existing InventoryManager for the business.
     * @param salesManager existing SalesManager for the business.
     * @param revenueCalculator existing RevenueCalculator for the business.
     * @param searchManager existing SearchManager for the business.
     */
    /*
    public CommandParser(InventoryManager inventoryManager, SalesManager salesManager,
                         RevenueCalculator revenueCalculator, SearchManager searchManager) {
        this.inventoryManager = inventoryManager;
        this.salesManager = salesManager;
        this.revenueCalculator = revenueCalculator;
        this.searchManager = searchManager;
    }
    */

    /**
     * Parses the user input for execution.
     *
     * @param input Input from the user.
     */
    public void parseCommand(String input) {
        int commandSeparatorIndex = getCommandSeparatorIndex(input);

        String command = extractCommand(commandSeparatorIndex, input);
        String info = extractInfo(commandSeparatorIndex, input);
    }

    /**
     * Gets the index where the command keyword ends.
     *
     * @param input Input from the user.
     * @return Index where the command keyword ends.
     */
    public int getCommandSeparatorIndex(String input) {
        return input.indexOf(' ');
    }

    /**
     * Returns a String of the command keyword for interpretation.
     *
     * @param commandSeparatorIndex Index where the command keyword ends.
     * @param input Input from the user.
     * @return String containing the command keyword.
     */
    public String extractCommand(int commandSeparatorIndex, String input) {
        return input.substring(0, commandSeparatorIndex);
    }

    /**
     * Returns a String of the information associated with the command keyword.
     *
     * @param commandSeparatorIndex Index where the command keyword ends.
     * @param input Input from the user.
     * @return String containing the information related to the command keyword.
     */
    public String extractInfo(int commandSeparatorIndex, String input) {
        return input.substring(commandSeparatorIndex);
    }



}
