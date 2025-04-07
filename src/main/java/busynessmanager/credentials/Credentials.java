//@@author amirhusaini06
package busynessmanager.credentials;

import busynessmanager.BusynessManager;

import static busynessmanager.constants.Constants.*;


/**
 * Represents the credentials of a business in the Busyness Manager application.
 * This class stores the business ID, name, password, and type.
 */
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
        if (businessID == null || businessID.isEmpty()) {
            throw new IllegalArgumentException(BM_ID_ASSERTION_FAIL_MESSAGE);
        }

        if (businessName == null || businessName.isEmpty()) {
            throw new IllegalArgumentException(BM_NAME_ASSERTION_FAIL_MESSAGE);
        }

        if (businessPassword == null || businessPassword.isEmpty()) {
            throw new IllegalArgumentException(BM_PASSWORD_ASSERTION_FAIL_MESSAGE);
        }

        if (businessType == null) {
            throw new IllegalArgumentException(BM_BUSINESSTYPE_ASSERTION_FAIL_MESSAGE);
        }

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
