package busynessmanager.credentials;

import busynessmanager.BusynessManager;

/**
 * Represents the credentials of a business in the Busyness Manager application.
 * This class stores the business ID, name, password, and type.
 */
//@@author amirhusaini06
public class Credentials {
    private final String businessID;
    private final String businessName;
    private final String businessPassword;
    private final BusynessManager.BusinessType businessType;

    /**
     * Constructs a Credentials object with the given business details.
     *
     * @param businessID      The unique identifier of the business.
     * @param businessName    The name of the business.
     * @param businessPassword The password for authentication.
     * @param businessType    The type of business (F&B or Retail).
     * @throws IllegalArgumentException if any of the parameters are null or empty.
     */
    public Credentials(String businessID, String businessName, String businessPassword,
                       BusynessManager.BusinessType businessType) {
        assert businessID != null && !businessID.isEmpty() : "Business ID cannot be null or empty";
        assert businessName != null && !businessName.isEmpty() : "Business name cannot be null or empty";
        assert businessPassword != null && !businessPassword.isEmpty() : "Business password cannot be null or empty";
        assert businessType != null : "Business type cannot be null";

        this.businessID = businessID;
        this.businessName = businessName;
        this.businessPassword = businessPassword;
        this.businessType = businessType;
    }

    /**
     * Gets the business ID.
     *
     * @return The business ID.
     */
    public String getBusinessID() {
        return businessID;
    }

    /**
     * Gets the business name.
     *
     * @return The business name.
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * Gets the business password.
     *
     * @return The business password.
     */
    public String getBusinessPassword() {
        return businessPassword;
    }

    /**
     * Gets the business type.
     *
     * @return The business type (F&B or Retail).
     */
    public BusynessManager.BusinessType getBusinessType() {
        return businessType;
    }
}
