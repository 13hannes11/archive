package edu.kit.informatik.list;

/**
 * The Class Container.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 * @param <T>
 *            the generic type
 */
public class Container<T> {

    /** The data. */
    private final T data;

    /** The next. */
    private Container<T> next;

    /**
     * Instantiates a new container.
     *
     * @param data
     *            the data
     */
    public Container(final T data) {
        this.data = data;
        this.next = null;
    }

    /**
     * Sets the next.
     *
     * @param next
     *            the new next
     */
    public void setNext(final Container<T> next) {
        this.next = next;
    }

    /**
     * Gets the next.
     *
     * @return the next
     */
    public Container<T> getNext() {
        return next;
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    public T getData() {
        return data;
    }

}
