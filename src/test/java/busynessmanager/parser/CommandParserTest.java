package busynessmanager.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CommandParserTest {

    @Test
    public void getCommandSeparatorIndex_stringContainsSpaces_success() {
        assertEquals(5, new CommandParser().getCommandSeparatorIndex("Hello World"));
        assertEquals(0, new CommandParser().getCommandSeparatorIndex(" LeadingSpace"));
        assertEquals(13, new CommandParser().getCommandSeparatorIndex("TrailingSpace "));
        assertEquals(8, new CommandParser().getCommandSeparatorIndex("Multiple Spaces Here"));
        assertEquals(0, new CommandParser().getCommandSeparatorIndex(" "));
        assertEquals(0, new CommandParser().getCommandSeparatorIndex("  "));
        assertEquals(-1, new CommandParser().getCommandSeparatorIndex("NoSpaces"));
        assertEquals(-1, new CommandParser().getCommandSeparatorIndex(""));
    }

    @Test
    public void extractCommand_positiveIndexWithinLimit_success() {
        assertEquals("Hello", new CommandParser().extractCommand(5, "HelloWorld"));
        assertEquals("", new CommandParser().extractCommand(0, "Hello"));
        assertEquals("Hello", new CommandParser().extractCommand(5, "Hello"));
        assertEquals("  ", new CommandParser().extractCommand(2, "  LeadingSpaces"));
        assertEquals("Multiple",
                new CommandParser().extractCommand(8, "Multiple Words"));

        // This should throw exception during later implementations.
        assertEquals("", new CommandParser().extractCommand(0, ""));
    }

    @Test
    public void extractInfo_positiveIndexWithinLimit_success() {
        assertEquals("World", new CommandParser().extractInfo(5, "HelloWorld"));
        assertEquals("Hello", new CommandParser().extractInfo(0, "Hello"));
        assertEquals("o", new CommandParser().extractInfo(4, "Hello"));
        assertEquals("", new CommandParser().extractInfo(5, "Hello"));
        assertEquals("LeadingSpaces",
                new CommandParser().extractInfo(2, "  LeadingSpaces"));

        // This should throw exception during later implementations.
        assertEquals("", new CommandParser().extractInfo(0, ""));
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
