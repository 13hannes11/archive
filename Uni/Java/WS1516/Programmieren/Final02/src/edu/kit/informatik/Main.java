package edu.kit.informatik;

import edu.kit.informatik.game.BoardGame;
import edu.kit.informatik.game.TorusBoardGame;
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
     * @param args the arguments
     */
    public static void main(final String[] args) {
        if (args.length != 1 || (!args[0].equals("standard") && !args[0].equals("torus"))) {
            InputManager.error("wrong parameter (expects one parameter which is either 'torus' or 'standard')");
            System.exit(1);
        }
        final InputManager inputManager;
        switch (args[0]) {
            case "torus":
                inputManager = new InputManager(new TorusBoardGame());
                break;
            default:
                inputManager = new InputManager(new BoardGame());
        }
        inputManager.run();
    }

}
