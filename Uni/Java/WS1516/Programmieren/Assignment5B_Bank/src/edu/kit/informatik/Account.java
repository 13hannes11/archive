package edu.kit.informatik;
/**
 * The Class Account.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class Account {

    /** The account number. */
    private final int accountNumber;

    /** The bank code. */
    private final int bankCode;

    /** The balance. */
    private int balance;

    /**
     * Instantiates a new account (balance is initialized with 0).
     *
     * @param bankCode
     *            the bank code
     * @param accountNumber
     *            the account number
     */
    public Account(final int bankCode, final int accountNumber) {
        if (bankCode < 0 || accountNumber < 0) {
            throw new IllegalArgumentException("Account number and bankCode have to be positive integers.");
        }
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        balance = 0;
    }

    /**
     * Tries to withdraw money from the account (Fails if this would make
     * balance negative).
     *
     * @param amount
     *            the amount that should be withdrawn
     * @return true, if successful
     */
    public boolean withdraw(final int amount) {
        if (balance - amount < 0 || amount < 0) {
            return false;
        }

        balance -= amount;
        return true;
    }

    /**
     * Increases the balance of the account by 'amount'.
     *
     * @param amount
     *            the amount
     * @throws OverflowException
     */
    public void deposit(final int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount must be positive");
        }
        balance += amount;
    }

    /**
     * Gets the account number.
     *
     * @return the account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Gets the bank code.
     *
     * @return the bank code
     */
    public int getBankCode() {
        return bankCode;
    }

    /**
     * Gets the balance.
     *
     * @return the balance
     */
    public int getBalance() {
        return balance;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        return (obj instanceof Account && ((Account) obj).accountNumber == this.accountNumber);
    }

    @Override
    public String toString() {
        return bankCode + "," + accountNumber + "," + balance;
    }
}
