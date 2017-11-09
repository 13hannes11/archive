package edu.kit.informatik;

/**
 * The Class Account.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class Bank {

    /** The bank code. */
    private int       bankCode;

    /** The accounts. */
    private Account[] accounts;

    /**
     * Instantiates a new bank.
     *
     * @param bnkCode
     *            the bankCode
     */
    public Bank(Integer bnkCode) {
        this.bankCode = bnkCode;
        accounts = new Account[4];
    }

    /**
     * Creates an account.
     *
     * @param accountNumber
     *            the account number
     * @return position of the created account inside the accounts
     */
    public int createAccount(int accountNumber) {
        if (this.size() == this.length()) {
            // If account is half way filled: double the amount of elements
            // accounts can hold
            Account[] tmpArr = accounts;
            accounts = new Account[tmpArr.length * 2];
            System.arraycopy(tmpArr, 0, accounts, 0, tmpArr.length);
        }
        // Return index of newly added element
        int index = this.size();
        accounts[index] = new Account(this.bankCode, accountNumber);
        return index;
    }

    /**
     * Removes the account.
     *
     * @param accountNumber
     *            the account number for the account that should be removed
     * @return true, if successful
     */
    public boolean removeAccount(int accountNumber) {
        if (!this.containsAccount(accountNumber)) { // if account is not in that
                                                    // bank it cannot be removed
            return false;
        } else {
            /*
             * Loop through each account until account is found that should be
             * removed. After that close the 'gap' -> change index of all
             * accounts (after the removed one) by -1
             */
            for (int i = 0; i < accounts.length; i++) {
                if (accounts[i] == null) {
                    if (accounts[i + 1] == null) { // break if end of existing
                                                   // accounts is reached
                        break;
                    } else if (i + 1 < accounts.length && accounts[i + 1] != null) { // fill
                                                                                     // in
                                                                                     // the
                                                                                     // gap
                        accounts[i] = accounts[i + 1];
                        accounts[i + 1] = null;
                    }
                } else if (accounts[i].getAccountNumber() == accountNumber) { // remove
                                                                              // account
                    accounts[i] = accounts[i + 1];
                    accounts[i + 1] = null;
                }
            }
            // If the number of accounts gets smaller than 1/4 of the arraysize
            // it will be halfed in size
            if (this.size() * 4 < this.length() && this.length() > 4) { // Make
                                                                        // array
                                                                        // smaller
                                                                        // if
                                                                        // necessary
                Account[] tmpAcc = this.accounts;
                int length = this.size();
                this.accounts = new Account[this.length() / 2];

                System.arraycopy(tmpAcc, 0, this.accounts, 0, length);
            }
            return true;
        }
    }

    /**
     * Contains account.
     *
     * @param accountNumber
     *            the account number
     * @return true, if successful
     */
    public boolean containsAccount(int accountNumber) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getAccountNumber() == accountNumber)
                return true;
        }
        return false;
    }

    /**
     * Internal bank transfer.
     *
     * @param fromAccountNumber
     *            the account number of the account money should be withdrawn
     *            from
     * @param toAccountNumber
     *            the account number of the account the money should be
     *            transfered to
     * @param amount
     *            the amount
     * @return true, if successful
     */
    public boolean internalBankTransfer(int fromAccountNumber, int toAccountNumber, int amount) {
        if (getAccountID(fromAccountNumber) < 0 && getAccountID(toAccountNumber) < 0)
            return false;

        Account fromAcc = this.getAccount(this.getAccountID(fromAccountNumber));
        Account toAcc = this.getAccount(this.getAccountID(toAccountNumber));

        if (fromAcc.withdraw(amount))
            toAcc.deposit(amount);

        return false;
    }

    /**
     * Length.
     *
     * @return the the length of the internal array used to store all accounts
     */
    public int length() {
        return accounts.length;
    }

    /**
     * Number of accounts.
     *
     * @return the number of accounts inside the bank
     */
    public int size() {
        if (accounts[0] == null)
            return 0;
        for (int i = 1; i < accounts.length; i++) {
            if (accounts[i] == null)
                return (i);
        }

        return accounts.length; // This should never be called
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
     * Gets an account.
     *
     * @param index
     *            the index of the account that should be returned
     * @return the account
     */
    public Account getAccount(int index) {
        if (size() <= index || index < 0)
            return null;
        else {
            return accounts[index];
        }
    }

    /**
     * Gets the account id.
     *
     * @param accountNumber
     *            the account number
     * @return the account id
     */
    private int getAccountID(int accountNumber) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getAccountNumber() == accountNumber)
                return i;
        }
        return -1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String str = "BankCode: " + bankCode + "\n";
        str += "size: " + size() + "\n";
        str += "length: " + length() + "\n";

        for (Account account : accounts) {
            if (account != null)
                str += "\t" + account + "\n\n";
        }
        return str.trim();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Bank && ((Bank) obj).getBankCode() == this.bankCode)
            return true;
        return false;
    }
}
