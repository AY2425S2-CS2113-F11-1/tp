//@@author b1inmeister
package busynessmanager.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;

import busynessmanager.exceptions.InvalidStringException;
import busynessmanager.exceptions.InvalidCommandException;
import busynessmanager.exceptions.NumberParsingFailedException;


public class CommandParserTest {
    /*
     * getCommandSeparatorIndex()
     */
    @Test
    public void getCommandSeparatorIndex_validString_success() throws InvalidStringException {
        assertEquals(3, new CommandParser().getCommandSeparatorIndex(
                "add MILK 50 2.50"));
        assertEquals(6, new CommandParser().getCommandSeparatorIndex(
                "delete 1"));
        assertEquals(6, new CommandParser().getCommandSeparatorIndex(
                "update 1 /name FRESH_MILK"));
        assertEquals(6, new CommandParser().getCommandSeparatorIndex(
                "update 1 /qty 45"));
        assertEquals(6, new CommandParser().getCommandSeparatorIndex(
                "update 1 /price 3.00"));
        assertEquals(4, new CommandParser().getCommandSeparatorIndex(
                "sold 1 5"));
        assertEquals(5, new CommandParser().getCommandSeparatorIndex(
                "clear 1"));
        assertEquals(7, new CommandParser().getCommandSeparatorIndex(
                "revenue 1"));
        assertEquals(6, new CommandParser().getCommandSeparatorIndex(
                "search /name MILK"));
        assertEquals(6, new CommandParser().getCommandSeparatorIndex(
                "search /id 1"));

        // Test cases for when there is one word as the user input.
        assertEquals(-1, new CommandParser().getCommandSeparatorIndex(
                "print"));
        assertEquals(-1, new CommandParser().getCommandSeparatorIndex(
                "revenue"));

        /*
         * Test case where erroneous inputs are given.
         * These tests will pass for now, and throw an exception later on in the execution.
         */
        assertEquals(4, new CommandParser().getCommandSeparatorIndex(
                "ding dong"));
        assertEquals(-1, new CommandParser().getCommandSeparatorIndex(
                "cat"));
        assertEquals(-1, new CommandParser().getCommandSeparatorIndex(
                ""));
    }

    @Test
    public void getCommandSeparatorIndex_nullString_exceptionThrown() {
        try {
            assertEquals(-1, new CommandParser().getCommandSeparatorIndex(
                    null));
            fail();
        } catch (InvalidStringException e) {
            assertEquals("Input String cannot be split.", e.getMessage());
        }
    }


    /*
     * extractCommand()
     */
    @Test
    public void extractCommand_positiveIndexWithinLimit_success() throws InvalidStringException {
        assertEquals("add", new CommandParser().extractCommand(
                3, "add MILK 50 2.50"));
        assertEquals("delete", new CommandParser().extractCommand(
                6, "delete 1"));
        assertEquals("update", new CommandParser().extractCommand(
                6, "update 1 /name FRESH_MILK"));
        assertEquals("update", new CommandParser().extractCommand(
                6, "update 1 /qty 45"));
        assertEquals("update", new CommandParser().extractCommand(
                6, "update 1 /price 3.00"));
        assertEquals("sold", new CommandParser().extractCommand(
                4, "sold 1 5"));
        assertEquals("clear", new CommandParser().extractCommand(
                5, "clear 1"));
        assertEquals("revenue", new CommandParser().extractCommand(
                7, "revenue 1"));
        assertEquals("search", new CommandParser().extractCommand(
                6, "search /name MILK"));
        assertEquals("search", new CommandParser().extractCommand(
                6, "search /id 1"));

        // Test cases for when there is one word as the user input.
        assertEquals("print", new CommandParser().extractCommand(
                -1, "print"));
        assertEquals("revenue", new CommandParser().extractCommand(
                -1, "revenue"));

        /*
         * Test cases where erroneous inputs are given.
         * These tests will pass for now, and throw an exception later on in the execution.
         */
        assertEquals("ding", new CommandParser().extractCommand(
                4, "ding dong"));
        assertEquals("cat", new CommandParser().extractCommand(
                -1, "cat"));
        assertEquals("", new CommandParser().extractCommand(
                -1, ""));
    }

    @Test
    public void extractCommand_positiveIndexOutsideLimit_exceptionThrown() {
        try {
            assertEquals("", new CommandParser().extractCommand(
                    4, "cat"));
            fail();
        } catch (InvalidStringException e) {
            assertEquals("Input String cannot be split.", e.getMessage());
        }
    }

    @Test
    public void extractCommand_negativeIndex_exceptionThrown() {
        try {
            assertEquals("", new CommandParser().extractCommand(
                    -2, "ding dong"));
            fail();
        } catch (InvalidStringException e) {
            assertEquals("Input String cannot be split.", e.getMessage());
        }
    }


    /*
     * extractInfo()
     */
    @Test
    public void extractInfo_positiveIndexWithinLimit_success() throws InvalidStringException {
        assertEquals("MILK 50 2.50", new CommandParser().extractInfo(
                3, "add MILK 50 2.50"));
        assertEquals("1", new CommandParser().extractInfo(
                6, "delete 1"));
        assertEquals("1 /name FRESH_MILK", new CommandParser().extractInfo(
                6, "update 1 /name FRESH_MILK"));
        assertEquals("1 /qty 45", new CommandParser().extractInfo(
                6, "update 1 /qty 45"));
        assertEquals("1 /price 3.00", new CommandParser().extractInfo(
                6, "update 1 /price 3.00"));
        assertEquals("1 5", new CommandParser().extractInfo(
                4, "sold 1 5"));
        assertEquals("1", new CommandParser().extractInfo(
                5, "clear 1"));
        assertEquals("1", new CommandParser().extractInfo(
                7, "revenue 1"));
        assertEquals("/name MILK", new CommandParser().extractInfo(
                6, "search /name MILK"));
        assertEquals("/id 1", new CommandParser().extractInfo(
                6, "search /id 1"));

        // Test cases for when there is one word as the user input.
        assertEquals("", new CommandParser().extractInfo(
                -1, "print"));
        assertEquals("", new CommandParser().extractInfo(
                -1, "revenue"));

        /*
         * Test cases where erroneous inputs are given.
         * These tests will pass for now, and throw an exception later on in the execution.
         */
        assertEquals("dong", new CommandParser().extractInfo(
                4, "ding dong"));
        assertEquals("", new CommandParser().extractInfo(
                -1, "cat"));
        assertEquals("", new CommandParser().extractInfo(
                -1, ""));
    }

    @Test
    public void extractInfo_positiveIndexOutsideLimit_exceptionThrown() {
        try {
            assertEquals("", new CommandParser().extractInfo(
                    4, "cat"));
            fail();
        } catch (InvalidStringException e) {
            assertEquals("Input String cannot be split.", e.getMessage());
        }
    }

    @Test
    public void extractInfo_negativeIndex_exceptionThrown() {
        try {
            assertEquals("", new CommandParser().extractInfo(
                    -2, "ding dong"));
            fail();
        } catch (InvalidStringException e) {
            assertEquals("Input String cannot be split.", e.getMessage());
        }
    }


    /*
     * executeCommand()
     */
    @Test
    public void executeCommand_wrongWord_exceptionThrown() {
        try {
            new CommandParser().executeCommand("ding", "dong");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The command provided does not exist. Please try again.", e.getMessage());
        }

        try {
            new CommandParser().executeCommand("cat", "");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The command provided does not exist. Please try again.", e.getMessage());
        }
    }

    @Test
    public void executeCommand_emptyString_exceptionThrown() {
        try {
            new CommandParser().executeCommand("", "");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The command provided does not exist. Please try again.", e.getMessage());
        }
    }


    /*
     * addProduct()
     */
    @Test
    public void addProduct_nameEmpty_exceptionThrown() {
        try {
            new CommandParser().addProduct("50 2.50");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: Invalid format. add <name> <quantity> <price>.", e.getMessage());
        }
    }

    @Test
    public void addProduct_quantityEmpty_exceptionThrown() {
        try {
            new CommandParser().addProduct("MILK 2.50");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: Invalid format. add <name> <quantity> <price>.", e.getMessage());
        }
    }

    @Test
    public void addProduct_priceEmpty_exceptionThrown() {
        try {
            new CommandParser().addProduct("MILK 50");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: Invalid format. add <name> <quantity> <price>.", e.getMessage());
        }
    }

    @Test
    public void addProduct_parseNumberFail_exceptionThrown() {
        try {
            new CommandParser().addProduct("MILK hi 2.50");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The quantity and/or price provided is not a proper number. Please try again."
                    , e.getMessage());
        }

        try {
            new CommandParser().addProduct("MILK 50 hi");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The quantity and/or price provided is not a proper number. Please try again."
                    , e.getMessage());
        }

        try {
            new CommandParser().addProduct("MILK 50.6 2.50");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The quantity and/or price provided is not a proper number. Please try again."
                    , e.getMessage());
        }
    }

    @Test
    public void addProduct_priceMoreThanTwoDecimals_exceptionThrown() {
        try {
            new CommandParser().addProduct("MILK 50 2.503");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The price provided is invalid. Please try again." +
                    " (Input is more than 2 decimal places)", e.getMessage());
        }
    }


    /*
     * deleteProduct()
     */
    @Test
    public void deleteProduct_iDEmpty_exceptionThrown() {
        try {
            new CommandParser().deleteProduct("");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: There is no product ID provided. Please try again.", e.getMessage());
        }
    }

    @Test
    public void deleteProduct_iDWrongFormat_exceptionThrown() {
        try {
            new CommandParser().deleteProduct("star");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The product ID provided is invalid. Please try again.", e.getMessage());
        }
    }

    @Test
    public void deleteProduct_iDNegative_exceptionThrown() {
        try {
            new CommandParser().deleteProduct("-2");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The product ID provided is invalid. Please try again.", e.getMessage());
        }
    }

    @Test
    public void deleteProduct_iDOverLimit_exceptionThrown() {
        try {
            new CommandParser().deleteProduct("11111");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The product ID provided is invalid. Please try again.", e.getMessage());
        }
    }


    /*
     * updateProduct(), getProductIDNumber() & updateBasedOnFlags()
     */
    @Test
    public void updateProduct_infoEmpty_exceptionThrown() {
        try {
            new CommandParser().updateProduct("");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("""
            Invalid flags.
            update <ID number> <flag> <updated value>,
            where <flag> is either /name, /qty, or /price.""", e.getMessage());
        }
    }

    @Test
    public void getProductIDNumber_tagInvalid_exceptionThrown() {
        try {
            new CommandParser().updateProduct("1 cat");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("""
            Invalid flags.
            update <ID number> <flag> <updated value>,
            where <flag> is either /name, /qty, or /price.""", e.getMessage());
        }
    }

    @Test
    public void getProductIDNumber_iDWrongFormat_exceptionThrown() {
        try {
            new CommandParser().updateProduct("star /name FRESH_MILK");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The product ID provided is invalid. Please try again.", e.getMessage());
        }
    }

    @Test
    public void updateProduct_iDNegative_exceptionThrown() {
        try {
            new CommandParser().updateProduct("-2 /qty 45");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The product ID provided is invalid. Please try again.", e.getMessage());
        }
    }

    @Test
    public void updateProduct_iDOverLimit_exceptionThrown() {
        try {
            new CommandParser().updateProduct("11111 /price 3.00");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The product ID provided is invalid. Please try again.", e.getMessage());
        }
    }

    @Test
    public void updateBasedOnFlags_nameEmpty_exceptionThrown() {
        try {
            new CommandParser().updateProduct("1 /name");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("""
            Invalid flags.
            update <ID number> <flag> <updated value>,
            where <flag> is either /name, /qty, or /price.""", e.getMessage());
        }
    }

    @Test
    public void updateBasedOnFlags_quantityEmpty_exceptionThrown() {
        try {
            new CommandParser().updateProduct("1 /qty");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("""
            Invalid flags.
            update <ID number> <flag> <updated value>,
            where <flag> is either /name, /qty, or /price.""", e.getMessage());
        }
    }

    @Test
    public void updateBasedOnFlags_priceEmpty_exceptionThrown() {
        try {
            new CommandParser().updateProduct("1 /price");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("""
            Invalid flags.
            update <ID number> <flag> <updated value>,
            where <flag> is either /name, /qty, or /price.""", e.getMessage());
        }
    }

    @Test
    public void updateBasedOnFlags_parseNumberFail_exceptionThrown() {
        try {
            new CommandParser().updateProduct("1 /qty hi");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The quantity and/or price provided is not a proper number. Please try again."
                    , e.getMessage());
        }

        try {
            new CommandParser().updateProduct("1 /price hi");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The quantity and/or price provided is not a proper number. Please try again."
                    , e.getMessage());
        }

        try {
            new CommandParser().updateProduct("1 /qty 45.6");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The quantity and/or price provided is not a proper number. Please try again."
                    , e.getMessage());
        }
    }

    @Test
    public void updateBasedOnFlags_priceMoreThanTwoDecimals_exceptionThrown() {
        try {
            new CommandParser().updateProduct("1 /price 3.003");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The price provided is invalid. Please try again." +
                    " (Input is more than 2 decimal places)", e.getMessage());
        }
    }


    /*
     * recordSale()
     */
    @Test
    public void recordSale_infoEmpty_exceptionThrown() {
        try {
            new CommandParser().recordSale("");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: Invalid format. sold <ID number> <Quantity sold>.", e.getMessage());
        }
    }

    @Test
    public void recordSale_qtyEmpty_exceptionThrown() {
        try {
            new CommandParser().recordSale("1");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: Invalid format. sold <ID number> <Quantity sold>.", e.getMessage());
        }
    }

    @Test
    public void recordSale_parseNumberFail_exceptionThrown() {
        try {
            new CommandParser().recordSale("1 hi");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The quantity provided must be a whole number. Please try again."
                    , e.getMessage());
        }

        try {
            new CommandParser().recordSale("1 50.6");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The quantity provided must be a whole number. Please try again."
                    , e.getMessage());
        }
    }

    @Test
    public void recordSale_iDWrongFormat_exceptionThrown() {
        try {
            new CommandParser().recordSale("star 50");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The product ID provided is invalid. Please try again.", e.getMessage());
        }
    }

    @Test
    public void recordSale_iDNegative_exceptionThrown() {
        try {
            new CommandParser().recordSale("-2 50");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The product ID provided is invalid. Please try again.", e.getMessage());
        }
    }

    @Test
    public void recordSale_iDOverLimit_exceptionThrown() {
        try {
            new CommandParser().recordSale("11111 50");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The product ID provided is invalid. Please try again.", e.getMessage());
        }
    }

    /*
     * clearSales()
     */
    @Test
    public void clearSales_iDEmpty_exceptionThrown() {
        try {
            new CommandParser().clearSales("");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: There is no product ID provided. Please try again.", e.getMessage());
        }
    }

    @Test
    public void clearSales_iDWrongFormat_exceptionThrown() {
        try {
            new CommandParser().clearSales("star");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The product ID provided is invalid. Please try again.", e.getMessage());
        }
    }

    @Test
    public void clearSales_iDNegative_exceptionThrown() {
        try {
            new CommandParser().clearSales("-2");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The product ID provided is invalid. Please try again.", e.getMessage());
        }
    }

    @Test
    public void clearSales_iDOverLimit_exceptionThrown() {
        try {
            new CommandParser().clearSales("11111");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The product ID provided is invalid. Please try again.", e.getMessage());
        }
    }


    /*
     * computeRevenue()
     */
    @Test
    public void computeRevenue_iDWrongFormat_exceptionThrown() {
        try {
            new CommandParser().computeRevenue("star");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The product ID provided is invalid. Please try again.", e.getMessage());
        }
    }

    @Test
    public void computeRevenue_iDNegative_exceptionThrown() {
        try {
            new CommandParser().computeRevenue("-2");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The product ID provided is invalid. Please try again.", e.getMessage());
        }
    }

    @Test
    public void computeRevenue_iDOverLimit_exceptionThrown() {
        try {
            new CommandParser().computeRevenue("11111");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The product ID provided is invalid. Please try again.", e.getMessage());
        }
    }


    /*
     * searchForProduct()
     */
    @Test
    public void searchForProduct_invalidTag_exceptionThrown() {
        try {
            new CommandParser().searchForProduct("cat");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: Invalid format. search /name <name> OR search /id <number>.", e.getMessage());
        }
    }

    @Test
    public void searchForProduct_nameEmpty_exceptionThrown() {
        try {
            new CommandParser().searchForProduct("/name");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: There is no name provided. Please try again.", e.getMessage());
        }
    }

    @Test
    public void searchForProduct_iDEmpty_exceptionThrown() {
        try {
            new CommandParser().searchForProduct("/id");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: There is no product ID provided. Please try again.", e.getMessage());
        }
    }

    @Test
    public void searchForProduct_iDWrongFormat_exceptionThrown() {
        try {
            new CommandParser().searchForProduct("/id star");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The product ID provided is invalid. Please try again.", e.getMessage());
        }
    }

    @Test
    public void searchForProduct_iDNegative_exceptionThrown() {
        try {
            new CommandParser().searchForProduct("/id -2");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The product ID provided is invalid. Please try again.", e.getMessage());
        }
    }

    @Test
    public void searchForProduct_iDOverLimit_exceptionThrown() {
        try {
            new CommandParser().searchForProduct("/id 11111");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Error: The product ID provided is invalid. Please try again.", e.getMessage());
        }
    }


    /*
     * splitInfo()
     */
    @Test
    public void testSplitInfo() {
        assertArrayEquals(new String[]{"/name", "MILK", "/qty", "50", "/price", "2.50"},
                new CommandParser().splitInfo("/name MILK /qty 50 /price 2.50"));
        assertArrayEquals(new String[]{"/id", "1"},
                new CommandParser().splitInfo("/id 1"));
        assertArrayEquals(new String[]{"/id", "1", "/name", "FRESH_MILK", "/qty", "45", "/price", "3.00"},
                new CommandParser().splitInfo("/id 1 /name FRESH_MILK /qty 45 /price 3.00"));
        assertArrayEquals(new String[]{"/id", "1", "/qty", "5"},
                new CommandParser().splitInfo("/id 1 /qty 5"));
        assertArrayEquals(new String[]{""},
                new CommandParser().splitInfo(""));
        assertArrayEquals(new String[]{"/name", "MILK"},
                new CommandParser().splitInfo("/name MILK"));
    }

    /*
     * parseInt()
     */
    @Test
    public void testParseInt_stringIsNumber_success() throws NumberParsingFailedException {
        assertEquals(50, new CommandParser().parseInt("50"));
        assertEquals(-2, new CommandParser().parseInt("-2"));
    }

    @Test
    public void testParseInt_stringIsWord_exceptionThrown() {
        try {
            assertEquals(0, new CommandParser().parseInt("cat"));
            fail();
        } catch (NumberParsingFailedException e) {
            assertEquals("Conversion to a number has failed.", e.getMessage());
        }
    }

    @Test
    public void testParseInt_stringIsEmpty_exceptionThrown() {
        try {
            assertEquals(0, new CommandParser().parseInt(""));
            fail();
        } catch (NumberParsingFailedException e) {
            assertEquals("Conversion to a number has failed.", e.getMessage());
        }
    }

    @Test
    public void testParseInt_stringIsFloatValue_exceptionThrown() {
        try {
            assertEquals(2, new CommandParser().parseInt("2.50"));
            fail();
        } catch (NumberParsingFailedException e) {
            assertEquals("Conversion to a number has failed.", e.getMessage());
        }
    }

    /*
     * parseDouble()
     */
    @Test
    public void testParseDouble_stringIsNumber_success() throws NumberParsingFailedException {
        assertEquals(2.50, new CommandParser().parseDouble("2.50"));
        assertEquals(-2, new CommandParser().parseDouble("-2"));
    }

    @Test
    public void testParseDouble_stringIsWord_exceptionThrown() {
        try {
            assertEquals(0, new CommandParser().parseDouble("cat"));
            fail();
        } catch (NumberParsingFailedException e) {
            assertEquals("Conversion to a number has failed.", e.getMessage());
        }
    }

    @Test
    public void testParseDouble_stringIsEmpty_exceptionThrown() {
        try {
            assertEquals(0, new CommandParser().parseDouble(""));
            fail();
        } catch (NumberParsingFailedException e) {
            assertEquals("Conversion to a number has failed.", e.getMessage());
        }
    }
}
