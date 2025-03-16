package busynessmanager.exceptions;

/**
 * Exception for an invalid String.
 */
public class InvalidString extends Exception {

    /**
     * Constructs the InvalidString exception.
     *
     * @param message Message to print along with the exception.
     */
    public InvalidString(String message) {
        super(message);
    }
}
