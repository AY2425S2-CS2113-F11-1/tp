package busynessmanager.UI_Constants;

import static busynessmanager.UI_Constants.Constants.*;

/**
 * Class meant for receiving input and giving output to the user
 */
public class UI {

    /**
     * Used to print _MESSAGE suffix constants from Constants class
     * @param message Constant variable from Constants class
     */
    public static void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Used to print _FM suffix constants from Constants class
     * @param message Constant variable from Constants class
     * @param args Variable number of input arguments to be inserted into formatted string
     */
    public static void printFormattedMessage(String message, Object... args) {
        System.out.printf(message, args);
    }
}
