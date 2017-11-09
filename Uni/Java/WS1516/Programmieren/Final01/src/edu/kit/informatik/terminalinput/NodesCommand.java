package edu.kit.informatik.terminalinput;

import edu.kit.informatik.Constant;
import edu.kit.informatik.Terminal;
import edu.kit.informatik.exception.IllegalObjectException;
import edu.kit.informatik.graph.Graph;
import edu.kit.informatik.graph.Vertex;

/**
 * The Class NodesCommand.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class NodesCommand extends Command {

    /**
     * Instantiates a new nodes command.
     */
    public NodesCommand() {
        super("nodes", "nodes <City>", 1);
    }

    @Override
    public boolean correctParameters(final String[] parameters) {
        return parameters[0].matches(Constant.REGEX_CITY_NAME);
    }

    @Override
    public void execute(final String command, final Graph graph) {
        final Vertex v = new Vertex(commandToParametersArray(command)[0]);
        try {
            Terminal.printLine(graph.neighboursToString(v));
        } catch (final IllegalObjectException e) {
            InputManager.error(e.getMessage());
        }
    }

}
