package edu.kit.informatik;

import edu.kit.informatik.exceptions.IllegalMethodCallException;
import edu.kit.informatik.exceptions.UserInputException;

/**
 * The Class InputManager.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class InputManager {
    private final String invalidCommand;
    private final ConnectFourBoard board;
    /** The boolean ready to quit used to store if game should end. */
    private boolean readyToQuit;

    /**
     * Instantiates a new input manager.
     */
    public InputManager() {
        invalidCommand = "Error: Invalid command. Commands are: 'quit', "
                + "'field <row>,<column>', 'print', 'throwin <column>'";
        board = new ConnectFourBoard();
        readyToQuit = false;
    }

    /**
     * Converts commands to actions that manipulates the Board.
     *
     * @param command
     *            the command as string
     */
    public void runCommand(final String command) {
        if (command == null) {
            Terminal.printLine(invalidCommand);
            return;
        }
        final String[] arr = command.split("[\\s]");

        String[] parameters;
        try {
            parameters = arr[1].split("[\\,]");
        } catch (final ArrayIndexOutOfBoundsException e) {
            parameters = new String[0];
        }

        if (arr != null && arr[0] != null) {
            try {
                switch (arr[0]) {
                    case "quit":
                        if (parameters.length != 0) {
                            throw new UserInputException("quit expects zero parameters");
                        }
                        readyToQuit = true;
                        break;
                    case "field":
                        if (parameters.length != 2) {
                            throw new UserInputException("field expects two parameters: field <row>,<column>");
                        }
                        Terminal.printLine(Character.toString(
                                board.getField(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]))));
                        break;
                    case "print":
                        if (parameters.length != 0) {
                            throw new UserInputException("print expects zero parameters");
                        }
                        Terminal.printLine(board.toString());
                        break;
                    case "throwin":
                        if (parameters.length != 1) {
                            throw new UserInputException("throwin expects one parameter: throwin <column>");
                        }

                        board.throwIn(Integer.parseInt(parameters[0]));

                        if (board.hasEnded()) {
                            if (board.hasFirstPlayerWon()) {
                                Terminal.printLine("P0 wins");
                            } else if (board.hasSecondPlayerWon()) {
                                Terminal.printLine("P1 wins");
                            } else {
                                Terminal.printLine("draw");
                            }
                        } else {
                            Terminal.printLine("success");
                        }
                        break;
                    default:
                        throw new UserInputException(invalidCommand);
                }
            } catch (final UserInputException e) {
                Terminal.printLine("Error: " + e.getMessage());
            } catch (final NumberFormatException e) {
                Terminal.printLine("Error: expected number(s) as parameter(s)");
            } catch (final IllegalArgumentException e) {
                Terminal.printLine("Error: " + e.getMessage());
            } catch (final IllegalMethodCallException e) {
                Terminal.printLine("Error: " + e.getMessage());
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
