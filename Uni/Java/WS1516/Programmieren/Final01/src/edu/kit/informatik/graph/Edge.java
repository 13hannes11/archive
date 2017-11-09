package edu.kit.informatik.graph;

/**
 * The Class Edge.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class Edge implements Comparable<Edge> {

    /** The distance in kilometers. */
    private final int distance;

    /** The time in minutes. */
    private final int time;

    /** The first. */
    private final Vertex first;

    /** The second. */
    private final Vertex second;

    /**
     * Instantiates a new edge.
     *
     * @param v
     *            the first vertex the edge should be connecting
     * @param w
     *            the second vertex the edge should be connecting
     * @param distance
     *            the distance
     * @param time
     *            the time
     */
    public Edge(final Vertex v, final Vertex w, final int distance, final int time) {
        this.first = v;
        this.second = w;
        this.distance = distance;
        this.time = time;
    }

    /**
     * Gets the distance.
     *
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Gets the time.
     *
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * Checks if two objects are the same.
     * 
     * @param obj
     *            the object this should be compared against
     * @return true, if obj is edge that contains the same vertices (ignores
     *         time and distance)
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj != null && obj.getClass().equals(Edge.class)) {
            if (((Edge) obj).getFirst().equals(this.first) && ((Edge) obj).getSecond().equals(this.second)) {
                return true;
            } else if (((Edge) obj).getFirst().equals(this.second) && ((Edge) obj).getSecond().equals(this.first)) {
                return true;
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return first.toString() + ";" + second.toString() + ";" + distance + ";" + time;
    }

    /**
     * Checks if edge contains vertex.
     *
     * @param v
     *            the v
     * @return true, if successful
     */
    public boolean containsVertex(final Vertex v) {
        return (v.equals(first) || v.equals(second));
    }

    /**
     * Gets the other vertex of the edge. Return null if v is not contained.
     *
     * @param v
     *            the v
     * @return the other vertex
     */
    public Vertex getOtherVertex(final Vertex v) {
        if (v.equals(first)) {
            return second;
        } else if (v.equals(second)) {
            return first;
        } else {
            return null;
        }
    }

    /**
     * Gets the first.
     *
     * @return the first
     */
    public Vertex getFirst() {
        return first;
    }

    /**
     * Gets the second.
     *
     * @return the second
     */
    public Vertex getSecond() {
        return second;
    }

    /**
     * Builds a sorted string which consist of first.toString and
     * second.toString. The string that is smaller (in terms of compareTo) will
     * ne placed at the beginning.
     *
     * @return the string
     */
    private String buildSortedString() {
        if (first.compareTo(second) < 0) {
            return first.toString() + second.toString();
        } else {
            return second.toString() + first.toString();
        }
    }

    @Override
    public int compareTo(final Edge o) {
        if (this.equals(o)) {
            return 0;
        }
        return buildSortedString().compareTo(o.buildSortedString());

    }
}
