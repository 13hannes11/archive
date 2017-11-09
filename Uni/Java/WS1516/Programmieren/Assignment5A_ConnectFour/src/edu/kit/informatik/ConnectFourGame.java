package edu.kit.informatik;

/**
 * The Class ConnectFourGame.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class ConnectFourGame {

    /** The input manager. */
    private final InputManager inputManager;

    /**
     * Instantiates a new connect four game.
     */
    public ConnectFourGame() {
        inputManager = new InputManager();
    }

    /**
     * Runs the game.
     */
    public void run() {
        while (!inputManager.isReadyToQuit()) {
            inputManager.runCommand(Terminal.readLine());
        }
    }
}
