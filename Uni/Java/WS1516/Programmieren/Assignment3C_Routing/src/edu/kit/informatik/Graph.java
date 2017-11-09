package edu.kit.informatik;

/**
 * The Class Graph.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class Graph {

    /** The edges. */
    private Edge[]   edges;

    /** The vertices. */
    private Vertex[] vertices;

    /**
     * Instantiates a new graph.
     */
    public Graph() {
        this.edges = null;
        this.vertices = null;
    }

    /**
     * Instantiates a new graph. (Adds all vertices of the edges to the vertices
     * array)
     *
     * @param edges
     *            the edges of the graph
     */
    public Graph(final Edge[] edges) {
        this.edges = edges;
        this.vertices = null;
        if (this.edges != null) {
            for (final Edge edge : this.edges) {
                if (!this.contains(edge.getFromVertex()))
                    this.add(edge.getFromVertex());
                if (!this.contains(edge.getToVertex()))
                    this.add(edge.getToVertex());
                if (!this.contains(edge)) {
                    this.add(edge);
                }
            }
        }
    }

    /**
     * Adds an edge to the graph and the vertices it contains.
     *
     * @param edge
     *            the edge
     */
    public void add(final Edge edge) {
        if (edge == null)
            return;

        if (edges == null)
            edges = new Edge[1];
        else {
            final Edge[] tmpEdge = edges.clone();
            edges = new Edge[tmpEdge.length + 1];
            System.arraycopy(tmpEdge, 0, edges, 0, tmpEdge.length);
        }
        if (!this.contains(edge)) {
            edges[edges.length - 1] = edge;
            this.add(edge.getFromVertex());
            this.add(edge.getToVertex());
        }
    }

    /**
     * Adds a vertex to the graph.
     *
     * @param vertex
     *            the vertex
     */
    public void add(final Vertex vertex) {
        if (vertex == null)
            return;

        if (vertices == null) {
            vertices = new Vertex[1];
            vertices[0] = vertex;
        } else {
            if (!this.contains(vertex)) {
                final Vertex[] tmpVertex = vertices.clone();
                vertices = new Vertex[tmpVertex.length + 1];
                System.arraycopy(tmpVertex, 0, vertices, 0, tmpVertex.length);
                vertices[vertices.length - 1] = vertex;
            }
        }

    }

    /**
     * Gets the number of connections.
     *
     * @param i
     *            the id of the vertex the route starts at
     * @param j
     *            the id of the vertex the route ends at
     * @param pathlength
     *            the path length of the route
     * @return the number of connections
     */
    public int getNumberOfConnections(final int i, final int j, final int pathlength) {
        if (pathlength < 1 || vertices == null || i >= vertices.length || j >= vertices.length)
            return 0;
        Matrix resultMatrix = this.toMatrix();
        for (int k = 1; k < pathlength; k++) {
            resultMatrix = Matrix.crossProduct(resultMatrix, resultMatrix);
        }
        return resultMatrix.getSingleElement(i, j);
    }

    /**
     * Checks if this graph contains the edge.
     *
     * @param edge
     *            the edge
     * @return true, if contained
     */
    public boolean contains(final Edge edge) {
        if (edges != null) {
            for (final Edge e : edges) {
                if (e != null && e.equals(edge))
                    return true;
            }
        }
        return false;
    }

    /**
     * Checks if this graph contains the vertex.
     *
     * @param vertex
     *            the vertex
     * @return true, if contained
     */
    public boolean contains(final Vertex vertex) {
        if (vertices != null) {
            for (final Vertex v : vertices) {
                if (v != null && v.equals(vertex))
                    return true;
            }
        }
        return false;
    }

    /**
     * Converts the graph to an adjacency matrix.
     *
     * @return the adjacency matrix
     */
    public Matrix toMatrix() {
        if (vertices == null || edges == null)
            return null;
        final Matrix result = new Matrix(vertices.length, vertices.length);
        for (final Edge edge : edges) {
            if (edge != null)
                result.setSingleElement(edge.getFromVertex().getId(), edge.getToVertex().getId(), 1);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String out = "E = { ";
        if (edges != null) {
            for (final Edge edge : this.edges) {
                out += edge.toString() + " ";
            }
        }
        out += "}\nV = { ";
        if (vertices != null) {
            for (final Vertex vertex : vertices) {
                out += vertex.toString() + " ";
            }
        }
        out += "}";
        return out;
    }
}
