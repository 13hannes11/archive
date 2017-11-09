package edu.kit.informatik;

/**
 * The Class Account.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class Account implements Comparable<Account> {

    /** The account number. */
    private int accountNumber;

    /** The bank code. */
    private int bankCode;

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
    public Account(int bankCode, int accountNumber) {
        if (bankCode < 0 || accountNumber < 0) {
            throw new IllegalArgumentException();
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
    public boolean withdraw(int amount) {
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
     */
    public void deposit(int amount) {
        if (amount < 0)
            throw new IllegalArgumentException("amount must be positive");
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
    public boolean equals(Object obj) {
        if (obj instanceof Account && ((Account) obj).accountNumber == this.accountNumber)
            return true;
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Account o) {
        if (o.getAccountNumber() > this.getAccountNumber())
            return -1;
        else if (o.getAccountNumber() < this.getAccountNumber())
            return 1;

        return 0;
    }

    @Override
    public String toString() {
        return bankCode + "," + accountNumber + "," + balance;
    }
}
