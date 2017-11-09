package edu.kit.informatik.commandline;

import edu.kit.informatik.BankRegistry;
import edu.kit.informatik.Terminal;
import edu.kit.informatik.exceptions.AccountDoesNotExistException;
import edu.kit.informatik.exceptions.AccountHolderDoesNotExistException;
import edu.kit.informatik.exceptions.BankDoesNotExistException;

/**
 * The Class CommandLineParser.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class CommandLineParser {
    private static final String SUCCESSFULL = "OK";
    private static final String NOT_NATURAL = "parameters have to be natural numbers";
    private static final String ACCOUNDHODLER_DOES_NOT_EXIST = "the accountholder does not exists within the bank";
    private static final String BANK_DOES_NOT_EXIST = "the bank does not exist";
    private static final String ACCOUNT_DOES_NOT_EXIST = "account does not exist in this bank";
    private static final String COMMAND_NOT_FOUND = "not valid command.'";
    private static final String WRONG_PARAMETER_COUNT = "the command expects %d parameter";
    private static final String WRONG_STRING_FORMAT = "strings have to be lowercase";
    private static final String UNEXPECTED_ERROR = "something unexpected went wront :(";

    private boolean readyToQuit;

    private final BankRegistry bankRegistry;

    /**
     * Instantiates a new command line parser.
     */
    public CommandLineParser() {
        bankRegistry = new BankRegistry();
        readyToQuit = false;
    }

    /**
     * Runs the command line parser
     */
    public void run() {
        while (!readyToQuit) {
            runCommand(Terminal.readLine());
        }
    }

    private void runCommand(final String command) {
        if (command == null) {
            error(COMMAND_NOT_FOUND);
            return;
        }
        final String[] arr = command.split("[\\s]");
        String[] parameters;
        if (arr.length > 1) {
            parameters = arr[1].split(";");
        } else {
            parameters = new String[0];
        }

        final Command c = Command.convertToCommand(arr[0]);
        // If parameter count does not match command
        if (parameters.length != c.getParamCount() && !c.equals(Command.INVALID)) {
            error(String.format(WRONG_PARAMETER_COUNT, c.getParamCount()));
            return;
        }

        switch (c) {
            case WITHDRAW:
                withdraw(parameters);
                break;
            case TRANSFER:
                transfer(parameters);
                break;
            case REMOVEACCOUNT:
                removeAccount(parameters);
                break;
            case GETACCOUNTNUMBER:
                getAccountNumber(parameters);
                break;
            case DEPOSIT:
                deposit(parameters);
                break;
            case CONTAINSACCOUNT:
                containsAccount(parameters);
                break;
            case BALANCE:
                balance(parameters);
                break;
            case ADDUSER:
                addUser(parameters);
                break;
            case ADDBANK:
                addBank(parameters);
                break;
            case ADDACCOUNT:
                addAccount(parameters);
                break;
            case QUIT:
                readyToQuit = true;
                break;
            default:
                error(COMMAND_NOT_FOUND);
                break;
        }
    }

    private void addUser(final String[] parameters) {
        if (!isNaturalNumberString(parameters[2]) || !isNaturalNumberString(parameters[3])) {
            error(NOT_NATURAL);
            return;
        }
        if (!parameters[0].matches("[a-z]+") || !parameters[1].matches("[a-z]+")) {
            error(WRONG_STRING_FORMAT);
            return;
        }
        final String firstName = parameters[0];
        final String lastName = parameters[1];
        final int personnelNumber = Integer.parseInt(parameters[2]);
        final int bankCode = Integer.parseInt(parameters[3]);
        try {
            bankRegistry.addUser(firstName, lastName, personnelNumber, bankCode);
            Terminal.printLine(SUCCESSFULL);
        } catch (final BankDoesNotExistException e) {
            error(BANK_DOES_NOT_EXIST);
        }
    }

    private void addBank(final String[] parameters) {
        if (!isNaturalNumberString(parameters[0])) {
            error(NOT_NATURAL);
            return;
        }
        final int bankCode = Integer.parseInt(parameters[0]);
        bankRegistry.addBank(bankCode);
        Terminal.printLine(SUCCESSFULL);
    }

    private void addAccount(final String[] parameters) {
        if (!isNaturalNumberString(parameters[0]) || !isNaturalNumberString(parameters[1])
                || !isNaturalNumberString(parameters[2])) {
            error(NOT_NATURAL);
            return;
        }
        final int accountNumber = Integer.parseInt(parameters[0]);
        final int personnelNumber = Integer.parseInt(parameters[1]);
        final int bankCode = Integer.parseInt(parameters[2]);

        try {
            bankRegistry.addAccount(accountNumber, personnelNumber, bankCode);
            Terminal.printLine(SUCCESSFULL);
        } catch (final BankDoesNotExistException e) {
            error(BANK_DOES_NOT_EXIST);
        } catch (final AccountHolderDoesNotExistException e) {
            error(ACCOUNDHODLER_DOES_NOT_EXIST);
        }
    }

    private void balance(final String[] parameters) {
        if (!isNaturalNumberString(parameters[0]) || !isNaturalNumberString(parameters[1])) {
            error(NOT_NATURAL);
            return;
        }
        final int accountNumber = Integer.parseInt(parameters[0]);
        final int bankCode = Integer.parseInt(parameters[1]);

        try {
            Terminal.printLine(Integer.toString(bankRegistry.balance(accountNumber, bankCode)));
        } catch (final AccountDoesNotExistException e) {
            error(ACCOUNT_DOES_NOT_EXIST);
        } catch (final BankDoesNotExistException e) {
            error(BANK_DOES_NOT_EXIST);
        }
    }

    private void containsAccount(final String[] parameters) {
        if (!isNaturalNumberString(parameters[0]) || !isNaturalNumberString(parameters[1])) {
            error(NOT_NATURAL);
            return;
        }
        final int accountNumber = Integer.parseInt(parameters[0]);
        final int bankCode = Integer.parseInt(parameters[1]);
        try {
            Terminal.printLine(Boolean.toString(bankRegistry.containsAccount(accountNumber, bankCode)));
        } catch (final BankDoesNotExistException e) {
            error(BANK_DOES_NOT_EXIST);
        }
    }

    private void deposit(final String[] parameters) {
        if (!isNaturalNumberString(parameters[0]) || !isNaturalNumberString(parameters[1])
                || !isNaturalNumberString(parameters[2])) {
            error(NOT_NATURAL);
            return;
        }
        final int accountNumber = Integer.parseInt(parameters[0]);
        final int bankCode = Integer.parseInt(parameters[1]);
        final int amount = Integer.parseInt(parameters[2]);

        try {
            bankRegistry.deposit(accountNumber, bankCode, amount);
            Terminal.printLine(SUCCESSFULL);
        } catch (final AccountDoesNotExistException e) {
            error(ACCOUNT_DOES_NOT_EXIST);
        } catch (final BankDoesNotExistException e) {
            error(BANK_DOES_NOT_EXIST);
        }
    }

    private void getAccountNumber(final String[] parameters) {
        if (!isNaturalNumberString(parameters[0])) {
            error(NOT_NATURAL);
            return;
        }
        final int personnelNumber = Integer.parseInt(parameters[0]);
        Terminal.printLine(Integer.toString(bankRegistry.getAccountNumber(personnelNumber)));
    }

    private void removeAccount(final String[] parameters) {
        if (!isNaturalNumberString(parameters[0]) || !isNaturalNumberString(parameters[1])) {
            error(NOT_NATURAL);
            return;
        }
        final int accountNumber = Integer.parseInt(parameters[0]);
        final int bankCode = Integer.parseInt(parameters[1]);
        try {
            bankRegistry.removeAccount(accountNumber, bankCode);
            Terminal.printLine(SUCCESSFULL);
        } catch (final AccountDoesNotExistException e) {
            error(ACCOUNT_DOES_NOT_EXIST);
        } catch (final BankDoesNotExistException e) {
            error(BANK_DOES_NOT_EXIST);
        }

    }

    private void transfer(final String[] parameters) {
        if (!isNaturalNumberString(parameters[0]) || !isNaturalNumberString(parameters[1])
                || !isNaturalNumberString(parameters[2]) || !isNaturalNumberString(parameters[3])
                || !isNaturalNumberString(parameters[4])) {
            error(NOT_NATURAL);
            return;
        }
        final int fromAccountNumber = Integer.parseInt(parameters[0]);
        final int fromBankCode = Integer.parseInt(parameters[1]);
        final int toAccountNumber = Integer.parseInt(parameters[2]);
        final int toBankCode = Integer.parseInt(parameters[3]);
        final int amount = Integer.parseInt(parameters[4]);

        try {
            bankRegistry.transfer(fromAccountNumber, fromBankCode, toAccountNumber, toBankCode, amount);
            Terminal.printLine(SUCCESSFULL);
        } catch (final AccountDoesNotExistException e) {
            error(ACCOUNT_DOES_NOT_EXIST);
        } catch (final BankDoesNotExistException e) {
            error(BANK_DOES_NOT_EXIST);
        }
    }

    private void withdraw(final String[] parameters) {
        if (!isNaturalNumberString(parameters[0]) || !isNaturalNumberString(parameters[1])
                || !isNaturalNumberString(parameters[2])) {
            error(NOT_NATURAL);
            return;
        }
        final int accountNumber = Integer.parseInt(parameters[0]);
        final int bankCode = Integer.parseInt(parameters[1]);
        final int amount = Integer.parseInt(parameters[2]);

        try {
            if (bankRegistry.withdraw(accountNumber, bankCode, amount)) {
                Terminal.printLine(SUCCESSFULL);
            } else {
                error(UNEXPECTED_ERROR);
            }
        } catch (final AccountDoesNotExistException e) {
            error(ACCOUNT_DOES_NOT_EXIST);
        } catch (final BankDoesNotExistException e) {
            error(BANK_DOES_NOT_EXIST);
        }
    }

    private boolean isNaturalNumberString(final String str) {
        return str.matches("[0-9]+");
    }

    private void error(final String str) {
        Terminal.printLine("Error, " + str);
    }
}
