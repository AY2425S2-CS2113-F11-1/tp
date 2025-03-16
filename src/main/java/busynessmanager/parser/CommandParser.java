package busynessmanager.parser;

public class CommandParser {

    /**
     * Constructs the CommandParser class.
     * NOTE: temporary constructor for JUnit testing.
     */
    public CommandParser() {}

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
