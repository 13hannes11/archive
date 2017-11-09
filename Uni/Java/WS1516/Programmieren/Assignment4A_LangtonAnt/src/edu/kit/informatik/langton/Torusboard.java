package edu.kit.informatik.langton;

import edu.kit.informatik.exceptions.GameHasEndedException;

/**
 * The Class Torusboard extends Board.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class Torusboard extends Board {

    /**
     * Instantiates a new torusboard.
     *
     * @param data
     *            the data of the board
     * @param ant
     *            the ant
     * @throws IllegalArgumentException
     *             if arguments that are used to instanziate Board have an error
     */
    public Torusboard(final Field[][] data, final Ant ant) throws IllegalArgumentException {
        super(data, ant);
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.kit.informatik.langton.Board#leftBoard()
     */
    @Override
    protected void leftBoard() throws GameHasEndedException, IllegalArgumentException {
        if (this.getAnt().getXPos() >= this.getWidth()) {
            this.getAnt().setXPos(this.getAnt().getXPos() - this.getWidth());
        } else if (this.getAnt().getYPos() >= this.getHeight()) {
            this.getAnt().setYPos(this.getAnt().getYPos() - this.getHeight());
        } else if (this.getAnt().getXPos() < 0) {
            this.getAnt().setXPos(getWidth() + this.getAnt().getXPos());
        } else if (this.getAnt().getYPos() < 0) {
            this.getAnt().setYPos(getHeight() + this.getAnt().getYPos());
        } else {
            throw new IllegalArgumentException("leftBoard() should not have been called");
        }
    }
}
