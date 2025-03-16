package busynessmanager.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandParserTest {

    /*
     * For now, since parseCommand() executes the 3 commands below it, we can just assume by wishful thinking if
     * testing the 3 commands is satisfactory, parseCommand() will also work.
     */

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
}
