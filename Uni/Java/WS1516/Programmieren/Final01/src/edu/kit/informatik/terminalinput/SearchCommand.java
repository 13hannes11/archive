package edu.kit.informatik.terminalinput;

import edu.kit.informatik.Constant;
import edu.kit.informatik.Terminal;
import edu.kit.informatik.graph.Graph;
import edu.kit.informatik.graph.Vertex;
import edu.kit.informatik.graph.pathfinding.GraphPathFinder;
import edu.kit.informatik.graph.pathfinding.PathVertexDistanceComparator;
import edu.kit.informatik.graph.pathfinding.PathVertexTimeComparator;

/**
 * The Class SearchCommand.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class SearchCommand extends Command {

    /**
     * Instantiates a new search command.
     */
    public SearchCommand() {
        super("search", "search <startCity>;<destinationCity>;<time|route|optimal>", 3);
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
                && parameters[2].matches(Constant.REGEX_SEARCH);
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.kit.informatik.terminlinput.Command#execute(java.lang.String,
     * edu.kit.informatik.graph.Graph)
     */
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
        case Constant.REGEX_CRITERION_BOTH:
            out = Integer.toString(pathFinder.getPathDepthFirstSearch(start, searchFor).getOptimalScore());
            break;
        case Constant.REGEX_CRITERION_TIME:
            out = Integer.toString(
                    pathFinder.getPathReversedDijkstra(start, searchFor, new PathVertexTimeComparator()).getTime());
            break;
        case Constant.REGEX_CRITERION_DISTANCE:
            out = Integer.toString(pathFinder
                    .getPathReversedDijkstra(start, searchFor, new PathVertexDistanceComparator()).getDistance());
            break;
        default:
            InputManager.error(Constant.CODE_NOT_ACCESSIBLE);
            return;
        }
        Terminal.printLine(out);
    }
}
