package edu.kit.informatik;

/**
 * The Enum Command.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public enum Command {

    /** set command. */
    SET("set", 1),

    /** quit command. */
    QUIT("quit", 0),

    /** invalid command. */
    INVALID("", -1);

    /** The command. */
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
        return INVALID;
    }
}
