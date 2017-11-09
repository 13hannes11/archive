/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hannes
 */
public class Tile {

    private int x, y;
    private byte type;
    private int round;
    private int score;
    private boolean ext;

    public Tile(int myX, int myY, byte TileType) {
        score = 0;
        x = myX;
        y = myY;
        type = TileType;
        if (type == 2) {
            ignite();
        } else {
            round = -1;
        }
        ext = false;
    }

    protected Tile(int myX, int myY, byte TileType, int r, int s, boolean extinguished) {
        score = s;
        x = myX;
        y = myY;
        type = TileType;
        round = r;
        ext = extinguished;
    }

    public void extinguish() {
        ext = true;
    }

    public int getType() {
        return type;
    }

    public void ignite() {
        if (type == 0 && !isExt()) {
            type = 2;
            round = 0;
        }
    }

    public void setRound(int r) {
        if (type != 2) {
            round = r;
        }
    }

    public int getRound() {
        return round;
    }

    public boolean isExt() {
        return ext;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public Tile clone() {
        return new Tile(x, y, type, round, getScore(),ext);
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "[" + x + "|" + y + "]";
    }
}
