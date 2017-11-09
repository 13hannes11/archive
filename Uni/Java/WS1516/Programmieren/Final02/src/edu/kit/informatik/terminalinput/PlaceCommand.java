package edu.kit.informatik.terminalinput;

import edu.kit.informatik.Constant;
import edu.kit.informatik.Terminal;
import edu.kit.informatik.exception.IllegalMethodCallException;
import edu.kit.informatik.exception.IllegalParameterException;
import edu.kit.informatik.game.BoardGame;

/**
 * The Class InsertCommand.
 *
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class PlaceCommand extends Command {

    /**
     * Instantiates a new insert command.
     */
    public PlaceCommand() {
        super("place", "place <rownumber>;<columnnumber>", 2);
    }

    @Override
    public boolean correctParameters(final String[] parameters) {
        return true;
    }

    // Required to do because of shitty regulations regarding what a correct
    // command is therefore
    @Override
    public final boolean validate(final String command) {
        final String[] parameters = commandToParametersArray(command);
        return correctCommand(command) && correctParameters(parameters);
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.kit.informatik.terminlinput.Command#execute(java.lang.String,
     * edu.kit.informatik.graph.Graph)
     */
    @Override
    public void execute(final String command, final BoardGame game) {
        final String[] parameters = commandToParametersArray(command);
        if (parameters.length != this.getParameterCount()) {
            game.restartTurn();
            InputManager.error(Constant.PLACE_PARAMCOUNT_WRONG);
            return;
        }
        final int row;
        final int column;
        try {
            row = Integer.parseInt(parameters[0]);
            column = Integer.parseInt(parameters[1]);
        } catch (final NumberFormatException e) {
            game.restartTurn();
            InputManager.error(e.getMessage());
            return;
        }
        if (game.hasEnded()) {
            InputManager.error(Constant.COMMAND_GAME_ENDED);
            return;
        }
        try {
            game.place(row, column);
            if (game.hasWon()) {
                Terminal.printLine("P" + Integer.toString((game.getTurnCount() % 2 - 2) * -1) + " wins\n"
                        + Integer.toString(game.getTurnCount()));
            } else if (game.hasEnded())
                Terminal.printLine("draw");
            else
                InputManager.printSuccess();
        } catch (IllegalMethodCallException | IllegalParameterException e) {
            game.restartTurn();
            InputManager.error(e.getMessage());
        }
    }

}
