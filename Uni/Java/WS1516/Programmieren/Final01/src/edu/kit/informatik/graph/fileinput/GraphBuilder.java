package edu.kit.informatik.graph.fileinput;

import java.util.ArrayList;
import java.util.Collection;

import edu.kit.informatik.Constant;
import edu.kit.informatik.FileInputHelper;
import edu.kit.informatik.exception.IllegalObjectException;
import edu.kit.informatik.exception.InvalidFileFormatException;
import edu.kit.informatik.graph.Edge;
import edu.kit.informatik.graph.Graph;
import edu.kit.informatik.graph.Vertex;

/**
 * The Class GraphBuilder.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public final class GraphBuilder {

    /**
     * Instantiates a new graph builder.
     */
    private GraphBuilder() {
    }

    /**
     * Reads a file and converts it to a graph.
     *
     * @param filePath
     *            the file path
     * @return the graph
     * @throws InvalidFileFormatException
     *             the invalid file format exception
     */
    public static Graph fileToGraph(final String filePath) throws InvalidFileFormatException {
        final String content = FileInputHelper.read(filePath);
        if (!content.matches(Constant.REGEX_GRAPH_FILE)) {
            exceptionInvalidFileFormat(Constant.FILE_WRONG_FORMAT);
        }
        final int sep = content.lastIndexOf("\n" + Constant.SEPARATOR + "\n");
        final String firstPart = content.substring(0, sep);
        // +2 to remove \n
        final String secondPart = content.substring(sep + Constant.SEPARATOR.length() + 2);

        final Collection<Vertex> vertices = firstPartToVertices(firstPart);
        final Collection<Edge> edges = secondPartToEdges(secondPart, vertices);

        for (final Vertex vertex : vertices) {
            boolean hasEdge = false;
            for (final Edge edge : edges) {
                if (edge.containsVertex(vertex)) {
                    hasEdge = true;
                }
            }
            if (!hasEdge) {
                exceptionInvalidFileFormat(Constant.GRAPH_NOT_CONTINOUS);
            }
        }

        final Graph graph = new Graph();
        for (final Edge edge : edges) {
            if (!(vertices.contains(edge.getFirst()) && vertices.contains(edge.getSecond()))) {
                exceptionInvalidFileFormat(Constant.EDGE_VERTEX_NOT_FOUND);
            }
        }
        try {
            graph.addAllEdges(edges);
        } catch (final IllegalObjectException e) {
            exceptionInvalidFileFormat(e.getMessage());
        }

        if (!graph.isContinous()) {
            exceptionInvalidFileFormat(Constant.GRAPH_NOT_CONTINOUS);
        }

        return graph;
    }

    /**
     * Converts the first part of the file to vertices.
     *
     * @param firstFilePart
     *            the first part of the file
     * @return the collection of vertices
     * @throws InvalidFileFormatException
     *             the invalid file format exception
     */
    private static Collection<Vertex> firstPartToVertices(final String firstFilePart)
            throws InvalidFileFormatException {
        final String[] lines = firstFilePart.split("\n");
        final Collection<Vertex> vertices = new ArrayList<>();

        for (final String str : lines) {
            final Vertex v = new Vertex(str);
            if (vertices.contains(v)) {
                exceptionInvalidFileFormat(Constant.VERTEX_DUPLICATE);
            } else {
                vertices.add(v);
            }
        }

        if (vertices.size() < 2) {
            exceptionInvalidFileFormat(Constant.GRAPH_VERTEX_LESS_THAN_TWO);
        }
        return vertices;
    }

    /**
     * Converts the second part of the file to edges.
     *
     * @param secondFilePart
     *            the second part of the file
     * @param vertices
     *            the vertices
     * @return the collection
     * @throws InvalidFileFormatException
     *             the invalid file format exception
     */
    private static Collection<Edge> secondPartToEdges(final String secondFilePart, final Collection<Vertex> vertices)
            throws InvalidFileFormatException {
        final Collection<Edge> edges = new ArrayList<>();
        final String[] lines = secondFilePart.split("\n");

        for (final String str : lines) {
            final String[] param = str.split(";");
            // Convert String to Vertices and int
            final Vertex v = new Vertex(param[0]);
            final Vertex w = new Vertex(param[1]);
            int distance = -1;
            int time = -1;
            try {
                distance = Integer.parseInt(param[2]);
                time = Integer.parseInt(param[3]);
                if (time <= 0 || distance <= 0) {
                    throw new InvalidFileFormatException();
                }
            } catch (final NumberFormatException e) {
                exceptionInvalidFileFormat(Constant.NUMBER_FORMAT_ILLEGAL);
            }

            if (!(vertices.contains(v) || vertices.contains(w))) {
                exceptionInvalidFileFormat(Constant.EDGE_VERTEX_NOT_FOUND);
            }

            final Edge e = new Edge(v, w, distance, time);
            if (edges.contains(e)) {
                exceptionInvalidFileFormat(Constant.VERTEX_DUPLICATE);
            }
            edges.add(e);
        }

        if (edges.size() < 1) {
            exceptionInvalidFileFormat(Constant.GRAPH_EDGE_LESS_THAN_ONE);
        }
        return edges;
    }

    private static void exceptionInvalidFileFormat(final String message) throws InvalidFileFormatException {
        throw new InvalidFileFormatException("while reading file: " + message);
    }
}
