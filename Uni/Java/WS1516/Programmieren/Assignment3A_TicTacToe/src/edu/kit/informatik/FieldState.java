package edu.kit.informatik;

/**
 * FieldState which tells who occupies the field.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
enum FieldState {
    /** Empty Field. */
    Empty,
    /** Player1 occupies the Field. */
    Player1,
    /** Player2 occupies the Field. */
    Player2;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        switch (this) {
            case Player1:
                return "P1";
            case Player2:
                return "P2";
            default:
                return "";
        }
    }
}
