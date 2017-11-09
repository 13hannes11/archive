package edu.kit.informatik.terminalinput;

import edu.kit.informatik.Constant;
import edu.kit.informatik.exception.IllegalMethodCallException;
import edu.kit.informatik.exception.IllegalParameterException;
import edu.kit.informatik.game.BoardGame;

/**
 * The Class InsertCommand.
 *
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class SelectCommand extends Command {

    /**
     * Instantiates a new insert command.
     */
    public SelectCommand() {
        super("select", "select <Token>", 1);
    }

    @Override
    public boolean correctParameters(final String[] parameters) {
        return parameters[0].matches(Constant.REGEX_TOKEN_ID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.kit.informatik.terminlinput.Command#execute(java.lang.String,
     * edu.kit.informatik.graph.Graph)
     */
    @Override
    public void execute(final String command, final BoardGame game) {
        if (game.hasEnded()) {
            InputManager.error(Constant.COMMAND_GAME_ENDED);
            return;
        }
        final int id = Integer.parseInt(commandToParametersArray(command)[0]);
        try {
            game.select(id);
            InputManager.printSuccess();
        } catch (IllegalParameterException | IllegalMethodCallException e) {
            InputManager.error(e.getMessage());
        }
    }

}
