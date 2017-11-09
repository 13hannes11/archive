package edu.kit.informatik.langton;

import edu.kit.informatik.exceptions.GameHasEndedException;
import edu.kit.informatik.exceptions.InvalidParameterException;

/**
 * The Class Board.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class Board {

    /** The data. */
    private Field[][] data;

    /** The ant. */
    private Ant ant;

    /**
     * Instantiates a new board.
     *
     * @param data
     *            the data of the board
     * @param ant
     *            the ant
     * @throws IllegalArgumentException
     *             if arguments that are used to instanziate Board have an error
     */
    public Board(final Field[][] data, final Ant ant) throws IllegalArgumentException {
        if (ant == null)
            throw new IllegalArgumentException("ant needs to be initialized");
        else if (data == null)
            throw new IllegalArgumentException("data can't be null");

        this.ant = ant;
        this.data = data;
        this.data[ant.getYPos()][ant.getXPos()] = Field.White;
    }

    /**
     * Make turn.
     *
     * @throws GameHasEndedException
     *             throws exception if a new turn is not possible
     */
    public void makeTurn() throws GameHasEndedException {
        ant.move();

        if (ant.getXPos() < 0 || ant.getXPos() >= data[0].length || ant.getYPos() < 0 || ant.getYPos() >= data.length)
            leftBoard();

        // Turning and coloring
        if (data[ant.getYPos()][ant.getXPos()].equals(Field.Black)) {
            ant.turn(270);
            data[ant.getYPos()][ant.getXPos()] = Field.White;
        } else if (Field.White.equals(data[ant.getYPos()][ant.getXPos()])) {
            ant.turn(90);
            data[ant.getYPos()][ant.getXPos()] = Field.Black;
        }
    }

    /**
     * Left board.
     *
     * @throws GameHasEndedException
     *             the exception is thrown if game needs to end
     */
    protected void leftBoard() throws GameHasEndedException {
        throw new GameHasEndedException("Game ended");
    }

    /**
     * Gets the ant.
     *
     * @return the ant
     */
    public Ant getAnt() {
        return ant;
    }

    /**
     * Gets the field at position x,y as string.
     *
     * @param x
     *            the x position
     * @param y
     *            the y position
     * @return the field as string
     * @throws InvalidParameterException
     *             thrown if x or y outside of bounds of data array invalid
     *             parameter exception
     */
    public String getFieldAsString(final int x, final int y) throws InvalidParameterException {
        if (x < 0 || x >= data[0].length || y < 0 || y >= data.length)
            throw new InvalidParameterException("x or y is to large");

        String ret = "";
        if (x == ant.getXPos() && y == ant.getYPos())
            ret += ant.getDirectionAsString();
        else
            ret += data[y][x];
        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String ret = "";
        for (int y = 0; y < data.length; y++) {
            for (int x = 0; x < data[y].length; x++) {
                try {
                    ret += getFieldAsString(x, y);
                } catch (InvalidParameterException e) {
                    e.printStackTrace();
                }
            }
            ret += "\n";
        }
        return ret.trim();
    }

    /**
     * @return the width of the board
     */
    public int getWidth() {
        if (data == null || data.length == 0)
            return 0;
        else
            return data[0].length;
    }

    /**
     * @return the height of the board
     */
    public int getHeight() {
        if (data == null)
            return 0;
        else
            return data.length;
    }
}
