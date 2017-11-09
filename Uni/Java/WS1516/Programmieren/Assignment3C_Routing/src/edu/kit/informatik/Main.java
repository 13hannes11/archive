package edu.kit.informatik;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The Class Main.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public final class Main {

    /** The Constant FILENOTFOUND for fileNotFound exceptions. */
    private static final String FILENOTFOUND = "File not Found";

    /** The Constant IO for IO exceptions. */
    private static final String IO = "IOException occured";

    /**
     * Instantiates a new main.
     */
    private Main() {
    }

    /**
     * Checks if 4 arguments are handed over and calculates the number of
     * connection between two nodes (args[1] and args[2]) at the pathlength
     * (args[3]) and prints them to the console.
     *
     * @param args
     *            the arguments
     */
    public static void main(final String[] args) {
        if (args.length == 4) {
            final String filePath = args[0];
            final int startVertexId = Integer.parseInt(args[1]);
            final int endVertexId = Integer.parseInt(args[2]);
            final int pathLength = Integer.parseInt(args[3]);

            final Graph graph = Main.readFile(filePath);
            System.out.println(graph.getNumberOfConnections(startVertexId, endVertexId, pathLength));
        }
    }

    /**
     * Reads a file that contains the description for a graph.
     *
     * @param path
     *            the path of the file
     * @return the graph
     */
    private static Graph readFile(final String path) {
        FileReader in = null;

        try {
            in = new FileReader(path);
        } catch (final FileNotFoundException e) {
            System.out.println(FILENOTFOUND);
            System.exit(1);
        }

        final BufferedReader reader = new BufferedReader(in);
        try {
            final Graph graph = new Graph();
            String line = reader.readLine();
            while (line != null) {
                final String[] nums = line.trim().split("\\s+");
                if (nums.length == 2)
                    graph.add(new Edge(new Vertex(Integer.parseInt(nums[0])), new Vertex(Integer.parseInt(nums[1]))));
                line = reader.readLine();
            }
            return graph;
        } catch (final IOException e) {
            System.out.println(IO);
            System.exit(1);
        }

        return null;

    }
}
