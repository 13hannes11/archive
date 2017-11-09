package edu.kit.informatik.terminalinput;

import edu.kit.informatik.Constant;
import edu.kit.informatik.Terminal;
import edu.kit.informatik.exception.IllegalParameterException;
import edu.kit.informatik.game.BoardGame;

/**
 * The Class InsertCommand.
 *
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class ColprintCommand extends Command {

    /**
     * Instantiates a new insert command.
     */
    public ColprintCommand() {
        super("colprint", "colprint <columnnumber>", 1);
    }

    @Override
    public boolean correctParameters(final String[] parameters) {
        return parameters[0].matches(Constant.REGEX_ON_BOARD);
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.kit.informatik.terminlinput.Command#execute(java.lang.String,
     * edu.kit.informatik.graph.Graph)
     */
    @Override
    public void execute(final String command, final BoardGame game) {
        try {
            Terminal.printLine(game.columnToString(Integer.parseInt(commandToParametersArray(command)[0])));
        } catch (final IllegalParameterException e) {
            InputManager.error(e.getMessage());
        }
    }

}
