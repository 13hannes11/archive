package edu.kit.informatik.terminalinput;

import edu.kit.informatik.Constant;
import edu.kit.informatik.Terminal;
import edu.kit.informatik.graph.Graph;

/**
 * The Class VerticesCommand.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class VerticesCommand extends Command {

    /**
     * Instantiates a new vertices command.
     */
    public VerticesCommand() {
        super("vertices", "vertices", 0);
    }

    @Override
    public void execute(final String command, final Graph graph) {
        Terminal.printLine(graph.toString().split("\n" + Constant.SEPARATOR + "\n")[0].trim());
    }
}
