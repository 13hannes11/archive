package edu.kit.informatik.terminalinput;

import edu.kit.informatik.Constant;
import edu.kit.informatik.Terminal;
import edu.kit.informatik.graph.Edge;
import edu.kit.informatik.graph.Graph;
import edu.kit.informatik.graph.Vertex;

/**
 * The Class RemoveCommand.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class RemoveCommand extends Command {

    /**
     * Instantiates a new removes the command.
     */
    public RemoveCommand() {
        super("remove", "remove <firstCity>;<secondCity>", 2);
    }

    @Override
    public boolean correctParameters(final String[] parameters) {
        return parameters[0].matches(Constant.REGEX_CITY_NAME) && parameters[1].matches(Constant.REGEX_CITY_NAME);
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.kit.informatik.terminlinput.Command#execute(java.lang.String,
     * edu.kit.informatik.graph.Graph)
     */
    @Override
    public void execute(final String command, final Graph graph) {
        final String[] parameters = commandToParametersArray(command);
        final Vertex v = new Vertex(parameters[0]);
        final Vertex w = new Vertex(parameters[1]);

        if (graph.removeEdge(new Edge(v, w, 10, 10))) {
            Terminal.printLine("OK");
        } else {
            InputManager.error(Constant.EDGE_CANNOT_REMOVE);
        }
    }

}
