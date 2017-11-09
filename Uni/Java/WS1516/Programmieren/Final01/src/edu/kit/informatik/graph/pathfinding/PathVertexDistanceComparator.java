package edu.kit.informatik.graph.pathfinding;

import edu.kit.informatik.graph.Edge;

/**
 * The Class PathVertexDistanceComparator.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class PathVertexDistanceComparator extends PathVertexComparator {

    /**
     * Instantiates a new path vertex distance comparator.
     */
    public PathVertexDistanceComparator() {
    }

    /**
     * Gets the edge score.
     *
     * @param e
     *            the e
     * @return the edge score
     */
    @Override
    public int getEdgeScore(final Edge e) {
        return e.getDistance();
    }

    @Override
    public int compare(final PathVertex first, final PathVertex second) {
        if (first.equals(second)) {
            return 0;
        } else if (first.getDistance() == -1) {
            // first is greater than second
            return 1;
        } else if (second.getDistance() == -1) {
            // first is less than second
            return -1;
        }

        return first.getDistance() - second.getDistance();
    }

    @Override
    public int getScore(final PathVertex v) {
        return v.getDistance();
    }

    @Override
    public void setScore(final PathVertex v, final int score) {
        v.setDistance(score);
    }
}
