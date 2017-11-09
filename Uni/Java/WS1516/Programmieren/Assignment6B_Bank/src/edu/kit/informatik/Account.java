/*
 * 
 */
package edu.kit.informatik;

/**
 * The Class Account.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class Account {
    private final int accountNumber;
    private final int bankCode;
    private int balance;

    /**
     * Instantiates a new account.
     *
     * @param accountNumber
     *            the account number
     * @param bankCode
     *            the bank code
     */
    public Account(final int accountNumber, final int bankCode) {
        if (accountNumber < 0 || bankCode < 0) {
            throw new IllegalArgumentException("Numbers must be > 0!");
        }
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
    }

    /**
     * Adds money to the account
     *
     * @param amount
     *            the amount
     */
    public void deposit(final int amount) {
        if (amount <= 0) {
            return;
        }

        balance += amount;
    }

    /**
     * Withdraws money from the account
     *
     * @param amount
     *            the amount
     * @return true, if successful
     */
    public boolean withdraw(final int amount) {
        if (amount < 0 || balance - amount < 0) {
            return false;
        } else {
            balance -= amount;
            return true;
        }
    }

    /**
     * Gets the balance.
     *
     * @return the balance
     */
    public int getBalance() {
        return balance;
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

    @Override
    public boolean equals(final Object obj) {
        return (obj.getClass() == Account.class && ((Account) obj).accountNumber == this.accountNumber
                && ((Account) obj).bankCode == this.bankCode);
    }
}
