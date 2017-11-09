package edu.kit.informatik;

/**
 * The Class Vertex.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class Vertex {

    /** The id. */
    private int id;

    /**
     * Instantiates a new vertex.
     *
     * @param id
     *            the id of the vertex
     */
    public Vertex(int id) {
        this.id = id;
    }

    /**
     * Gets the id of the vertex
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "" + id + "";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof Vertex) && (((Vertex) obj).id == this.id))
            return true;
        else
            return false;
    }
}
