package edu.kit.informatik.list;

/**
 * The Class Pair.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 * 
 * @param <T>
 *            the generic type
 * @param <U>
 *            the generic type
 */
public class Pair<T, U> {

    private final T first;
    private final U second;

    /**
     * Instantiates a new pair.
     *
     * @param first
     *            the first
     * @param second
     *            the second
     */
    public Pair(final T first, final U second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Gets the first element of the pair.
     *
     * @return the first
     */
    public T getFirst() {
        return first;
    }

    /**
     * Gets the second element of the pair.
     *
     * @return the second
     */
    public U getSecond() {
        return second;
    }

}
