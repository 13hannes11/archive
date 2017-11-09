package edu.kit.informatik.commandline;

/**
 * The Enum Command.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public enum Command {

    /** The addbank. */
    ADDBANK("addbank", 1),
    /** The adduser. */
    ADDUSER("adduser", 4),
    /** The addaccount. */
    ADDACCOUNT("addaccount", 3),
    /** The removeaccount. */
    REMOVEACCOUNT("removeaccount", 2),
    /** The deposit. */
    DEPOSIT("deposit", 3),
    /** The withdraw. */
    WITHDRAW("withdraw", 3),
    /** The transfer. */
    TRANSFER("transfer", 5),
    /** The getaccountnumber. */
    GETACCOUNTNUMBER("getaccountnumber", 1),
    /** The containsaccount. */
    CONTAINSACCOUNT("containsaccount", 2),
    /** The balance. */
    BALANCE("balance", 2),
    /** The quit. */
    QUIT("quit", 0),
    /** The invalid. */
    INVALID("", -1);

    private final String command;

    /** The param count. */
    private final int paramCount;

    /**
     * Instantiates a new command.
     *
     * @param command
     *            the command
     * @param paramCount
     *            the param count
     */
    private Command(final String command, final int paramCount) {
        this.paramCount = paramCount;
        this.command = command;
    }

    /**
     * Gets the command.
     *
     * @return the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * Gets the param count.
     *
     * @return the param count
     */
    public int getParamCount() {
        return paramCount;
    }

    /**
     * converts string to command.
     *
     * @param str
     *            the str
     * @return the command
     */
    public static Command convertToCommand(final String str) {
        for (final Command command : Command.values()) {
            if (command.getCommand().equals(str)) {
                return command;
            }
        }
        return Command.INVALID;
    }
}
