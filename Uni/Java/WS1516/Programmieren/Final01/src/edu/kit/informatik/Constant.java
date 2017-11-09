package edu.kit.informatik;

/**
 * All constants used in the program
 *
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public final class Constant {

    /**
     * The regex for a positive integer.
     */
    public static final String REGEX_POSITIVE_INTEGER = "([+]?[0-9]*[1-9][0-9]*)";
    /**
     * The regex for a city name.
     */
    public static final String REGEX_CITY_NAME = "([A-Za-z-]+)";
    /**
     * The regex for the SEPARATOR of the two parts in the file.
     */
    public static final String SEPARATOR = "--";
    /**
     * The regex for an edge.
     */
    public static final String REGEX_EDGE = REGEX_CITY_NAME + ";" + REGEX_CITY_NAME + ";"
            + REGEX_POSITIVE_INTEGER + ";" + REGEX_POSITIVE_INTEGER;
    /**
     * The regex for a file that contains a graph.
     */
    public static final String REGEX_GRAPH_FILE = "(" + REGEX_CITY_NAME + "\\n){2,}"
            + SEPARATOR + "(\\n" + REGEX_EDGE + "){1,}";

    /**
     * The Constant REGEX_CRITERION_TIME.
     */
    public static final String REGEX_CRITERION_TIME = "time";
    /**
     * The Constant REGEX_CRITERION_DISTANCE.
     */
    public static final String REGEX_CRITERION_DISTANCE = "route";
    /**
     * The Constant REGEX_CRITERION_BOTH.
     */
    public static final String REGEX_CRITERION_BOTH = "optimal";
    /**
     * The Constant REGEX_CRITERION_ALL.
     */
    public static final String REGEX_CRITERION_ALL = "all";
    /**
     * The Constant REGEX_SEARCH.
     */
    public static final String REGEX_SEARCH = "((" + REGEX_CRITERION_TIME + ")|(" + REGEX_CRITERION_DISTANCE + ")|("
            + REGEX_CRITERION_BOTH + "))";
    /**
     * The Constant REGEX_ROUTE.
     */
    public static final String REGEX_ROUTE = "(" + REGEX_SEARCH + "|" + REGEX_CRITERION_ALL + ")";

    ///////////////////////// ////Error Messages///// ///////////////////////

    /**
     * The Constant VERTEX_DUPLICATE.
     */
    public static final String VERTEX_DUPLICATE = "duplicate vertex found";
    /**
     * The Constant VERTEX_NOT_FOUND.
     */
    public static final String VERTEX_NOT_FOUND = "vertex not found";
    /**
     * The Constant EDGE_VERTEX_NOT_FOUND.
     */
    public static final String EDGE_VERTEX_NOT_FOUND = "edge contains vertices that " + "have not been initilized";
    /**
     * The Constant EDGE_CANNOT_REMOVE.
     */
    public static final String EDGE_CANNOT_REMOVE = "edge can't be removed";
    /**
     * The Constant EDGE_CONTAINED_ALLREADY.
     */
    public static final String EDGE_CONTAINED_ALLREADY = "edge is allready contained";

    /**
     * The Constant NUMBER_FORMAT_ILLEGAL.
     */
    public static final String NUMBER_FORMAT_ILLEGAL = "not a number (format may be wrong or number might be to big)";

    /**
     * The Constant FILE_WRONG_FORMAT.
     */
    public static final String FILE_WRONG_FORMAT = "not formated correctly";

    /**
     * The Constant GRAPH_NOT_CONTINOUS.
     */
    public static final String GRAPH_NOT_CONTINOUS = "graph is not continous";
    /**
     * The Constant GRAPH_EDGE_LESS_THAN_ONE.
     */
    public static final String GRAPH_EDGE_LESS_THAN_ONE = "contains less than one edge";
    /**
     * The Constant GRAPH_VERTEX_LESS_THAN_TWO.
     */
    public static final String GRAPH_VERTEX_LESS_THAN_TWO = "contains less than two vertices";

    /**
     * The Constant PREFIX_ERROR.
     */
    public static final String PREFIX_ERROR = "Error,";

    /**
     * The Constant COMMAND_SUCCESSFUL.
     */
    public static final String COMMAND_SUCCESSFUL = "OK";
    /**
     * The Constant COMMAND_NOT_FOUND.
     */
    public static final String COMMAND_NOT_FOUND = "please use a valid command";

    /**
     * The Constant CODE_NOT_ACCESSIBLE.
     */
    public static final String CODE_NOT_ACCESSIBLE = "this code is not possible to run therefore you must be god";

    private Constant() {
    }
}
