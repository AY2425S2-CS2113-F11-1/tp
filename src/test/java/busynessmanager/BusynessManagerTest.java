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

    @Test
    void testFirstTimeSetup() {
        Scanner input = new Scanner("testName\ntestPass\nFNB\n");
        busynessManager.firstTimeSetup(input, "testID");

        assertTrue(busynessManager.validPassword("testID", "testPass"),
                "Password should be set correctly");
    }

    @Test
    void testLoginWithInvalidCredentials() {
        Scanner setupInput = new Scanner("testName\ntestPass\nFNB\n");
        busynessManager.firstTimeSetup(setupInput, "testID");

        Scanner loginInput = new Scanner("wrongPass\n");
        busynessManager.login(loginInput, "testID");

        assertFalse(busynessManager.validPassword("testID", "wrongPass"),
                "Login should fail with incorrect password");
    }

    @Test
    void testBusinessTypeSelection() {
        Scanner setupInput = new Scanner("testName\ntestPass\nFNB\n");
        busynessManager.firstTimeSetup(setupInput, "testID");

        assertNotNull(busynessManager.extractBusinessType(new Scanner("FNB\n")),
                "Business type should not be null");
    }
}
