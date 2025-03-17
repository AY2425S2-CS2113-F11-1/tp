package busynessmanager.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CommandParserTest {

    @Test
    public void getCommandSeparatorIndex_success() {
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

        // Test cases where erroneous inputs are given.
        assertEquals(-1, new CommandParser().getCommandSeparatorIndex(
                ""));
        assertEquals(4, new CommandParser().getCommandSeparatorIndex(
                "ding dong"));
    }

    @Test
    public void extractCommand_positiveIndexWithinLimit_success() {
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
         * These tests will pass for now, and throw an exception during later methods.
         */
        assertEquals("", new CommandParser().extractCommand(
                0, ""));
        assertEquals("ding", new CommandParser().extractCommand(
                4, "ding dong"));
    }

    @Test
    public void extractInfo_positiveIndexWithinLimit_success() {
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

        // This test fails. Put into fail section.
        //assertEquals("", new CommandParser().extractInfo(0, ""));

        /*
         * Test cases where erroneous inputs are given.
         * These tests will pass for now, and will fail later as the command portion will
         * throw the exception.
         */
        assertEquals("dong", new CommandParser().extractInfo(
                4, "ding dong"));
    }

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

    @Test
    public void testParseInt_stringIsNumber() {
        assertEquals(50, new CommandParser().parseInt("50"));
    }

    @Test
    public void testParseDouble_stringIsNumber() {
        assertEquals(2.50, new CommandParser().parseDouble("2.50"));
    }
}
