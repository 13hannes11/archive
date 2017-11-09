package edu.kit.informatik.langton;

/**
 * The Enum Field.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public enum Field {

    /** field is white. */
    White,
    /** field is black. */
    Black;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        if (this.equals(Field.White))
            return "" + 0;
        else
            return "" + 1;
    }
}
