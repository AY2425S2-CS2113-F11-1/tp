//@@author b1inmeister
package busynessmanager.exceptions;

/**
 * Exception for when parsing a String to a number has failed.
 */
public class NumberParsingFailedException extends Exception {
    /**
     * Constructs the NumberParsingFailed exception.
     */
    public NumberParsingFailedException() {
        super("Conversion to a number has failed.");
    }
}
