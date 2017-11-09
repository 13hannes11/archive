package edu.kit.informatik;

import edu.kit.informatik.exceptions.InvalidCommandException;
import edu.kit.informatik.list.Container;
import edu.kit.informatik.list.MinimaList;
import edu.kit.informatik.list.Pair;

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
    private final MinimaList<Pair<AccountHolder, MinimaList<Account>>> data;

    /**
     * Instantiates a new bank.
     *
     * @param bankCode
     *            the bank code
     */
    public Bank(final int bankCode) {
        this.bankCode = bankCode;
        data = new MinimaList<Pair<AccountHolder, MinimaList<Account>>>();
    }

    /**
     * Adds a user.
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
        data.add(new Pair<AccountHolder, MinimaList<Account>>(accHolder, new MinimaList<Account>()));
    }

    /**
     * Adds an account.
     *
     * @param accountNumber
     *            the account number
     * @param personnelNumber
     *            the personnel number
     * @return true, if successful
     * @throws InvalidCommandException
     *             the invalid command exception
     */
    public boolean addAccount(final int accountNumber, final int personnelNumber) throws InvalidCommandException {
        Container<Pair<AccountHolder, MinimaList<Account>>> container = data.getFirstContainer();
        while (container != null) {
            if (container.getData().getFirst().getPersonnelNumber() == personnelNumber) {
                container.getData().getSecond().add(new Account(bankCode, accountNumber));
                return true;
            }

            container = container.getNext();
        }
        throw new InvalidCommandException(
                "the person with this personnel number(" + personnelNumber + ") is not at this bank.");
    }

    /**
     * Removes an account.
     *
     * @param accountNumber
     *            the account number
     * @return true, if successful
     */
    public boolean removeAccount(final int accountNumber) {
        if (containsAccount(accountNumber)) {
            Container<Pair<AccountHolder, MinimaList<Account>>> container = data.getFirstContainer();
            while (container != null) {
                final Pair<AccountHolder, MinimaList<Account>> pair = container.getData();
                final int index = pair.getSecond().getIndex(new Account(bankCode, accountNumber));

                if (pair.getSecond().remove(index)) {
                    return true;
                }
                container = container.getNext();
            }
        }
        return false;
    }

    /**
     * Deposits money.
     *
     * @param accountNumber
     *            the account number
     * @param amount
     *            the amount
     */
    public void deposit(final int accountNumber, final int amount) {
        final Account acc = getAccount(accountNumber);
        acc.deposit(amount);
    }

    /**
     * Withdraws money.
     *
     * @param accountNumber
     *            the account number
     * @param amount
     *            the amount
     * @return true, if successful
     */
    public boolean withdraw(final int accountNumber, final int amount) {
        final Account acc = getAccount(accountNumber);
        return acc.withdraw(amount);
    }

    /**
     * Internal bank transfer.
     *
     * @param fromAccountNumber
     *            the from account number
     * @param toAccountNumber
     *            the to account number
     * @param amount
     *            the amount
     * @throws InvalidCommandException
     *             the invalid parameter exception
     */
    public void internalBankTransfer(final int fromAccountNumber, final int toAccountNumber, final int amount)
            throws InvalidCommandException {

        final Account from = getAccount(fromAccountNumber);
        final Account to = getAccount(toAccountNumber);
        if (to == null || from == null) {
            throw new InvalidCommandException("either the account (" + fromAccountNumber
                    + ") the money shouled be transfered from or the account (" + toAccountNumber
                    + ") the money should be transefered to does not exist.");
        }
        if (!from.withdraw(amount)) {
            throw new InvalidCommandException("amount could not be withdrawn from account (" + fromAccountNumber + ")");
        }
        to.deposit(amount);
    }

    /**
     * Gets the account count of an account holder.
     *
     * @param personnelNumber
     *            the personnel number
     * @return the account count
     */
    public int getAccountCount(final int personnelNumber) {
        Container<Pair<AccountHolder, MinimaList<Account>>> container = data.getFirstContainer();
        while (container != null) {
            final Pair<AccountHolder, MinimaList<Account>> pair = container.getData();
            if (pair.getFirst().getPersonnelNumber() == personnelNumber) {
                return pair.getSecond().size();
            }
            container = container.getNext();
        }
        return 0;
    }

    /**
     * Checks if account is contained.
     *
     * @param accountNumber
     *            the account number
     * @return true, if contained
     */
    public boolean containsAccount(final int accountNumber) {
        Container<Pair<AccountHolder, MinimaList<Account>>> container = data.getFirstContainer();
        while (container != null) {
            final Pair<AccountHolder, MinimaList<Account>> pair = container.getData();
            if (pair.getSecond().contains(new Account(bankCode, accountNumber))) {
                return true;
            }
            container = container.getNext();
        }
        return false;
    }

    /**
     * Gets the account.
     *
     * @param accountNumber
     *            the account number
     * @return the account
     */
    public Account getAccount(final int accountNumber) {
        Container<Pair<AccountHolder, MinimaList<Account>>> container = data.getFirstContainer();
        while (container != null) {
            final Pair<AccountHolder, MinimaList<Account>> pair = container.getData();
            final int index = pair.getSecond().getIndex(new Account(bankCode, accountNumber));
            if (index >= 0) {
                return pair.getSecond().get(index);
            }
            container = container.getNext();
        }
        return null;
    }

    /**
     * Balance.
     *
     * @param accountNumber
     *            the account number
     * @return the int
     * @throws InvalidCommandException
     *             the invalid parameter exception
     */
    public int balance(final int accountNumber) throws InvalidCommandException {
        final Account acc = getAccount(accountNumber);
        if (acc == null) {
            throw new InvalidCommandException(
                    "the bank (" + this.bankCode + ") does not contain the account (" + accountNumber + ")");
        } else {
            return acc.getBalance();
        }
    }

    /**
     * Gets the bank code.
     *
     * @return the bank code
     */
    public int getBankCode() {
        return bankCode;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        return (obj instanceof Bank || ((Bank) obj).bankCode == this.bankCode);
    }
}
