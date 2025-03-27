//@@author b1inmeister
package busynessmanager.exceptions;

/**
 * Exception for an invalid command.
 */
public class InvalidCommandException extends Exception {
    /**
     * Constructs the InvalidCommand exception.
     *
     * @param message Message to print along with the exception.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
