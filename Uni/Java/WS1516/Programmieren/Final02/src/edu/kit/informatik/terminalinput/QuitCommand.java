package edu.kit.informatik.terminalinput;

import edu.kit.informatik.game.BoardGame;

/**
 * The Class InsertCommand.
 *
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class QuitCommand extends Command {

    private boolean readyToquit = false;

    /**
     * Instantiates a new insert command.
     */
    public QuitCommand() {
        super("quit", "quit", 0);
    }

    @Override
    public boolean correctParameters(final String[] parameters) {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.kit.informatik.terminlinput.Command#execute(java.lang.String,
     * edu.kit.informatik.graph.Graph)
     */
    @Override
    public void execute(final String command, final BoardGame game) {
        readyToquit = true;
    }

    @Override
    public boolean checkQuit() {
        return readyToquit;
    }
}
