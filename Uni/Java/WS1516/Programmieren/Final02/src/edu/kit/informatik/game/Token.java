package edu.kit.informatik.game;

/**
 * The Class Token extends emptyToken.
 *
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class Token extends EmptyToken {
    private final int identifier;

    private boolean isLarge;
    private boolean isBlack;
    private boolean isEdgy;
    private boolean isHollow;

    /**
     * Instanciates a Token
     * 
     * @param id
     *            the identifier
     * @param isBlack
     *            the attribute which indicates if the token is black or white
     * @param isEdgy
     *            the attribute which indicates if the token is round or edgy
     * @param isLarge
     *            the attribute which indicates if the token is large or small
     * @param isHollow
     *            the attribute which indicates if the token is hollow or
     *            massive
     */
    public Token(final int id, final boolean isBlack, final boolean isEdgy, final boolean isLarge,
            final boolean isHollow) {
        this.identifier = id;
        this.setLarge(isLarge);
        this.setBlack(isBlack);
        this.setEdgy(isEdgy);
        this.setHollow(isHollow);
    }

    @Override
    public boolean equals(final Object obj) {
        return obj.getClass().equals(this.getClass()) && ((Token) obj).getIdentifier() == this.getIdentifier();
    }

    @Override
    public String toString() {
        return Integer.toString(getIdentifier());
    }

    /**
     * gets the Identifier
     * 
     * @return get the identifier
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Is large boolean.
     *
     * @return the boolean
     */
    public boolean isLarge() {
        return isLarge;
    }

    /**
     * Sets large.
     *
     * @param large
     *            the large
     */
    public void setLarge(final boolean large) {
        isLarge = large;
    }

    /**
     * Is black boolean.
     *
     * @return the boolean
     */
    public boolean isBlack() {
        return isBlack;
    }

    /**
     * Sets black.
     *
     * @param black
     *            the black
     */
    public void setBlack(final boolean black) {
        isBlack = black;
    }

    /**
     * Is edgy boolean.
     *
     * @return the boolean
     */
    public boolean isEdgy() {
        return isEdgy;
    }

    /**
     * Sets edgy.
     *
     * @param edgy
     *            the edgy
     */
    public void setEdgy(final boolean edgy) {
        isEdgy = edgy;
    }

    /**
     * Is hollow boolean.
     *
     * @return the boolean
     */
    public boolean isHollow() {
        return isHollow;
    }

    /**
     * Sets hollow.
     *
     * @param hollow
     *            the hollow
     */
    public void setHollow(final boolean hollow) {
        isHollow = hollow;
    }
}
