package edu.kit.informatik.game;

/**
 * The Class TorusBoardGame.
 *
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class TorusBoardGame extends BoardGame {
    /**
     * Instantiates a new Torus board game.
     */
    public TorusBoardGame() {
        super();
    }

    @Override
    protected int modifyCoordinate(final int coordinate) {
        if (coordinate < 0)
            return 5 - (Math.abs(coordinate + 1) % 6);
        else if (coordinate > 5)
            return coordinate % 6;
        return coordinate;
    }
}
