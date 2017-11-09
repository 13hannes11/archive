package edu.kit.informatik;

/**
 * The Class Bank.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class Bank {

    private MinimaList accounts;
    private int bankCode;

    /**
     * Instantiates a new bank.
     *
     * @param bankCode
     *            the bank code
     */
    public Bank(int bankCode) {
        accounts = new MinimaList();
        this.bankCode = bankCode;
    }

    /**
     * Creates an account.
     *
     * @param accountNumber
     *            the account number
     * @return the index of the account
     */
    public int createAccount(int accountNumber) {
        Account acc = new Account(bankCode, accountNumber);
        if (accounts.contains(acc)) {
            throw new IllegalArgumentException("Account already exists.");
        } else {
            int index = 0;
            for (int i = length() - 1; i >= 0; i--) {
                // As soon as acc is bigger than current element
                if (acc.compareTo(accounts.get(i)) > 0) {
                    index = i + 1;
                    break;
                }
            }
            accounts.add(acc, index);
            return index;
        }
    }

    /**
     * Removes the account at the index.
     *
     * @param accountNumber
     *            the account number
     * @return true, if successful
     */
    public boolean removeAccount(int accountNumber) {
        int index = accounts.getIndex(new Account(bankCode, accountNumber));
        if (index < 0)
            return false;
        return accounts.remove(index);
    }

    /**
     * Checks if account exists in this bank.
     *
     * @param accountNumber
     *            the account number
     * @return true, if the account exists
     */
    public boolean containsAccount(int accountNumber) {
        if (accounts.contains(new Account(this.bankCode, accountNumber))) {
            return true;
        }
        return false;
    }

    /**
     * Transfers money from one account to another.
     *
     * @param fromAccountNumber
     *            the account number of the account money is withdrawn from
     * @param toAccountNumber
     *            the account number of the account money is deposited to
     * @param amount
     *            the amount
     * @return true, if successful
     */
    public boolean transfer(int fromAccountNumber, int toAccountNumber, int amount) {
        int fromIndex = accounts.getIndex(new Account(bankCode, fromAccountNumber));
        int toIndex = accounts.getIndex(new Account(bankCode, toAccountNumber));
        if (fromIndex < 0 || toIndex < 0)
            return false;

        if (accounts.get(fromIndex).withdraw(amount)) {
            accounts.get(toIndex).deposit(amount);
            return true;
        }
        return false;
    }

    /**
     * Length.
     *
     * @return the int
     */
    public int length() {
        return accounts.size();
    }

    /**
     * Gets the account at the index.
     *
     * @param index
     *            the index
     * @return the account
     */
    public Account getAccount(int index) {
        return accounts.get(index);
    }

    @Override
    public String toString() {
        return bankCode + "\n" + accounts;
    }

    /**
     * Gets the bank code.
     *
     * @return the bank code
     */
    public int getBankCode() {
        return bankCode;
    }
}
