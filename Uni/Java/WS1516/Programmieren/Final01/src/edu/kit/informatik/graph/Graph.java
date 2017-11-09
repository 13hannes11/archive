package edu.kit.informatik.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

import edu.kit.informatik.Constant;
import edu.kit.informatik.exception.IllegalObjectException;

/**
 * The Class Graph.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class Graph {

    /** The empty graph continious. */
    public static final boolean EMPTY_GRAPH_CONTINIOUS = true;

    /** The vertices. */
    private final List<Edge> edges;

    /**
     * Instantiates a new graph.
     */
    public Graph() {
        edges = new ArrayList<>();
    }

    /**
     * Adds an edge to the graph but does not allow duplicates.
     *
     * @param edge
     *            the edge
     * @throws IllegalObjectException
     *             the illegal object exception
     */
    public void addEdge(final Edge edge) throws IllegalObjectException {
        if (edge.getFirst().equals(edge.getSecond())) {
            throw new IllegalObjectException(Constant.VERTEX_DUPLICATE);
        }
        if (edges.contains(edge)) {
            throw new IllegalObjectException(Constant.EDGE_CONTAINED_ALLREADY);
        }
        edges.add(edge);
    }

    /**
     * Checks if graph contains a vertex
     *
     * @param v
     *            the v
     * @return true, if successful
     */
    public boolean contains(final Vertex v) {
        for (final Edge edge : edges) {
            if (edge.containsVertex(v)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the all edge to the graph but does not allow duplicates. Requires at
     * lest one vertex of each edge to be part of the graph.
     *
     * @param collection
     *            the collection
     * @throws IllegalObjectException
     *             the illegal object exception
     */
    public void addAllEdges(final Collection<Edge> collection) throws IllegalObjectException {
        for (final Edge edge : collection) {
            addEdge(edge);
        }
    }

    /**
     * Checks if graph is continous.
     *
     * @return true, if graph is continous
     */
    public boolean isContinous() {
        if (edges.size() == 0) {
            return EMPTY_GRAPH_CONTINIOUS;
        }
        final Stack<Vertex> queue = new Stack<>();
        final List<Edge> notUsedEdges = new ArrayList<>(this.edges);
        final List<Vertex> notUsedVertices = new ArrayList<>(getAllVertices());
        queue.add(edges.get(0).getFirst());
        notUsedVertices.remove(notUsedVertices.indexOf(edges.get(0).getFirst()));

        final List<Edge> used = new ArrayList<>();
        Vertex v = null;
        while (notUsedVertices.size() > 0 && !queue.isEmpty()) {
            v = queue.pop();
            if (notUsedVertices.contains(v)) {
                notUsedVertices.remove(notUsedVertices.indexOf(v));
            }
            for (final Edge edge : notUsedEdges) {
                final Vertex other = edge.getOtherVertex(v);
                if (!used.contains(edge) && other != null) {
                    queue.push(other);
                    used.add(edge);
                }
            }
            notUsedEdges.removeAll(used);
        }
        return (notUsedVertices.size() == 0);
    }

    /**
     * Checks if graph contains edge;
     *
     * @param e
     *            the e
     * @return true, if successful
     */
    public boolean contains(final Edge e) {
        return edges.contains(e);
    }

    private Collection<Vertex> getNeightbours(final Vertex v) throws IllegalObjectException {
        final Collection<Vertex> tmp = new LinkedList<>();
        for (final Edge edge : edges) {
            if (edge.containsVertex(v)) {
                tmp.add(edge.getOtherVertex(v));
            }
        }
        if (tmp.size() == 0) {
            throw new IllegalObjectException(Constant.VERTEX_NOT_FOUND);
        }
        return tmp;
    }

    /**
     * Gets the reference of the vertex contained in the graph that is equal to
     * vertex v but might not have the same reference.
     *
     * @param v
     *            the v
     * @return the vertex
     */
    public Vertex getVertex(final Vertex v) {
        for (final Edge edge : edges) {
            if (v.equals(edge.getFirst())) {
                return edge.getFirst();
            } else if (v.equals(edge.getSecond())) {
                return edge.getSecond();
            }
        }
        return null;
    }

    /**
     * Gets all neigbouring Vertices and returns them as a string. Each vertex
     * is in its own line.
     *
     * @param v
     *            the v
     * @return the string
     * @throws IllegalObjectException
     *             the illegal object exception
     */
    public String neighboursToString(final Vertex v) throws IllegalObjectException {
        String ret = "";
        for (final Vertex vertex : getNeightbours(v)) {
            ret += vertex.toString() + "\n";
        }
        return ret.trim();
    }

    /**
     * Removes the edge and if vertex therefore has no adjacent edges it will be
     * removed too.
     *
     * @param edge
     *            the edge to remove
     * @return true, if successful
     */
    public boolean removeEdge(final Edge edge) {
        if (!edges.contains(edge)) {
            return false;
        }
        // To have the correct reference
        final Edge tmp = edges.get(edges.indexOf(edge));
        edges.remove(tmp);

        if (!isContinous()) {
            // Undo changes
            edges.add(tmp);
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        if (edges.size() == 0) {
            return "";
        }
        final Collection<Vertex> vertexSet = new TreeSet<>();

        String firstSection = "";
        final String seperator = "--";
        String secondSection = "";
        for (final Edge edge : edges) {
            vertexSet.add(edge.getFirst());
            vertexSet.add(edge.getSecond());
            secondSection += "\n" + edge.toString();
        }
        for (final Vertex vertex : vertexSet) {
            firstSection += vertex.toString() + "\n";
        }
        return (firstSection + seperator + secondSection).trim();
    }

    private List<Vertex> getAllVertices() {
        final List<Vertex> list = new ArrayList<>();
        for (final Edge edge : edges) {
            final Vertex first = edge.getFirst();
            final Vertex second = edge.getSecond();
            if (!list.contains(first)) {
                list.add(first);
            }
            if (!list.contains(second)) {
                list.add(second);
            }
        }
        return list;
    }

    /**
     * Gets the edges.
     *
     * @return the edges
     */
    public List<Edge> getEdges() {
        return edges;
    }
}
