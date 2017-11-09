package edu.kit.informatik.langton;

/**
 * The Class Ant.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class Ant {

    /** The x position. */
    private int xPos;

    /** The y position. */
    private int yPos;

    /** The direction in degrees (clockwise) starting north (0°) */
    private int direction;

    /**
     * Instantiates a new ant.
     *
     * @param xPos
     *            the x-position
     * @param yPos
     *            the y-position
     * @param direction
     *            the direction
     */
    public Ant(final int xPos, final int yPos, final int direction) {
        if ((direction % 90) == 0)
            this.direction = direction;
        else
            this.direction = 0;

        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Turns the ant by a multiple of 90°.
     *
     * @param degrees
     *            the degrees to turn
     */
    public void turn(final int degrees) {
        if (degrees % 90 != 0)
            throw new IllegalArgumentException();

        direction += degrees;
        if (direction >= 360)
            direction = direction - 360;
        else if (direction <= -360)
            direction = 0;
    }

    /**
     * Movees the Ant in the direction it is facing by one unit.
     */
    public void move() {
        /*
         * cos(0°) is one therefore yPos will become smaller by one if the ant
         * is facing north.
         * cos(180°) is minus one therefore xPos will become smaller by one if
         * the ant is facing south.
         * cos(90°) and cos(270°) are zero therefore yPos will not increase if
         * the ant is facing east or west
         */
        yPos -= Math.round(Math.cos(Math.toRadians(direction)));
        /*
         * sin(90°) is one therefore xPos will become bigger by one if the ant
         * is facing east.
         * cos(270°) is minus one therefore xPos will become bigger by one if
         * the ant is facing west.
         * cos(0°) and cos(180°) are zero therefore xPos will not increase if
         * the ant is facing south or north.
         */
        xPos += Math.round(Math.sin(Math.toRadians(direction)));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return yPos + "," + xPos;
    }

    /**
     * Gets the x pos.
     *
     * @return the x pos
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * Gets the y pos.
     *
     * @return the y pos
     */
    public int getYPos() {
        return yPos;
    }

    /**
     * Sets the x pos.
     *
     * @param xPos
     *            the new x pos
     */
    public void setXPos(final int xPos) {
        this.xPos = xPos;
    }

    /**
     * Sets the y pos.
     *
     * @param yPos
     *            the new y pos
     */
    public void setYPos(final int yPos) {
        this.yPos = yPos;
    }

    /**
     * Gets the direction as string. Which converts 90 to O, 180 to S, 270 to W
     * and everything else to N
     *
     * @return the direction as string
     */
    public String getDirectionAsString() {
        String ret = "N";
        switch (direction) {
            case 90:
                ret = "O";
                break;
            case 180:
                ret = "S";
                break;
            case 270:
                ret = "W";
                break;
            default:
                ret = "N";
        }
        return ret;
    }
}
