package edu.kit.informatik.graph.pathfinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.kit.informatik.graph.Edge;
import edu.kit.informatik.graph.Vertex;

/**
 * The Class PathVertex.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class PathVertex {

    /** The data. */
    private final Vertex data;

    /** The previous. */
    private PathVertex previous;

    private int time;
    private int distance;

    /**
     * Instantiates a new path vertex.
     *
     * @param v
     *            the v
     */
    public PathVertex(final Vertex v) {
        time = 0;
        distance = 0;
        data = v;
        previous = null;
    }

    /**
     * Sets the previous.
     *
     * @param previous
     *            the new previous
     */
    public void setPrevious(final PathVertex previous) {
        this.previous = previous;
    }

    /**
     * Gets the previous.
     *
     * @return the previous
     */
    public PathVertex getPrevious() {
        return previous;
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    public Vertex getData() {
        return data;
    }

    /**
     * Gets the neighbours of a pathVertex
     *
     * @param vertices
     *            the vertices
     * @param edges
     *            the edges
     * @return the neighbours
     */
    public Collection<PathVertex> getNeighbours(final List<PathVertex> vertices, final Collection<Edge> edges) {
        final Collection<PathVertex> neighbours = new ArrayList<>();
        for (final Edge edge : edges) {
            for (final PathVertex vertex : vertices) {
                if (edge.containsVertex(vertex.data) && !neighbours.contains(vertex)) {
                    neighbours.add(vertex);
                }
            }
        }
        return neighbours;
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
     * Gets the optimal score.
     *
     * @return the optimal score
     */
    public int getOptimalScore() {
        return (distance * distance) + (time * time);
    }

    /**
     * Sets the distance.
     *
     * @param distance
     *            the new distance
     */
    public void setDistance(final int distance) {
        this.distance = distance;
    }

    /**
     * Sets the time.
     *
     * @param time
     *            the new time
     */
    public void setTime(final int time) {
        this.time = time;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String str = data.toString();
        if (previous != null) {
            str += " " + previous.toString();
        }

        return str;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!obj.getClass().equals(this.getClass())) {
            return false;
        } else {
            return data.equals(((PathVertex) obj).data);
        }
    }

    @Override
    protected PathVertex clone() {
        final PathVertex tmpPathVertex = new PathVertex(data);
        tmpPathVertex.setPrevious(this.previous);
        tmpPathVertex.setDistance(this.distance);
        tmpPathVertex.setTime(this.time);
        return tmpPathVertex;
    }

}
