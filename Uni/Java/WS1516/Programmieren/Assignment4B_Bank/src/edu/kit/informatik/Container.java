package edu.kit.informatik;

/**
 * The Class Container.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class Container {

    /** The account. */
    private Account account;

    /** The next element in the list. */
    private Container next;

    /**
     * Instantiates a new container.
     *
     * @param account
     *            the account
     */
    public Container(Account account) {
        this.account = account;
        this.next = null;
    }

    /**
     * Sets the next element.
     *
     * @param next
     *            the new next element
     */
    public void setNext(Container next) {
        this.next = next;
    }

    /**
     * Gets the next element.
     *
     * @return the next element
     */
    public Container getNext() {
        return next;
    }

    /**
     * Gets the account.
     *
     * @return the account
     */
    public Account getAccount() {
        return account;
    }
}
