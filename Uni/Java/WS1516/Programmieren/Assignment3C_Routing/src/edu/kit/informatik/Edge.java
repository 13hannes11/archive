package edu.kit.informatik;

/**
 * The Class Edge.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class Edge {

    /** The vertex the edge begins at. */
    private final Vertex fromVertex;

    /** The vertex the edge ends at. */
    private final Vertex toVertex;

    /**
     * Instantiates a new edge.
     *
     * @param from
     *            the vertex the edge begins at
     * @param to
     *            the vertex the edge ends at
     */
    public Edge(final Vertex from, final Vertex to) {
        this.fromVertex = from;
        this.toVertex = to;
    }

    /**
     * Gets vertex the edge begins at.
     *
     * @return the vertex the edge begins at
     */
    public Vertex getFromVertex() {
        return fromVertex;
    }

    /**
     * Gets the vertex the edge ends at.
     *
     * @return the vertex the edge ends at
     */
    public Vertex getToVertex() {
        return toVertex;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "(" + fromVertex.toString() + " , " + toVertex.toString() + ")";
    }
}
