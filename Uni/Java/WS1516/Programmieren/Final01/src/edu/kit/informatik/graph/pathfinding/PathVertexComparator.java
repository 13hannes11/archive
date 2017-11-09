package edu.kit.informatik.graph.pathfinding;

import java.util.Comparator;

import edu.kit.informatik.graph.Edge;

/**
 * The Class VertexComparator.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public abstract class PathVertexComparator implements Comparator<PathVertex> {
    @Override
    public abstract int compare(final PathVertex first, final PathVertex second);

    /**
     * Gets the score of a pathVertex
     *
     * @param v
     *            the v
     * @return the score
     */
    public abstract int getScore(PathVertex v);

    /**
     * Sets the score of a path vertex.
     *
     * @param v
     *            the path vertex
     * @param score
     *            the score
     */
    public abstract void setScore(PathVertex v, int score);

    /**
     * Gets the score of an edge, what the score actually means might differ in
     * subclasses.
     *
     * @param e
     *            the e
     * @return the edge score
     */
    public abstract int getEdgeScore(Edge e);

}
