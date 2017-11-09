package edu.kit.informatik.terminalinput;

import java.util.LinkedList;
import java.util.List;

import edu.kit.informatik.Constant;
import edu.kit.informatik.Terminal;
import edu.kit.informatik.graph.Graph;

/**
 * The Class InputManager.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class InputManager {

    /** The commands. */
    private List<Command> commands;

    /** The graph. */
    private final Graph graph;

    /** The quit. */
    private boolean quit;

    /**
     * Instantiates a new input manager.
     *
     * @param g
     *            the graph
     */
    public InputManager(final Graph g) {
        this.graph = g;
        initializeCommands();
        quit = false;
    }

    /**
     * Initialize all commands.
     */
    private void initializeCommands() {
        commands = new LinkedList<>();
        commands.add(new QuitCommand());
        commands.add(new InfoCommand());
        commands.add(new VerticesCommand());
        commands.add(new NodesCommand());
        commands.add(new RemoveCommand());
        commands.add(new InsertCommand());
        commands.add(new RouteCommand());
        commands.add(new SearchCommand());

    }

    /**
     * Run the input manager.
     */
    public void run() {
        while (!quit) {
            boolean commandExcecuted = false;
            final String input = Terminal.readLine();
            for (final Command command : commands) {
                if (command.validate(input)) {
                    command.execute(input, graph);
                    if (command.checkQuit()) {
                        quit = true;
                    }
                    commandExcecuted = true;
                }
            }
            if (!commandExcecuted) {
                error(Constant.COMMAND_NOT_FOUND + " (valid commands: " + commandsInfo()
                        + " [numbers can't be zero or less])");
            }
        }
    }

    /**
     * Prints an error.
     * 
     * @param message
     *            the message
     */
    public static void error(final String message) {
        Terminal.printLine(Constant.PREFIX_ERROR + " " + message);
    }

    /**
     * Prints the success command output.
     */
    public static void printSuccess() {
        Terminal.printLine(Constant.COMMAND_SUCCESSFUL);
    }

    private String commandsInfo() {
        String out = "";
        for (final Command command : commands) {
            out += "'" + command.getInfo() + "' ";
        }
        return out;
    }
}
