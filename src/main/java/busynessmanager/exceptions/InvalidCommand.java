package busynessmanager.exceptions;

/**
 * Exception for an invalid command.
 */
public class InvalidCommand extends Exception {

    /**
     * Constructs the InvalidCommand exception.
     *
     * @param message Message to print along with the exception.
     */
    public InvalidCommand(String message) {
        super(message);
    }
}
