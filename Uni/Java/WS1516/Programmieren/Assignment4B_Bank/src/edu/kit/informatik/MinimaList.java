package edu.kit.informatik;

/**
 * The Class MinimaList.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class MinimaList {

    /** The first container that stores the first element of the list. */
    private Container firstContainer;

    /** The last container that stores the last element of the list. */
    private Container lastContainer;
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
     * @param account
     *            the account
     */
    public void add(Account account) {
        if (firstContainer == null) {
            this.firstContainer = new Container(account);
            this.lastContainer = this.firstContainer;
        } else {
            Container tmp = new Container(account);
            this.lastContainer.setNext(tmp);
            this.lastContainer = tmp;
        }
        this.size++;
    }

    /**
     * Gets the index of an element.
     *
     * @param account
     *            the account
     * @return the index
     */
    public int getIndex(Account account) {
        int pos = -1;
        int i = 0;
        Container pointer = firstContainer;
        while (pointer != null) {
            if (pointer.getAccount().equals(account)) {
                return i;
            }
            pointer = pointer.getNext();
            i++;
        }
        return pos;
    }

    /**
     * Adds an element to the list at the given index
     *
     * @param account
     *            the account
     * @param index
     *            the index
     */
    public void add(Account account, int index) {
        if (index >= size || index < 0) {
            this.add(account);
        } else if (index == 0) {
            Container tmpContainer = new Container(account);
            tmpContainer.setNext(firstContainer);
            firstContainer = tmpContainer;
            this.size++;
        } else {
            Container pointer = getContainer(index - 1);
            Container tmpContainer = new Container(account);
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
    public boolean remove(int index) {
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
    public Account getFirst() {
        return firstContainer.getAccount();
    }

    /**
     * Gets the last element of the list.
     *
     * @return the last
     */
    public Account getLast() {
        return lastContainer.getAccount();
    }

    private Container getContainer(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Container pointer = firstContainer;
        int i = 0;
        while (i < index) {
            pointer = pointer.getNext();
            i++;
        }
        return pointer;
    }

    /**
     * Gets the account at the index.
     *
     * @param index
     *            the index
     * @return the account
     */
    public Account get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return this.getContainer(index).getAccount();
    }

    /**
     * Checks if element is part of the list.
     *
     * @param account
     *            the account
     * @return true, if element is part of list
     */
    public boolean contains(Account account) {
        Container pointer = firstContainer;
        while (pointer != null) {
            if (pointer.getAccount().equals(account)) {
                return true;
            }
            pointer = pointer.getNext();
        }
        return false;
    }

    /**
     * @return the size of the list
     */
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String ret = "";
        Container pointer = firstContainer;
        while (pointer != null) {
            ret += "\t" + pointer.getAccount() + "\n";
            pointer = pointer.getNext();
        }
        return "\t" + ret.trim();
    }
}
