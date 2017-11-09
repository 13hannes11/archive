package edu.kit.informatik;

import java.util.ArrayList;

import edu.kit.informatik.exceptions.AccountDoesNotExistException;
import edu.kit.informatik.exceptions.AccountHolderDoesNotExistException;
import edu.kit.informatik.exceptions.BankDoesNotExistException;

/**
 * The Class BankRegistry.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class BankRegistry {

    /** The banks. */
    private final ArrayList<Bank> banks;

    /**
     * Instantiates a new bank registry.
     */
    public BankRegistry() {
        banks = new ArrayList<Bank>();
    }

    /**
     * Adds the bank.
     *
     * @param bankCode
     *            the bank code
     */
    public void addBank(final int bankCode) {
        banks.add(new Bank(bankCode));
    }

    /**
     * Gets the account number.
     *
     * @param personnelNumber
     *            the personnel number
     * @return the account number
     */
    public int getAccountNumber(final int personnelNumber) {
        int counter = 0;
        for (final Bank bank : banks) {
            counter += bank.getNumberOfAccounts(personnelNumber);
        }
        return counter;
    }

    /**
     * Adds the user to the bank.
     *
     * @param firstName
     *            the first name
     * @param lastName
     *            the last name
     * @param personnelNumber
     *            the personnel number
     * @param bankCode
     *            the bank code
     * @throws BankDoesNotExistException
     *             the bank does not exist exception
     */
    public void addUser(final String firstName, final String lastName, final int personnelNumber, final int bankCode)
            throws BankDoesNotExistException {
        final Bank bank = getBank(bankCode);
        bank.addUser(firstName, lastName, personnelNumber);
    }

    /**
     * Removes the account.
     *
     * @param accountNumber
     *            the account number
     * @param bankCode
     *            the bank code
     * @throws BankDoesNotExistException
     *             the bank does not exist exception
     * @throws AccountDoesNotExistException
     *             the account does not exist exception
     */
    public void removeAccount(final int accountNumber, final int bankCode)
            throws BankDoesNotExistException, AccountDoesNotExistException {
        final Bank bank = getBank(bankCode);
        bank.removeAccount(accountNumber);
    }

    /**
     * Deposit.
     *
     * @param accountNumber
     *            the account number
     * @param bankCode
     *            the bank code
     * @param amount
     *            the amount
     * @throws AccountDoesNotExistException
     *             the account does not exist exception
     * @throws BankDoesNotExistException
     *             the bank does not exist exception
     */
    public void deposit(final int accountNumber, final int bankCode, final int amount)
            throws AccountDoesNotExistException, BankDoesNotExistException {
        final Bank bank = getBank(bankCode);
        bank.deposit(accountNumber, amount);
    }

    /**
     * Withdraw.
     *
     * @param accountNumber
     *            the account number
     * @param bankCode
     *            the bank code
     * @param amount
     *            the amount
     * @return true, if successful
     * @throws AccountDoesNotExistException
     *             the account does not exist exception
     * @throws BankDoesNotExistException
     *             the bank does not exist exception
     */
    public boolean withdraw(final int accountNumber, final int bankCode, final int amount)
            throws AccountDoesNotExistException, BankDoesNotExistException {
        final Bank bank = getBank(bankCode);
        return bank.withdraw(accountNumber, amount);
    }

    /**
     * Transfer.
     *
     * @param fromAccountNumber
     *            the from account number
     * @param fromBankCode
     *            the from bank code
     * @param toAccountNumber
     *            the to account number
     * @param toBankCode
     *            the to bank code
     * @param amount
     *            the amount
     * @return true, if successful
     * @throws AccountDoesNotExistException
     *             the account does not exist exception
     * @throws BankDoesNotExistException
     *             the bank does not exist exception
     */
    public boolean transfer(final int fromAccountNumber, final int fromBankCode, final int toAccountNumber,
            final int toBankCode, final int amount) throws AccountDoesNotExistException, BankDoesNotExistException {
        final Bank fromBank = getBank(fromBankCode);
        // internal bank transfer
        if (fromBankCode == toBankCode) {
            return fromBank.transfer(fromAccountNumber, toAccountNumber, amount);
        }
        final Bank toBank = getBank(toBankCode);
        // transfer between two different banks
        if (fromBank.containsAccount(new Account(fromAccountNumber, fromBankCode))
                && toBank.containsAccount(new Account(toAccountNumber, toBankCode))) {
            if (fromBank.withdraw(fromAccountNumber, amount)) {
                fromBank.deposit(toAccountNumber, amount);
                return true;
            } else {
                return false;
            }
        } else {
            throw new AccountDoesNotExistException();
        }

    }

    /**
     * Contains account.
     *
     * @param accountNumber
     *            the account number
     * @param bankCode
     *            the bank code
     * @return true, if successful
     * @throws BankDoesNotExistException
     *             the bank does not exist exception
     */
    public boolean containsAccount(final int accountNumber, final int bankCode) throws BankDoesNotExistException {
        final Bank bank = getBank(bankCode);
        return bank.containsAccount(new Account(accountNumber, bankCode));
    }

    /**
     * Balance.
     *
     * @param accountNumber
     *            the account number
     * @param bankCode
     *            the bank code
     * @return the int
     * @throws AccountDoesNotExistException
     *             the account does not exist exception
     * @throws BankDoesNotExistException
     *             the bank does not exist exception
     */
    public int balance(final int accountNumber, final int bankCode)
            throws AccountDoesNotExistException, BankDoesNotExistException {
        return getBank(bankCode).balance(accountNumber);
    }

    /**
     * Adds the account.
     *
     * @param accountNumber
     *            the account number
     * @param personnelNumber
     *            the personnel number
     * @param bankCode
     *            the bank code
     * @throws BankDoesNotExistException
     *             the bank does not exist exception
     * @throws AccountHolderDoesNotExistException
     *             the account holder does not exist exception
     */
    public void addAccount(final int accountNumber, final int personnelNumber, final int bankCode)
            throws BankDoesNotExistException, AccountHolderDoesNotExistException {
        final Bank bank = getBank(bankCode);
        bank.addAccount(accountNumber, personnelNumber);
    }

    private Bank getBank(final int bankCode) throws BankDoesNotExistException {
        if (!banks.contains(new Bank(bankCode))) {
            throw new BankDoesNotExistException();
        }
        return banks.get(banks.indexOf(new Bank(bankCode)));
    }
}
