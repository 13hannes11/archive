/*
 * @author Hannes Kuchelmeister
 * 
 * @version 1.0
 */
package edu.kit.informatik.graph.pathfinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import edu.kit.informatik.graph.Edge;
import edu.kit.informatik.graph.Vertex;

/**
 * The Class GraphPathFinder will find a path between two nodes in a graph
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class GraphPathFinder {

    /** The graph. */
    private final List<Edge> graph;

    /**
     * Instantiates a new graph path finder.
     *
     * @param edges
     *            all edges of a graph
     */
    public GraphPathFinder(final List<Edge> edges) {
        graph = edges;
    }

    /**
     * Gets the path with Dijkstra algorithm.
     *
     * @param start
     *            the start vertex
     * @param destination
     *            the destination vertex
     * @param comparator
     *            the comparator which compares vertices
     * @return the path
     */
    public PathVertex getPathReversedDijkstra(final Vertex start, final Vertex destination,
            final PathVertexComparator comparator) {
        final PathVertex s = new PathVertex(start);

        // Intialize Get a list of all vertices
        final List<PathVertex> vertices = new ArrayList<>();
        final List<PathVertex> toVisit = new LinkedList<>();
        //foreach edge add all vertices to toVisit, vertices and set they score to -1 which resembles infinity
        for (final Edge edge : graph) {

            final PathVertex first = new PathVertex(edge.getFirst());
            if (!first.equals(s)) {
                comparator.setScore(first, -1);
            }
            if (!vertices.contains(first)) {
                vertices.add(first);
            }
            if (!toVisit.contains(first)) {
                toVisit.add(first);
            }

            final PathVertex second = new PathVertex(edge.getSecond());
            if (!second.equals(s)) {
                comparator.setScore(second, -1);
            }
            if (!vertices.contains(second)) {
                vertices.add(second);
            }
            if (!toVisit.contains(second)) {
                toVisit.add(second);
            }
        }
        // Real magic happens here (pathfinding)
        while (toVisit.size() > 1) {
            toVisit.sort(comparator);
            PathVertex v = toVisit.get(0);

            toVisit.remove(toVisit.indexOf(v));
            for (final PathVertex u : v.getNeighbours(vertices, graph)) {
                if (toVisit.contains(u)) {
                    // Get connecting edge
                    final int index = graph.indexOf(new Edge(v.getData(), u.getData(), 10, 10));
                    if (index >= 0) {
                        final Edge e = graph.get(index);
                        // new Distance
                        final int dist = comparator.getScore(v) + comparator.getEdgeScore(e);
                        if (dist < comparator.getScore(u) || comparator.getScore(u) == -1) {
                            comparator.setScore(u, dist);
                            u.setPrevious(v);
                        }
                    }
                }
            }
        }
        return vertices.get(vertices.indexOf(new PathVertex(destination)));
    }

    /**
     * Gets all paths.
     *
     * @param vertex
     *            the vertex
     * @param searchFor
     *            the search for
     * @return the all paths
     */
    public Collection<PathVertex> getAllPaths(final Vertex vertex, final Vertex searchFor) {
        return depthFirstSearch(vertex, searchFor, new ArrayList<>());
    }

    /**
     * Gets the path depth first search.
     *
     * @param vertex
     *            the vertex
     * @param searchFor
     *            the search for
     * @return the best path (pathScore calculated by distance*distance +
     *         time*time)
     */
    public PathVertex getPathDepthFirstSearch(final Vertex vertex, final Vertex searchFor) {
        final Collection<PathVertex> pathVertices = depthFirstSearch(vertex, searchFor, new ArrayList<>());

        // find best path
        PathVertex path = null;
        for (final PathVertex v : pathVertices) {
            if (path == null || path.getOptimalScore() > v.getOptimalScore()) {
                path = v;
            }
        }
        return path;
    }

    /**
     * recursive search for paths from <code>vertex</code> to
     * <code>searchFor</code>
     *
     * @param vertex
     *            the vertex
     * @param searchFor
     *            the search for
     * @param lookedAt
     *            the looked at
     * @return the collection
     */
    private Collection<PathVertex> depthFirstSearch(final Vertex vertex, final Vertex searchFor,
            final ArrayList<Vertex> lookedAt) {
        // Abbort conditions
        if (vertex.equals(searchFor)) {
            final PathVertex tmp = new PathVertex(vertex);
            final Collection<PathVertex> collection = new ArrayList<>();
            collection.add(tmp);
            return collection;
        }
        if (lookedAt.contains(vertex)) {
            return null;
        }
        lookedAt.add(vertex);

        final Collection<Edge> neighbours = vertex.getEdges(graph);

        final PathVertex path = new PathVertex(vertex);
        final Collection<PathVertex> retPathCollection = new ArrayList<>();

        path.setDistance(0);
        path.setTime(0);
        for (final Edge edge : neighbours) {
            // recursive search for all paths
            final Collection<PathVertex> tmpPathCollection = depthFirstSearch(edge.getOtherVertex(vertex), searchFor,
                    /*(ArrayList<Vertex>) lookedAt.clone()*/ new ArrayList<>(lookedAt));
            // connect <code>vertex</code> to all found paths
            if (tmpPathCollection != null) {
                for (final PathVertex pathVertex : tmpPathCollection) {
                    if (pathVertex != null) {
                        PathVertex tmpPath;
                        tmpPath = (path.clone());
                        if (tmpPath != null) {
                            tmpPath.setPrevious(pathVertex);
                            // calc new pathscore
                            tmpPath.setDistance(pathVertex.getDistance() + edgeDistance(pathVertex.getData(), vertex));
                            tmpPath.setTime(pathVertex.getTime() + edgeTime(pathVertex.getData(), vertex));
                            retPathCollection.add(tmpPath);
                        }
                    }
                }
            }
        }

        return retPathCollection;

    }

    private int edgeDistance(final Vertex v, final Vertex w) {
        for (final Edge edge : graph) {
            if (edge.containsVertex(v) && edge.containsVertex(w)) {
                return edge.getDistance();
            }
        }
        return 0;
    }

    private int edgeTime(final Vertex v, final Vertex w) {
        for (final Edge edge : graph) {
            if (edge.containsVertex(v) && edge.containsVertex(w)) {
                return edge.getTime();
            }
        }
        return 0;
    }
}
