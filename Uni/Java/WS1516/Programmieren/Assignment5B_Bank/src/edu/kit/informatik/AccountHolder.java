package edu.kit.informatik;
/**
 * The Class AccountHolder.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class AccountHolder {

    /** The first name. */
    private final String firstName;

    /** The last name. */
    private final String lastName;

    /** The personnel number. */
    private final int personnelNumber;

    /**
     * Instantiates a new account holder.
     *
     * @param firstName
     *            the first name
     * @param lastName
     *            the last name
     * @param personnelNumber
     *            the personnel number
     */
    public AccountHolder(final String firstName, final String lastName, final int personnelNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personnelNumber = personnelNumber;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the personnel number.
     *
     * @return the personnel number
     */
    public int getPersonnelNumber() {
        return personnelNumber;
    }

}
