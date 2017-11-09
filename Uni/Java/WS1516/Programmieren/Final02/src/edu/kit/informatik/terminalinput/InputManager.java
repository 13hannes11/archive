package edu.kit.informatik.terminalinput;

import java.util.LinkedList;
import java.util.List;

import edu.kit.informatik.Constant;
import edu.kit.informatik.Terminal;
import edu.kit.informatik.game.BoardGame;

/**
 * The Class InputManager.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class InputManager {

    /** The commands. */
    private List<Command> commands;

    /** The boardGame. */
    private final BoardGame game;

    /** The quit. */
    private boolean quit;

    /**
     * Instantiates a new input manager.
     *
     * @param g
     *            the graph
     */
    public InputManager(final BoardGame g) {
        this.game = g;
        initializeCommands();
        quit = false;
    }

    /**
     * Initialize all commands.
     */
    private void initializeCommands() {
        commands = new LinkedList<>();
        commands.add(new SelectCommand());
        commands.add(new BagCommand());
        commands.add(new ColprintCommand());
        commands.add(new RowprintCommand());
        commands.add(new PlaceCommand());
        commands.add(new QuitCommand());
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
                    command.execute(input, game);
                    if (command.checkQuit()) {
                        quit = true;
                    }
                    commandExcecuted = true;
                }
            }
            if (!commandExcecuted) {
                error(Constant.COMMAND_NOT_FOUND + " (valid commands: " + commandsInfo());
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
