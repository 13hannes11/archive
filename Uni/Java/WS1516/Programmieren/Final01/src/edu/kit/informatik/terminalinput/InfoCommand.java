package edu.kit.informatik.terminalinput;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.graph.Graph;

/**
 * The Class InfoCommand.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class InfoCommand extends Command {

    /**
     * Instantiates a new info command.
     */
    public InfoCommand() {
        super("info", "info", 0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.kit.informatik.terminlinput.Command#execute(java.lang.String,
     * edu.kit.informatik.graph.Graph)
     */
    @Override
    public void execute(final String command, final Graph graph) {
        Terminal.printLine(graph.toString());
    }

}
