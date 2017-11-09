package edu.kit.informatik.graph.pathfinding;

import edu.kit.informatik.graph.Edge;

/**
 * The Class PathVertexDistanceComparator.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class PathVertexTimeComparator extends PathVertexComparator {

    /**
     * Instantiates a new path vertex time comparator.
     */
    public PathVertexTimeComparator() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.kit.informatik.graph.PathVertexComparator#getEdgeScore(edu.kit.
     * informatik.graph.Edge)
     */
    @Override
    public int getEdgeScore(final Edge e) {
        return e.getTime();
    }

    @Override
    public int compare(final PathVertex first, final PathVertex second) {
        if (first.equals(second)) {
            return 0;
        } else if (first.getTime() == -1) {
            // first is greater than second
            return 1;
        } else if (second.getTime() == -1) {
            // first is less than second
            return -1;
        }

        return first.getTime() - second.getTime();
    }

    @Override
    public int getScore(final PathVertex v) {
        return v.getTime();
    }

    @Override
    public void setScore(final PathVertex v, final int score) {
        v.setTime(score);
    }

}
