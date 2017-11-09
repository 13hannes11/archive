package edu.kit.informatik.terminalinput;

import edu.kit.informatik.graph.Graph;

/**
 * The Class Command.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public abstract class Command {

    /** The name. */
    private final String name;

    private final String info;

    /** The parameter count. */
    private final int parameterCount;

    /**
     * Instantiates a new command.
     *
     * @param name
     *            the name
     * @param info
     *            the information on how to use the command
     * @param parameterCount
     *            the parameter count
     */
    public Command(final String name, final String info, final int parameterCount) {
        this.name = name;
        this.info = info;
        this.parameterCount = parameterCount;
    }

    /**
     * Checks if the string starts with the correct name of this command.
     *
     * @param str
     *            the str
     * @return true, if successful
     */
    private boolean correctCommand(final String str) {
        return name.equals(str.split(" ")[0]);
    }

    /**
     * Checks if string has valid command form. Will call
     * <code>correctCommand</code>, <code>correctParameters</code> and will
     * check parameterCount.
     *
     * @param command
     *            the command
     * @return true, if successful
     */
    public final boolean validate(final String command) {
        final String[] parameters = commandToParametersArray(command);
        return correctCommand(command) && parameters.length == parameterCount && correctParameters(parameters);
    }

    /**
     * Checks if parameters of string are correct.
     *
     * @param command
     *            the command
     * @return true, if successful
     */
    public boolean correctParameters(final String[] command) {
        return true;
    }

    /**
     * Executes the command.
     *
     * @param command
     *            the command
     * @param graph
     *            the graph
     */
    public abstract void execute(String command, Graph graph);

    /**
     * Gets the parameters from a String which is a console command.
     *
     * @param command
     *            the command
     * @return the string[] that contains all parameters
     */
    public String[] commandToParametersArray(final String command) {
        final String[] tmp = command.split(" ");
        if (tmp.length > 1) {
            return tmp[1].split(";");
        }
        return new String[0];
    }

    /**
     * Checks if program should quit.
     *
     * @return true, if successful
     */
    public boolean checkQuit() {
        return false;
    }

    /**
     * Checks if objects are equal.
     *
     * @param obj
     *            the obj
     * @return true, if same name and same parameterCount
     */
    @Override
    public boolean equals(final Object obj) {
        return (obj.getClass().equals(this.getClass())) && ((Command) obj).name.equals(this.name)
                && ((Command) obj).parameterCount == this.parameterCount;
    }
    /**
     * Gets the name.
     *
     * @return the name
     */
    /*public String getName() {
        return name;
    }*/

    /**
     * Gets the info.
     *
     * @return the info
     */
    public String getInfo() {
        return info;
    }

}
