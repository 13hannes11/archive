package edu.kit.informatik;

import edu.kit.informatik.exception.InvalidFileFormatException;
import edu.kit.informatik.graph.fileinput.GraphBuilder;
import edu.kit.informatik.terminalinput.InputManager;

/**
 * The Class Main.
 *
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public final class Main {
    private Main() {
    }

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     */
    public static void main(final String[] args) {
        if (args.length != 1) {
            InputManager.error("program has to be started with one parameter (filePath)");
            System.exit(1);
        }
        final InputManager inputManager;
        try {
            inputManager = new InputManager(GraphBuilder.fileToGraph(args[0]));
            inputManager.run();
        } catch (final InvalidFileFormatException e) {
            InputManager.error(e.getMessage());
            System.exit(1);
        }

    }

}
