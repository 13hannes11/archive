package edu.kit.informatik;

import edu.kit.informatik.exceptions.InvalidCommandException;
import edu.kit.informatik.list.Container;
import edu.kit.informatik.list.MinimaList;

/**
 * The Class BankRegistry.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class BankRegistry {
    private boolean readyToQuit;
    private final MinimaList<Bank> banks;

    /**
     * Instantiates a new bank registry.
     */
    public BankRegistry() {
        readyToQuit = false;
        banks = new MinimaList<Bank>();
    }

    /**
     * Run the bank registry
     */
    public void run() {
        while (!readyToQuit) {
            try {
                processCommand(Terminal.readLine());
            } catch (final InvalidCommandException e) {
                Terminal.printLine("Error: " + e.getMessage());
            }
        }
    }

    private boolean processBankActions(final String action, final String[] parameters) throws InvalidCommandException {
        switch (action) {
            case "addbank": {
                if (parameters.length != 1) {
                    throw new InvalidCommandException("addbank expects one parameter: addbank <bankCode>");
                }
                final int bankCode = Integer.parseInt(parameters[0]);
                addBank(bankCode);
                Terminal.printLine("OK");
                return true;
            }
            case "adduser": {
                if (parameters.length != 4) {
                    throw new InvalidCommandException("adduser expects four parameters: "
                            + "addbank <firsName>;<lastName>;<personnelNumber>;<bankCode>");
                }
                final String firstName = parameters[0];
                final String lastName = parameters[1];
                final int personnelNumber = Integer.parseInt(parameters[2]);
                final int bankCode = Integer.parseInt(parameters[3]);

                addUser(firstName, lastName, personnelNumber, bankCode);
                Terminal.printLine("OK");
                return true;
            }
            case "addaccount": {
                if (parameters.length != 3) {
                    throw new InvalidCommandException("addaccount expects three parameters: "
                            + "addaccount <accountNumber>;<personnelMumber>;<bankCode>");
                }
                final int accountNumber = Integer.parseInt(parameters[0]);
                final int personnelNumber = Integer.parseInt(parameters[1]);
                final int bankCode = Integer.parseInt(parameters[2]);

                addAccount(accountNumber, personnelNumber, bankCode);
                Terminal.printLine("OK");
                return true;
            }
            case "removeaccount": {
                if (parameters.length != 2) {
                    throw new InvalidCommandException(
                            "removeaccount expects two parameters: removeaccount <accountNumber>;<bankCode>;");
                }
                final int accountNumber = Integer.parseInt(parameters[0]);
                final int bankCode = Integer.parseInt(parameters[1]);

                removeAccount(accountNumber, bankCode);
                Terminal.printLine("OK");
                return true;
            }
            case "containsaccount": {
                if (parameters.length != 2) {
                    throw new InvalidCommandException(
                            "containsaccount expects two parameters: containsaccount <accountNumber>;<bankCode>");
                }
                final int accountNumber = Integer.parseInt(parameters[0]);
                final int bankCode = Integer.parseInt(parameters[1]);

                Terminal.printLine(new Boolean(containsAccount(accountNumber, bankCode)).toString());
                return true;
            }
            case "getaccountnumber": {
                if (parameters.length != 1) {
                    throw new InvalidCommandException(
                            "getaccountnumber expects one parameter: getaccountnumber <personnelNumber>");
                }
                final int personnelNumber = Integer.parseInt(parameters[0]);

                Terminal.printLine(new Integer(getAccountCount(personnelNumber)).toString());
                return true;
            }
            default:
                return false;
        }
    }

    private void processCommand(final String command) throws InvalidCommandException {
        final String action = command.split(" ")[0];
        String[] parameters = new String[0];
        if (command.split(" ").length >= 2) {
            parameters = (command.split(" ")[1]).split(";");
        }

        switch (action) {
            case "deposit": {
                if (parameters.length != 3) {
                    throw new InvalidCommandException(
                            "deposit expects three parameters: deposit <accountNumber>;<bankCode>;<amount>");
                }
                final int accountNumber = Integer.parseInt(parameters[0]);
                final int bankCode = Integer.parseInt(parameters[1]);
                final int amount = Integer.parseInt(parameters[2]);

                deposit(accountNumber, bankCode, amount);
                Terminal.printLine("OK");
                break;
            }
            case "withdraw": {
                if (parameters.length != 3) {
                    throw new InvalidCommandException(
                            "withdraw expects three parameters: withdraw <accountNumber>;<bankCode>;<amount>");
                }
                final int accountNumber = Integer.parseInt(parameters[0]);
                final int bankCode = Integer.parseInt(parameters[1]);
                final int amount = Integer.parseInt(parameters[2]);

                withdraw(accountNumber, bankCode, amount);
                Terminal.printLine("OK");
                break;
            }
            case "transfer": {
                if (parameters.length != 5) {
                    throw new InvalidCommandException("transfer expects five xparameters: "
                            + "transfer <fromAccountNumber>;<fromBankCode>;<toAccountNumber>;<toBankCode>;<amount>");
                }
                final int fromAccountNumber = Integer.parseInt(parameters[0]);
                final int fromBankCode = Integer.parseInt(parameters[1]);
                final int toAccountNumber = Integer.parseInt(parameters[2]);
                final int toBankCode = Integer.parseInt(parameters[3]);
                final int amount = Integer.parseInt(parameters[4]);

                transfer(fromAccountNumber, fromBankCode, toAccountNumber, toBankCode, amount);
                Terminal.printLine("OK");
                break;
            }
            case "balance": {
                if (parameters.length != 2) {
                    throw new InvalidCommandException(
                            "balance expects two parameters: balance <accountNumber>;<bankCode>");
                }
                final int accountNumber = Integer.parseInt(parameters[0]);
                final int bankCode = Integer.parseInt(parameters[1]);

                Terminal.printLine(new Integer(balance(accountNumber, bankCode)).toString());
                break;
            }
            case "quit":
                if (parameters.length != 0) {
                    throw new InvalidCommandException("quit expects zero parameters: quit");
                }
                readyToQuit = true;
                break;
            default:
                if (!processBankActions(action, parameters)) {
                    throw new InvalidCommandException(
                            "command not found, supported commands: 'quit', 'balance <accountNumber>;<bankCode>', "
                                    + "'containsaccount <accountNumber>;<bankCode>', 'getaccountnumber "
                                    + "<personnelNumber>', 'transfer <fromAccountNumber>;<fromBankCode>;"
                                    + "<toAccountNumber>;<toBankCode>;<amount>', 'withdraw <accountNumber>;<bankCode>"
                                    + ";<amount>', 'deposit <accountNumber>;<bankCode>;<amount>', 'removeaccount "
                                    + "<accountNumber>;<bankCode>;', 'addaccount <accountNumber>;<personnelMumber>;"
                                    + "<bankCode>', 'addbank <bankCode>'");
                }
        }
    }

    private void addBank(final int bankCode) throws InvalidCommandException {
        final Bank b = new Bank(bankCode);
        if (bankCode <= 0) {
            throw new InvalidCommandException("bank code has to be a positive integer");
        }
        if (banks.contains(b)) {
            throw new InvalidCommandException("bank with this bank code already exists");
        }
        banks.add(b);
    }

    private void addUser(final String firstName, final String lastName, final int personnelNumber, final int bankCode)
            throws InvalidCommandException {

        if (!(isValidName(firstName) && isValidName(lastName))) {
            throw new InvalidCommandException("names have to be in lowercase and can only contain characters a-z");
        }
        if (!(isNaturalNumber(personnelNumber) && isNaturalNumber(bankCode))) {
            throw new InvalidCommandException("numbers have to be natural numbers");
        }
        final int index = banks.getIndex(new Bank(bankCode));
        if (index < 0) {
            throw new InvalidCommandException("bank (" + bankCode + ") does not exist");
        }
        banks.get(index).addUser(firstName, lastName, personnelNumber);
    }

    private boolean isNaturalNumber(final int number) {
        return (number > 0);
    }

    private boolean isValidName(final String name) {
        if (name == "") {
            return false;
        }

        final char[] chars = name.toCharArray();
        for (final char c : chars) {
            // If char is not a lower-case letter
            if (!(Character.isLowerCase(c) && Character.isLetter(c))) {
                return false;
            }
        }
        return true;
    }

    private void addAccount(final int accountNumber, final int personnelNumber, final int bankCode)
            throws InvalidCommandException {
        if (!(isNaturalNumber(personnelNumber) && isNaturalNumber(bankCode) && isNaturalNumber(accountNumber))) {
            throw new InvalidCommandException("numbers have to be natural numbers");
        }
        final int index = banks.getIndex(new Bank(bankCode));
        if (index < 0) {
            throw new InvalidCommandException("bank (" + bankCode + ") does not exist");
        }
        banks.get(index).addAccount(accountNumber, personnelNumber);
    }

    private void removeAccount(final int accountNumber, final int bankCode) throws InvalidCommandException {
        if (!(isNaturalNumber(accountNumber) && isNaturalNumber(bankCode))) {
            throw new InvalidCommandException("numbers have to be natural numbers");
        }
        final int index = banks.getIndex(new Bank(bankCode));
        if (index < 0) {
            throw new InvalidCommandException("bank (" + bankCode + ") does not exist");
        }
        banks.get(index).removeAccount(accountNumber);
    }

    private void deposit(final int accountNumber, final int bankCode, final int amount) throws InvalidCommandException {
        if (!isNaturalNumber(accountNumber) || !isNaturalNumber(bankCode) || !isNaturalNumber(amount)) {
            throw new InvalidCommandException("numbers have to be natural numbers");
        }
        final int index = banks.getIndex(new Bank(bankCode));
        if (index < 0) {
            throw new InvalidCommandException("bank (" + bankCode + ") does not exist");
        }
        banks.get(index).deposit(accountNumber, amount);
    }

    private void withdraw(final int accountNumber, final int bankCode, final int amount)
            throws InvalidCommandException {
        if (!isNaturalNumber(accountNumber) || !isNaturalNumber(bankCode) || !isNaturalNumber(amount)) {
            throw new InvalidCommandException("numbers have to be natural numbers");
        }
        final int index = banks.getIndex(new Bank(bankCode));
        if (index < 0) {
            throw new InvalidCommandException("bank (" + bankCode + ") does not exist");
        }
        banks.get(index).withdraw(accountNumber, amount);
    }

    private void transfer(final int fromAccountNumber, final int fromBankCode, final int toAccountNumber,
            final int toBankCode, final int amount) throws InvalidCommandException {
        if (!isNaturalNumber(fromAccountNumber) || !isNaturalNumber(fromBankCode) || !isNaturalNumber(toAccountNumber)
                || !isNaturalNumber(toBankCode) || !isNaturalNumber(amount)) {
            throw new InvalidCommandException("numbers have to be natural numbers");
        }

        if (fromBankCode == toBankCode) {
            // Internal bank transfer
            final int index = banks.getIndex(new Bank(fromBankCode));
            if (index < 0) {
                throw new InvalidCommandException("bank (" + fromBankCode + ") does not exist");
            }
            banks.get(index).internalBankTransfer(fromAccountNumber, toAccountNumber, amount);
        } else {
            // external bank transfer
            withdraw(fromAccountNumber, fromBankCode, amount);
            deposit(toAccountNumber, toBankCode, amount);
        }
    }

    private int getAccountCount(final int personnelNumber) {
        Container<Bank> container = banks.getFirstContainer();
        while (container != null) {
            final Bank bank = container.getData();
            if (bank.getAccountCount(personnelNumber) > 0) {
                return bank.getAccountCount(personnelNumber);
            }
            container = container.getNext();
        }
        return 0;
    }

    private boolean containsAccount(final int accountNumber, final int bankCode) throws InvalidCommandException {
        final int index = banks.getIndex(new Bank(bankCode));
        if (index == -1) {
            throw new InvalidCommandException("bank with this bankcode does not exist");
        }
        return banks.get(index).containsAccount(accountNumber);
    }

    private int balance(final int accountNumber, final int bankCode) throws InvalidCommandException {
        final int index = banks.getIndex(new Bank(bankCode));
        if (index == -1) {
            throw new InvalidCommandException("bank with this bankcode does not exist");
        }
        return banks.get(index).balance(accountNumber);
    }

}
