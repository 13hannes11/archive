package edu.kit.informatik;

/**
 * The Class CommandLineParser.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class CommandLineParser {
    private static final String COMMAND_NOT_FOUND = "not valid command. Use: 'set <n>', 'quit'";
    private static final String WRONG_PARAMETER_COUNT = "the command expects %d parameter";
    private boolean readyToQuit;

    /**
     * Instantiates a new command line parser.
     */
    public CommandLineParser() {
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
            parameters = arr[1].split("[\\,]");
        } else {
            parameters = new String[0];
        }

        switch (Command.convertToCommand(arr[0])) {
            case QUIT:
                if (parameters.length != Command.QUIT.getParamCount()) {
                    error(String.format(WRONG_PARAMETER_COUNT, Command.QUIT.getParamCount()));
                } else {
                    readyToQuit = true;
                }
                break;
            case SET:
                if (parameters.length != Command.SET.getParamCount()) {
                    error(String.format(WRONG_PARAMETER_COUNT, Command.QUIT.getParamCount()));
                } else {
                    set(parameters);
                }
                break;
            default:
                error(COMMAND_NOT_FOUND);
                break;

        }
    }

    private void set(final String[] param) {
        try {
            final int n = Integer.parseInt(param[0]);
            final double rounded = Math.ceil(Eulerapproximator.calcEuler(n) * 1000.0) / 1000.0;
            Terminal.printLine(Double.toString(rounded));
        } catch (final NumberFormatException e) {
            error("set expects <n> where n is a number as parameter");
        } catch (final IllegalNumberException e) {
            error(e.getMessage());
        }
    }

    private void error(final String str) {
        Terminal.printLine("Error, " + str);
    }
}
