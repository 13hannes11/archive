package edu.kit.informatik.game;

/**
 * The Class Token Counter.
 *
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class TokenCounter {
    private final Token compare;
    private final Token testFor;

    private int blackCount;
    private int largeCount;
    private int edgyCount;
    private int hollowCount;

    /**
     * Instantiates a new Token counter.
     *
     * @param compare
     *            the token to compare to
     */
    public TokenCounter(final Token compare) {
        blackCount = 0;
        largeCount = 0;
        edgyCount = 0;
        hollowCount = 0;

        this.compare = compare;

        testFor = new Token(0, true, true, true, true);
    }

    /**
     * combines the count of the two elements.
     *
     * @param first
     *            the first
     * @param second
     *            the second
     * @return the int
     */
    public static int combinedCount(final TokenCounter first, final TokenCounter second) {
        final int tmpBlack = first.getBlackCount() + second.getBlackCount();
        final int tmpLarge = first.getLargeCount() + second.getLargeCount();
        final int tmpHollow = first.getHollowCount() + second.getHollowCount();
        final int tmpEdgy = first.getEdgyCount() + second.getEdgyCount();

        if (tmpBlack >= tmpLarge && tmpBlack >= tmpHollow && tmpBlack >= tmpEdgy)
            return tmpBlack;
        if (tmpLarge >= tmpBlack && tmpLarge >= tmpHollow && tmpLarge >= tmpEdgy)
            return tmpLarge;
        if (tmpHollow >= tmpLarge && tmpHollow >= tmpBlack && tmpHollow >= tmpEdgy)
            return tmpHollow;
        if (tmpEdgy >= tmpLarge && tmpEdgy >= tmpHollow && tmpEdgy >= tmpBlack)
            return tmpEdgy;

        return 0;
    }

    /**
     * Add token to be counted.
     *
     * @param token
     *            the token
     */
    public void addToCount(final Token token) {
        if (token == null) {
            testFor.setBlack(false);
            testFor.setEdgy(false);
            testFor.setLarge(false);
            testFor.setHollow(false);
            return;
        }
        if (testFor.isEdgy() && token.isEdgy() == compare.isEdgy())
            edgyCount += 1;
        else
            testFor.setEdgy(false);
        if (testFor.isBlack() && token.isBlack() == compare.isBlack())
            blackCount += 1;
        else
            testFor.setBlack(false);

        if (testFor.isLarge() && token.isLarge() == compare.isLarge())
            largeCount += 1;
        else
            testFor.setLarge(false);

        if (testFor.isHollow() && token.isHollow() == compare.isHollow())
            hollowCount += 1;
        else
            testFor.setHollow(false);
    }

    /**
     * Gets black count.
     *
     * @return the black count
     */
    public int getBlackCount() {
        return blackCount;
    }

    /**
     * Gets large count.
     *
     * @return the large count
     */
    public int getLargeCount() {
        return largeCount;
    }

    /**
     * Gets edgy count.
     *
     * @return the edgy count
     */
    public int getEdgyCount() {
        return edgyCount;
    }

    /**
     * Gets hollow count.
     *
     * @return the hollow count
     */
    public int getHollowCount() {
        return hollowCount;
    }
}
