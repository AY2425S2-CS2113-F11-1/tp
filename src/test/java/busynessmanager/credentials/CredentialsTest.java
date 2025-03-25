package busynessmanager.credentials;

import busynessmanager.BusynessManager;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link Credentials} class.
 * Ensures correct object creation and exception handling.
 */
//@@author amirhusaini06
class CredentialsTest {

    /**
     * Tests successful creation of a valid Credentials object.
     */
    @Test
    void testValidCredentialsCreation() {
        Credentials credentials = new Credentials("12345", "My Business",
                "securePass", BusynessManager.BusinessType.FNB);

        assertEquals("12345", credentials.getBusinessID());
        assertEquals("My Business", credentials.getBusinessName());
        assertEquals("securePass", credentials.getBusinessPassword());
        assertEquals(BusynessManager.BusinessType.FNB, credentials.getBusinessType());
    }

    /**
     * Tests that creating a Credentials object with a null business ID throws an exception.
     */
    @Test
    void testNullBusinessIDThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Credentials(null, "My Business",
                        "securePass", BusynessManager.BusinessType.RETAIL));

        assertEquals("Business ID cannot be null or empty", exception.getMessage());
    }

    /**
     * Tests that creating a Credentials object with an empty business ID throws an exception.
     */
    @Test
    void testEmptyBusinessIDThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Credentials("", "My Business",
                        "securePass", BusynessManager.BusinessType.RETAIL));

        assertEquals("Business ID cannot be null or empty", exception.getMessage());
    }

    /**
     * Tests that creating a Credentials object with a null business name throws an exception.
     */
    @Test
    void testNullBusinessNameThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Credentials("12345", null,
                        "securePass", BusynessManager.BusinessType.RETAIL));

        assertEquals("Business name cannot be null or empty", exception.getMessage());
    }

    /**
     * Tests that creating a Credentials object with an empty business name throws an exception.
     */
    @Test
    void testEmptyBusinessNameThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Credentials("12345", "",
                        "securePass", BusynessManager.BusinessType.RETAIL));

        assertEquals("Business name cannot be null or empty", exception.getMessage());
    }

    /**
     * Tests that creating a Credentials object with a null password throws an exception.
     */
    @Test
    void testNullPasswordThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Credentials("12345", "My Business",
                        null, BusynessManager.BusinessType.RETAIL));

        assertEquals("Business password cannot be null or empty", exception.getMessage());
    }

    /**
     * Tests that creating a Credentials object with an empty password throws an exception.
     */
    @Test
    void testEmptyPasswordThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Credentials("12345", "My Business",
                        "", BusynessManager.BusinessType.RETAIL));

        assertEquals("Business password cannot be null or empty", exception.getMessage());
    }

    /**
     * Tests that creating a Credentials object with a null business type throws an exception.
     */
    @Test
    void testNullBusinessTypeThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Credentials("12345", "My Business",
                        "securePass", null));

        assertEquals("Business type cannot be null", exception.getMessage());
    }
}

