package edu.kit.informatik.list;

/**
 * The Class MinimaList.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 * @param <T>
 *            the generic type
 */
public class MinimaList<T> {

    /** The first container that stores the first element of the list. */
    private Container<T> firstContainer;

    /** The last container that stores the last element of the list. */
    private Container<T> lastContainer;
    /** The number of elements of the list. */
    private int size;

    /**
     * Instantiates a new minima list.
     */
    public MinimaList() {
        size = 0;
        firstContainer = null;
        lastContainer = null;
    }

    /**
     * Adds an element at the end of the list.
     *
     * @param element
     *            the account
     */
    public void add(final T element) {
        if (firstContainer == null) {
            this.firstContainer = new Container<T>(element);
            this.lastContainer = this.firstContainer;
        } else {
            final Container<T> tmp = new Container<T>(element);
            this.lastContainer.setNext(tmp);
            this.lastContainer = tmp;
        }
        this.size++;
    }

    /**
     * Gets the index of an element.
     *
     * @param element
     *            the account
     * @return the index
     */
    public int getIndex(final T element) {
        final int pos = -1;
        int i = 0;
        Container<T> pointer = firstContainer;
        while (pointer != null) {
            if (pointer.getData().equals(element)) {
                return i;
            }
            pointer = pointer.getNext();
            i++;
        }
        return pos;
    }

    /**
     * Adds an element to the list at the given index.
     *
     * @param element
     *            the element
     * @param index
     *            the index
     */
    public void add(final T element, final int index) {
        if (index >= size || index < 0) {
            this.add(element);
        } else if (index == 0) {
            final Container<T> tmpContainer = new Container<T>(element);
            tmpContainer.setNext(firstContainer);
            firstContainer = tmpContainer;
            this.size++;
        } else {
            final Container<T> pointer = getContainer(index - 1);
            final Container<T> tmpContainer = new Container<T>(element);
            tmpContainer.setNext(pointer.getNext());
            pointer.setNext(tmpContainer);
            this.size++;
        }
    }

    /**
     * Removes the element at the index from the list.
     *
     * @param index
     *            the index
     * @return true, if successful
     */
    public boolean remove(final int index) {
        if (index >= size || index < 0) {
            return false;
        } else if (index == 0) {
            firstContainer = firstContainer.getNext();
            this.size--;
            return true;
        } else if (index == size - 1) {
            lastContainer = getContainer(index - 1);
            lastContainer.setNext(null);
            this.size--;
            return true;
        } else {

            this.size--;
            return true;
        }
    }

    /**
     * Gets the first element of the list.
     *
     * @return the first
     */
    public T getFirst() {
        return firstContainer.getData();
    }

    /**
     * Gets the last element of the list.
     *
     * @return the last
     */
    public T getLast() {
        return lastContainer.getData();
    }

    /**
     * Gets the container.
     *
     * @param index
     *            the index
     * @return the container
     */
    private Container<T> getContainer(final int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Container<T> pointer = firstContainer;
        int i = 0;
        while (i < index) {
            pointer = pointer.getNext();
            i++;
        }
        return pointer;
    }

    /**
     * Gets the element at given index.
     *
     * @param index
     *            the index
     * @return the element
     */
    public T get(final int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return this.getContainer(index).getData();
    }

    /**
     * Checks if element is part of the list.
     *
     * @param element
     *            the element
     * @return true, if element is part of list
     */
    public boolean contains(final T element) {
        Container<T> pointer = firstContainer;
        while (pointer != null) {
            if (pointer.getData().equals(element)) {
                return true;
            }
            pointer = pointer.getNext();
        }
        return false;
    }

    /**
     * Size.
     *
     * @return the size of the list
     */
    public int size() {
        return size;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String ret = "";
        Container<T> pointer = firstContainer;
        while (pointer != null) {
            ret += "\t" + pointer.getData().toString() + "\n";
            pointer = pointer.getNext();
        }
        return "\t" + ret.trim();
    }

    /**
     * Gets the first container.
     *
     * @return the first container
     */
    public Container<T> getFirstContainer() {
        return firstContainer;
    }
}
