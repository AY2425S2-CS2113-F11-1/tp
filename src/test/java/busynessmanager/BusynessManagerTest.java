//@@author amirhusaini06
package busynessmanager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Scanner;


class BusynessManagerTest {
    private BusynessManager busynessManager;

    @BeforeEach
    void setUp() {
        busynessManager = new BusynessManager();
    }

    /**
     * Tests the first-time setup process to ensure credentials are stored correctly.
     */
    @Test
    void testFirstTimeSetup() {
        Scanner input = new Scanner("1234\ntestName\ntestPass\nFNB\n");
        busynessManager.firstTimeSetup(input);

        assertTrue(busynessManager.validPassword("1234", "testPass"),
                "Password should be set correctly");
    }

    /**
     * Tests login validation by checking invalid password scenarios.
     */
    @Test
    void testValidPasswordWithInvalidCredentials() {
        Scanner setupInput = new Scanner("1234\ntestName\ntestPass\nFNB\n");
        busynessManager.firstTimeSetup(setupInput);

        assertFalse(busynessManager.validPassword("1234", "wrongPass"),
                "Password validation should fail for incorrect password");
    }

    /**
     * Tests the business type extraction method to ensure it returns a valid type.
     */
    @Test
    void testBusinessTypeSelection() {
        Scanner setupInput = new Scanner("1234\ntestName\ntestPass\nFNB\n");
        busynessManager.firstTimeSetup(setupInput);
        Scanner businessTypeInput = new Scanner("FNB\n");

        assertNotNull(busynessManager.extractBusinessType(businessTypeInput),
                "Business type should not be null");
    }
}
