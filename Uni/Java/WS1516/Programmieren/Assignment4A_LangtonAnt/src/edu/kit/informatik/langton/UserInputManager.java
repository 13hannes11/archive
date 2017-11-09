package edu.kit.informatik.langton;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.exceptions.GameHasEndedException;
import edu.kit.informatik.exceptions.InvalidParameterException;

/**
 * The Class UserInputManager.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class UserInputManager {

    /** The board. */
    private final Board board;

    /** The boolean ready to quit used to store if game should end. */
    private boolean readyToQuit;

    /**
     * Instantiates a new user input manager.
     *
     * @param board
     *            the board
     */
    public UserInputManager(final Board board) {
        this.board = board;
        readyToQuit = false;
    }

    /**
     * Converts commands to actions that manipulates the Board.
     *
     * @param command
     *            the command as string
     */
    public void doCommand(final String command) {
        final String[] arr = command.split("[\\s,\\,]+");
        if (arr != null && arr[0] != null) {
            try {
                switch (arr[0]) {
                    case "move":
                        if (arr.length != 2) {
                            throw new InvalidParameterException("move expects 1 parameter");
                        }
                        final int turnNumber = Integer.parseInt(arr[1]);
                        for (int i = 0; i < turnNumber; i++) {
                            board.makeTurn();
                        }
                        break;
                    case "print":
                        if (arr.length != 1) {
                            throw new InvalidParameterException("print expects 0 parameter");
                        }
                        Terminal.printLine(board.toString());
                        break;
                    case "position":
                        if (arr.length != 1) {
                            throw new InvalidParameterException("position expects 0 parameters");
                        }
                        Terminal.printLine(board.getAnt().toString());
                        break;
                    case "field":
                        if (arr.length != 3) {
                            throw new InvalidParameterException("field expects 2 parameters");
                        }
                        final int column = Integer.parseInt(arr[1]);
                        final int row = Integer.parseInt(arr[2]);

                        try {
                            Terminal.printLine(board.getFieldAsString(row, column));
                        } catch (final edu.kit.informatik.exceptions.InvalidParameterException e) {
                            throw new InvalidParameterException(
                                    "coordinates are outside the board they have to be from 0 to "
                                            + (board.getHeight() - 1) + " for the first parameter and from 0 to "
                                            + (board.getWidth() - 1) + " for the second parameter");
                        }
                        break;
                    case "direction":
                        if (arr.length != 1) {
                            throw new InvalidParameterException("print expects 0 parameters");
                        }
                        Terminal.printLine(board.getAnt().getDirectionAsString());
                        break;
                    case "quit":
                        if (arr.length != 1) {
                            throw new InvalidParameterException("quit expects 0 parameter");
                        }
                        readyToQuit = true;
                        break;
                    default:
                        throw new InvalidParameterException(
                                "Invalid command. Commands are: 'quit', 'direction', 'field <i> <j>', "
                                        + "'position', 'print', 'move <numberOfTurns>'");
                }
            } catch (final InvalidParameterException e) {
                Terminal.printLine("Error: " + e.getMessage());
            } catch (final GameHasEndedException e) {
                readyToQuit = true;
            } catch (final NumberFormatException e) {
                Terminal.printLine("Error: Expected Number");
            }
        }
    }

    /**
     * Checks if is program ready to quit.
     *
     * @return true, if is ready to quit
     */
    public boolean isReadyToQuit() {
        return readyToQuit;
    }
}
