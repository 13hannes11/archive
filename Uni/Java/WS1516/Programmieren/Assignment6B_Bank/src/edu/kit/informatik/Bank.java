package edu.kit.informatik;

import java.util.ArrayList;

import edu.kit.informatik.exceptions.AccountDoesNotExistException;
import edu.kit.informatik.exceptions.AccountHolderDoesNotExistException;

/**
 * The Class Bank.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class Bank {

    /** The bank code. */
    private final int bankCode;

    /** The data. */
    private final ArrayList<Pair<AccountHolder, ArrayList<Account>>> data;

    /**
     * Instantiates a new bank.
     *
     * @param bankCode
     *            the bank code
     */
    public Bank(final int bankCode) {
        this.bankCode = bankCode;
        this.data = new ArrayList<Pair<AccountHolder, ArrayList<Account>>>();
    }

    /**
     * Adds the user.
     *
     * @param firstName
     *            the first name
     * @param lastName
     *            the last name
     * @param personnelNumber
     *            the personnel number
     */
    public void addUser(final String firstName, final String lastName, final int personnelNumber) {
        final AccountHolder accHolder = new AccountHolder(firstName, lastName, personnelNumber);
        data.add(new Pair<AccountHolder, ArrayList<Account>>(accHolder, new ArrayList<Account>()));
    }

    /**
     * Adds the account.
     *
     * @param accountNumber
     *            the account number
     * @param personnelNumber
     *            the personnel number
     * @return true, if successful
     * @throws AccountHolderDoesNotExistException
     *             the account holder does not exist exception
     */
    public boolean addAccount(final int accountNumber, final int personnelNumber)
            throws AccountHolderDoesNotExistException {
        for (final Pair<AccountHolder, ArrayList<Account>> pair : data) {
            if (pair.getFirst().getPersonnelNumber() == personnelNumber) {
                pair.getSecond().add(new Account(accountNumber, bankCode));
                return true;
            }
        }
        throw new AccountHolderDoesNotExistException();
    }

    /**
     * Removes the account.
     *
     * @param accountNumber
     *            the account
     * @throws AccountDoesNotExistException
     *             the account does not exist exception
     */
    public void removeAccount(final int accountNumber) throws AccountDoesNotExistException {
        for (final Pair<AccountHolder, ArrayList<Account>> pair : data) {
            if (pair.getSecond().remove(new Account(accountNumber, bankCode))) {
                return;
            }
        }
        throw new AccountDoesNotExistException();
    }

    /**
     * Withdraws money from an account.
     *
     * @param accountNumber
     *            the account number
     * @param amount
     *            the amount
     * @return true, if successful
     * @throws AccountDoesNotExistException
     *             the account does not exist exception
     */
    public boolean withdraw(final int accountNumber, final int amount) throws AccountDoesNotExistException {
        final Account acc = getAccount(accountNumber);
        if (acc == null) {
            return false;
        }
        return acc.withdraw(amount);
    }

    /**
     * Adds money to an account.
     *
     * @param accountNumber
     *            the account number
     * @param amount
     *            the amount
     * @throws AccountDoesNotExistException
     *             the account does not exist exception
     */
    public void deposit(final int accountNumber, final int amount) throws AccountDoesNotExistException {
        final Account acc = getAccount(accountNumber);
        if (acc == null) {
            throw new AccountDoesNotExistException();
        }
        acc.deposit(amount);
    }

    /**
     * Transfers money from one account to another.
     *
     * @param fromAccountNumber
     *            the from account number
     * @param toAccountnumber
     *            the to accountnumber
     * @param amount
     *            the amount
     * @return true, if successful
     * @throws AccountDoesNotExistException
     *             the account does not exist exception
     */
    public boolean transfer(final int fromAccountNumber, final int toAccountnumber, final int amount)
            throws AccountDoesNotExistException {
        final Account fromAcc = getAccount(fromAccountNumber);
        final Account toAccount = getAccount(toAccountnumber);
        if (!fromAcc.withdraw(amount)) {
            return false;
        }

        toAccount.deposit(amount);
        return true;

    }

    /**
     * Gets the number of accounts. Returns 0 if personnelNumber is not known in
     * this bank.
     *
     * @param personnelNumber
     *            the personnel number
     * @return the number of accounts
     */
    public int getNumberOfAccounts(final int personnelNumber) {
        for (final Pair<AccountHolder, ArrayList<Account>> pair : data) {
            if (pair.getFirst().getPersonnelNumber() == personnelNumber) {
                return pair.getSecond().size();
            }
        }
        return 0;
    }

    /**
     * Checks if account is contained.
     *
     * @param account
     *            the account
     * @return true, if successful
     */
    public boolean containsAccount(final Account account) {
        try {
            final Account comp = getAccount(account.getAccountNumber());
            return account.equals(comp);
        } catch (final AccountDoesNotExistException e) {
            return false;
        }
    }

    /**
     * Returns the balance of the account with the corresponding bank code. If
     * there is no account with that bankCode -1 is returned.
     *
     * @param accountNumber
     *            the account number
     * @return the balance, if unsuccessful -1
     * @throws AccountDoesNotExistException
     *             the account does not exist exception
     */
    public int balance(final int accountNumber) throws AccountDoesNotExistException {
        final Account acc = getAccount(accountNumber);
        return acc.getBalance();
    }

    private Account getAccount(final int accountNumber) throws AccountDoesNotExistException {
        final Account comp = new Account(accountNumber, bankCode);
        for (final Pair<AccountHolder, ArrayList<Account>> pair : data) {
            if (pair.getSecond().contains(comp)) {
                return pair.getSecond().get(pair.getSecond().indexOf(comp));
            }
        }
        throw new AccountDoesNotExistException();
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
        return (obj.getClass() == Bank.class && ((Bank) obj).getBankCode() == this.bankCode);
    }
}
