package edu.kit.informatik.terminalinput;

import edu.kit.informatik.Constant;
import edu.kit.informatik.Terminal;
import edu.kit.informatik.graph.Graph;
import edu.kit.informatik.graph.Vertex;
import edu.kit.informatik.graph.pathfinding.GraphPathFinder;
import edu.kit.informatik.graph.pathfinding.PathVertex;
import edu.kit.informatik.graph.pathfinding.PathVertexDistanceComparator;
import edu.kit.informatik.graph.pathfinding.PathVertexTimeComparator;

/**
 * The Class RouteCommand.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class RouteCommand extends Command {

    /**
     * Instantiates a new route command.
     */
    public RouteCommand() {
        super("route", "route <startCity>;<destinationCity>;<time|route|optimal|all>", 3);
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.kit.informatik.terminlinput.Command#correctParameters(java.lang.
     * String)
     */
    @Override
    public boolean correctParameters(final String[] parameters) {
        return parameters[0].matches(Constant.REGEX_CITY_NAME) && parameters[1].matches(Constant.REGEX_CITY_NAME)
                && parameters[2].matches(Constant.REGEX_ROUTE);
    }

    @Override
    public void execute(final String command, final Graph graph) {
        final GraphPathFinder pathFinder = new GraphPathFinder(graph.getEdges());
        final String[] parameters = commandToParametersArray(command);

        final Vertex start = new Vertex(parameters[0]);
        final Vertex searchFor = new Vertex(parameters[1]);
        final String criterion = parameters[2];

        // Check if start and searchFor are part of graph
        if (!graph.contains(start) || !graph.contains(searchFor)) {
            InputManager.error(Constant.VERTEX_NOT_FOUND);
            return;
        }

        String out = "";
        switch (criterion) {
        case Constant.REGEX_CRITERION_ALL:
            out = "";
            for (final PathVertex pVert : pathFinder.getAllPaths(start, searchFor)) {
                out += pVert.toString() + "\n";
            }
            break;
        case Constant.REGEX_CRITERION_BOTH:
            out = pathFinder.getPathDepthFirstSearch(start, searchFor).toString();
            break;
        case Constant.REGEX_CRITERION_TIME: {
            out = "";
            final String[] arr = pathFinder.getPathReversedDijkstra(start, searchFor, new PathVertexTimeComparator())
                    .toString().split(" ");
            // Reverse order
            for (String anArr : arr) {
                out = anArr + " " + out;
            }
            out = out.trim();
            break;
        }
        case Constant.REGEX_CRITERION_DISTANCE: {
            out = "";
            final String[] arr = pathFinder
                    .getPathReversedDijkstra(start, searchFor, new PathVertexDistanceComparator()).toString()
                    .split(" ");
            // Reverse order
            for (String anArr : arr) {
                out = anArr + " " + out;
            }
            out = out.trim();
            break;
        }
        default:
            InputManager.error(Constant.CODE_NOT_ACCESSIBLE);
            return;
        }
        out = out.trim();
        Terminal.printLine(out);
    }

}
