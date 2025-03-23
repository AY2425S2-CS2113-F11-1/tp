package busynessmanager.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;

import busynessmanager.exceptions.InvalidStringException;
import busynessmanager.exceptions.InvalidCommandException;
import busynessmanager.exceptions.NumberParsingFailedException;


//@@author b1inmeister
public class CommandParserTest {

    /*
     * getCommandSeparatorIndex()
     */
    @Test
    public void getCommandSeparatorIndex_validString_success() throws InvalidStringException {
        assertEquals(3, new CommandParser().getCommandSeparatorIndex(
                "add /name MILK /qty 50 price 2.50"));
        assertEquals(6, new CommandParser().getCommandSeparatorIndex(
                "delete /id 1"));
        assertEquals(6, new CommandParser().getCommandSeparatorIndex(
                "update /id 1 /name FRESH_MILK /qty 45 /price 3.00"));
        assertEquals(4, new CommandParser().getCommandSeparatorIndex(
                "sold /id 1 /qty 5"));
        assertEquals(5, new CommandParser().getCommandSeparatorIndex(
                "clear /id 1"));
        assertEquals(7, new CommandParser().getCommandSeparatorIndex(
                "revenue /id 1"));
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
                3, "add /name MILK /qty 50 price 2.50"));
        assertEquals("delete", new CommandParser().extractCommand(
                6, "delete /id 1"));
        assertEquals("update", new CommandParser().extractCommand(
                6, "update /id 1 /name FRESH_MILK /qty 45 /price 3.00"));
        assertEquals("sold", new CommandParser().extractCommand(
                4, "sold /id 1 /qty 5"));
        assertEquals("clear", new CommandParser().extractCommand(
                5, "clear /id 1"));
        assertEquals("revenue", new CommandParser().extractCommand(
                7, "revenue /id 1"));
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
        assertEquals("/name MILK /qty 50 price 2.50", new CommandParser().extractInfo(
                3, "add /name MILK /qty 50 price 2.50"));
        assertEquals("/id 1", new CommandParser().extractInfo(
                6, "delete /id 1"));
        assertEquals("/id 1 /name FRESH_MILK /qty 45 /price 3.00", new CommandParser().extractInfo(
                6, "update /id 1 /name FRESH_MILK /qty 45 /price 3.00"));
        assertEquals("/id 1 /qty 5", new CommandParser().extractInfo(
                4, "sold /id 1 /qty 5"));
        assertEquals("/id 1", new CommandParser().extractInfo(
                5, "clear /id 1"));
        assertEquals("/id 1", new CommandParser().extractInfo(
                7, "revenue /id 1"));
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
            assertEquals("Command does not exist. Please try again.", e.getMessage());
        }

        try {
            new CommandParser().executeCommand("cat", "");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Command does not exist. Please try again.", e.getMessage());
        }
    }

    @Test
    public void executeCommand_emptyString_exceptionThrown() {
        try {
            new CommandParser().executeCommand("", "");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Command does not exist. Please try again.", e.getMessage());
        }
    }

    /*
     * addProduct()
     */
    @Test
    public void addProduct_wrongWord_exceptionThrown() {
        try {
            new CommandParser().addProduct("name larry qty 50 price 2.50");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Invalid format. /name /qty /price.", e.getMessage());
        }
    }

    @Test
    public void addProduct_priceEmpty_exceptionThrown() {
        try {
            new CommandParser().addProduct("/name larry /qty 50 /price");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Invalid format. /name /qty /price.", e.getMessage());
        }
    }

    @Test
    public void addProduct_parseNumberFail_exceptionThrown() {
        try {
            new CommandParser().addProduct("/name larry /qty hi /price 2.50");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Quantity or price is not a number. Please try again.", e.getMessage());
        }

        try {
            new CommandParser().addProduct("/name larry /qty 15.6 /price 2.50");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Quantity or price is not a number. Please try again.", e.getMessage());
        }

        try {
            new CommandParser().addProduct("/name larry /qty 50 /price hi");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Quantity or price is not a number. Please try again.", e.getMessage());
        }
    }

    /*
     * deleteProduct()
     */
    @Test
    public void deleteProduct_wrongWord_exceptionThrown() {
        try {
            new CommandParser().deleteProduct("id ID_0069");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Invalid format. /id.", e.getMessage());
        }
    }

    @Test
    public void deleteProduct_iDEmpty_exceptionThrown() {
        try {
            new CommandParser().deleteProduct("/id");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Invalid format. /id.", e.getMessage());
        }
    }

    @Test
    public void deleteProduct_iDWrongFormat_exceptionThrown() {
        try {
            new CommandParser().deleteProduct("/id ID_STAR");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("ID is invalid. Please try again.", e.getMessage());
        }
    }

    /*
     * updateProduct()
     */
    @Test
    public void updateProduct_wrongWord_exceptionThrown() {
        try {
            new CommandParser().updateProduct("id ID_0069 name larry qty 50 price 2.50");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Invalid format. /id /name /qty /price.", e.getMessage());
        }
    }

    @Test
    public void updateProduct_priceEmpty_exceptionThrown() {
        try {
            new CommandParser().updateProduct("/id ID_0069 /name larry /qty 50 /price");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Invalid format. /id /name /qty /price.", e.getMessage());
        }
    }

    @Test
    public void updateProduct_parseNumberFail_exceptionThrown() {
        try {
            new CommandParser().updateProduct("/id ID_0069 /name larry /qty hi /price 2.50");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Quantity or price is not a number. Please try again.", e.getMessage());
        }

        try {
            new CommandParser().updateProduct("/id ID_0069 /name larry /qty 15.6 /price 2.50");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Quantity or price is not a number. Please try again.", e.getMessage());
        }

        try {
            new CommandParser().updateProduct("/id ID_0069 /name larry /qty 50 /price hi");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Quantity or price is not a number. Please try again.", e.getMessage());
        }
    }

    @Test
    public void updateProduct_iDWrongFormat_exceptionThrown() {
        try {
            new CommandParser().updateProduct("/id ID_STAR /name larry /qty 50 /price 2.50");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("ID is invalid. Please try again.", e.getMessage());
        }
    }

    /*
     * recordSale()
     */
    @Test
    public void recordSale_wrongWord_exceptionThrown() {
        try {
            new CommandParser().recordSale("id ID_0069 qty 50");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Invalid format. /id /qty.", e.getMessage());
        }
    }

    @Test
    public void recordSale_qtyEmpty_exceptionThrown() {
        try {
            new CommandParser().recordSale("/id ID_0069 /qty");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Invalid format. /id /qty.", e.getMessage());
        }
    }

    @Test
    public void recordSale_parseNumberFail_exceptionThrown() {
        try {
            new CommandParser().recordSale("/id ID_0069 /qty hi");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Quantity is not a number. Please try again.", e.getMessage());
        }

        try {
            new CommandParser().recordSale("/id ID_0069 /qty 15.6");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Quantity is not a number. Please try again.", e.getMessage());
        }
    }

    @Test
    public void recordSale_iDWrongFormat_exceptionThrown() {
        try {
            new CommandParser().recordSale("/id ID_STAR /qty 50");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("ID is invalid. Please try again.", e.getMessage());
        }
    }

    /*
     * clearSales()
     */
    @Test
    public void clearSales_wrongWord_exceptionThrown() {
        try {
            new CommandParser().clearSales("id ID_0069");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Invalid format. /id.", e.getMessage());
        }
    }

    @Test
    public void clearSales_iDEmpty_exceptionThrown() {
        try {
            new CommandParser().clearSales("/id");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Invalid format. /id.", e.getMessage());
        }
    }

    @Test
    public void clearSales_iDWrongFormat_exceptionThrown() {
        try {
            new CommandParser().clearSales("/id ID_STAR");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("ID is invalid. Please try again.", e.getMessage());
        }
    }


    /*
     * computeRevenue()
     */
    @Test
    public void computeRevenue_wrongWord_exceptionThrown() {
        try {
            new CommandParser().computeRevenue("id ID_0069");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Invalid format. /id or keep empty for total.", e.getMessage());
        }
    }

    @Test
    public void computeRevenue_iDEmpty_exceptionThrown() {
        try {
            new CommandParser().computeRevenue("/id");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Invalid format. /id or keep empty for total.", e.getMessage());
        }
    }

    @Test
    public void computeRevenue_iDWrongFormat_exceptionThrown() {
        try {
            new CommandParser().computeRevenue("/id ID_STAR");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("ID is invalid. Please try again.", e.getMessage());
        }
    }



    /*
     * searchForProduct()
     */
    @Test
    public void searchForProduct_wrongWord_exceptionThrown() {
        try {
            new CommandParser().searchForProduct("cat");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Invalid format. /name OR /id.", e.getMessage());
        }
    }

    @Test
    public void searchForProduct_nameEmpty_exceptionThrown() {
        try {
            new CommandParser().searchForProduct("/name");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Invalid format. /name OR /id.", e.getMessage());
        }
    }

    @Test
    public void searchForProduct_iDEmpty_exceptionThrown() {
        try {
            new CommandParser().searchForProduct("/id");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Invalid format. /name OR /id.", e.getMessage());
        }
    }

    @Test
    public void searchForProduct_iDWrongFormat_exceptionThrown() {
        try {
            new CommandParser().searchForProduct("/id ID_STAR");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("ID is invalid. Please try again.", e.getMessage());
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
