package edu.kit.informatik.graph;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The Class Vertex.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class Vertex implements Comparable<Vertex> {

    /** The identifier of a Vertex. */
    private final String identifier;

    /**
     * Instantiates a new vertex.
     *
     * @param identifier
     *            the identifier
     */
    public Vertex(final String identifier) {
        this.identifier = identifier;
    }

    /**
     * Gets the edges that contain this vertex from a list of edges
     *
     * @param edges
     *            the edges
     * @return the edges
     */
    public Collection<Edge> getEdges(final Collection<Edge> edges) {
        final Collection<Edge> col = new ArrayList<>();
        for (final Edge edge : edges) {
            if (edge.containsVertex(this)) {
                col.add(edge);
            }
        }
        return col;
    }

    /**
     * Gets the identifier.
     *
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Checks if two objects are the same.
     * 
     * @param obj
     *            the object this should be compared against
     * @return true, if obj is Vertex and if identifiers are the same
     */
    @Override
    public boolean equals(final Object obj) {
        return obj.getClass().equals(Vertex.class) && ((Vertex) obj).identifier.equalsIgnoreCase(this.identifier);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return identifier;
    }

    @Override
    public int compareTo(final Vertex o) {
        return this.identifier.compareToIgnoreCase(o.identifier);
    }
}
