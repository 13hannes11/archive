package edu.kit.informatik;

/**
 * The Class Account.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class Account {

    /** The account number. */
    private int accountNumber;

    /** The bank code. */
    private int bankCode;

    /** The balance. */
    private int balance;

    /**
     * Instantiates a new account.
     *
     * @param bnkCode
     *            the bank code
     * @param accNumber
     *            the account number
     */
    public Account(int bnkCode, int accNumber) {
        this.bankCode = bnkCode;
        this.accountNumber = accNumber;
        this.balance = 0;
    }

    /**
     * Withdraw money.
     *
     * @param amount
     *            the amount
     * @return true, if successful
     */
    public boolean withdraw(int amount) {
        if ((this.balance - amount) < 0)
            return false;
        else {
            this.balance -= amount;
            return true;
        }
    }

    /**
     * Deposit money.
     *
     * @param amount
     *            the amount
     */
    public void deposit(int amount) {
        this.balance += amount;
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
        if (obj instanceof Account) {
            return (this.accountNumber == ((Account) obj).getAccountNumber());
        } else
            return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String str = "\t" + "accountNumber: " + accountNumber + "\n";
        str += "\t\t" + "bankCode" + bankCode + "\n";
        str += "\t\t" + "balance: " + balance;
        return str;
    }
}
