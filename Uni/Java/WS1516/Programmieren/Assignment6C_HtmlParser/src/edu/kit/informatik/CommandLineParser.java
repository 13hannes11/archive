package edu.kit.informatik;

/**
 * The Class CommandLineParser.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class CommandLineParser {
    private static final String COMMAND_NOT_FOUND = "not valid command. Use: 'search <word>', 'tag <tag>', 'quit'";
    private static final String WRONG_PARAMETER_COUNT = "the command expects %d parameter";
    private boolean readyToQuit;
    private final Tag tag;

    /**
     * Instantiates a new command line parser.
     *
     * @param fileContent
     *            the file content
     */
    public CommandLineParser(final String fileContent) {
        readyToQuit = false;
        tag = new Tag(fileContent);
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

        final Command c = Command.convertToCommand(arr[0]);
        // If parameter count does not match command
        if (parameters.length != c.getParamCount() && !c.equals(Command.INVALID)) {
            error(String.format(WRONG_PARAMETER_COUNT, c.getParamCount()));
            return;
        }

        switch (c) {
            case QUIT:
                readyToQuit = true;
                break;
            case SEARCH:
                search(parameters);
                break;
            case TAG:
                tag(parameters);
                break;
            default:
                error(COMMAND_NOT_FOUND);
                break;

        }
    }

    private void search(final String[] parameters) {
        if (!parameters[0].matches("[a-zA-Z0-9_-]+")) {
            error("the parameters of search have to consist of letters, numbers, '_' and '-'");
            return;
        }
        final String str = tag.toString();

        int count = str.split(" " + parameters[0].toLowerCase() + " ").length - 1;
        if (str.startsWith(parameters[0].toLowerCase())) {
            count++;
        }
        if (str.endsWith(parameters[0].toLowerCase())) {
            count++;
        }
        Terminal.printLine(Integer.toString(count));
    }

    private void tag(final String[] parameters) {
        if (!parameters[0].matches("[a-z0-9]+") || parameters[0].equals("head")) {
            error("the parameters of 'tag' have to consist of lower case letters and/or numbers "
                    + "and searches for 'head' are forbidden");
            return;
        }
        Terminal.printLine(tag.getTagText(parameters[0]).trim().toLowerCase());
    }

    private void error(final String str) {
        Terminal.printLine("Error, " + str);
    }
}
