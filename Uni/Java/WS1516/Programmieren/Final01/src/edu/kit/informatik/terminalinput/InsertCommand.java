package edu.kit.informatik.terminalinput;

import edu.kit.informatik.Constant;
import edu.kit.informatik.exception.IllegalObjectException;
import edu.kit.informatik.graph.Edge;
import edu.kit.informatik.graph.Graph;
import edu.kit.informatik.graph.Vertex;

/**
 * The Class InsertCommand.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class InsertCommand extends Command {

    /**
     * Instantiates a new insert command.
     */
    public InsertCommand() {
        super("insert", "insert <firstCity>;<secondCity>;<distanceKilometer>;<distanceMinutes>", 4);
    }

    @Override
    public boolean correctParameters(final String[] parameters) {
        return parameters[0].matches(Constant.REGEX_CITY_NAME) && parameters[1].matches(Constant.REGEX_CITY_NAME)
                && parameters[2].matches(Constant.REGEX_POSITIVE_INTEGER)
                && parameters[3].matches(Constant.REGEX_POSITIVE_INTEGER);
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

        Vertex v = new Vertex(parameters[0]);
        Vertex w = new Vertex(parameters[1]);
        int distance = 0;
        int time = 0;
        try {
            distance = Integer.parseInt(parameters[2]);
            time = Integer.parseInt(parameters[3]);
        } catch (final NumberFormatException e) {
            InputManager.error(Constant.NUMBER_FORMAT_ILLEGAL);
            return;
        }

        if (!graph.contains(v) && !graph.contains(w)) {
            InputManager.error(Constant.EDGE_VERTEX_NOT_FOUND);
            return;
        }

        // Get the correct reference
        if (graph.contains(v)) {
            v = graph.getVertex(v);
        }
        if (graph.contains(w)) {
            w = graph.getVertex(w);
        }

        final Edge edge = new Edge(v, w, distance, time);
        if (graph.contains(edge)) {
            InputManager.error(Constant.EDGE_CONTAINED_ALLREADY);
            return;
        }
        try {
            graph.addEdge(edge);
            InputManager.printSuccess();
        } catch (final IllegalObjectException e) {
            InputManager.error(e.getMessage());
        }

    }

}
