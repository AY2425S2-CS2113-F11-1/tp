package busynessmanager.exceptions;

/**
 * Exception for an invalid String.
 */
public class InvalidStringException extends Exception {
    /**
     * Constructs the InvalidString exception.
     */
    public InvalidStringException() {
        super("Input String cannot be split.");
    }
}
