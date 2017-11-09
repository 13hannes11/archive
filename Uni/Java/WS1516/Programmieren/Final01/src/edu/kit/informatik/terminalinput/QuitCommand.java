package edu.kit.informatik.terminalinput;

import edu.kit.informatik.graph.Graph;

/**
 * The Class QuitCommand.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class QuitCommand extends Command {
    private boolean quit;

    /**
     * Instantiates a new quit command.
     */
    public QuitCommand() {
        super("quit", "quit", 0);
        quit = false;
    }

    @Override
    public void execute(final String command, final Graph graph) {
        quit = true;
    }

    @Override
    public boolean checkQuit() {
        return quit;
    }

}
