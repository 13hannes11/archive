package edu.kit.informatik;

/**
 * All constants used in the program
 *
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public final class Constant {
    /**
     * The regex for a token id
     */
    public static final String REGEX_TOKEN_ID = "[+]?((1[0-5])|([0-9]))";
    /**
     * The constant COMMAND_SUCCESSFUL.
     */
    public static final String COMMAND_SUCCESSFUL = "OK";
    /**
     * The constant COMMAND_NOT_FOUND.
     */
    public static final String COMMAND_NOT_FOUND = "please use a valid command";
    /**
     * The constant PREFIX_ERROR
     */
    public static final String PREFIX_ERROR = "Error, ";
    /**
     * The constant COORDINATES_WRONG
     */
    public static final String COORDINATE_WRONG = "Coordinate(s) not on board (or allready occupied).";
    /**
     * The constant BAG_NOT_FOUND
     */
    public static final String BAG_NOT_FOUND = "No Token with this identifier inside the bag.";
    /**
     * The constant COMMAND_PLACE_NEXT
     */
    public static final String COMMAND_PLACE_NEXT = "'place' has to be called before using this command again.";
    /**
     * The constant COMMAND_SELECT_NEXT
     */
    public static final String COMMAND_SELECT_NEXT = "'select' has to be called before using this command again.";
    /**
     * The constant COMMAND_GAME_ENDED
     */
    public static final String COMMAND_GAME_ENDED = "Game is over. Command illegal.";
    /**
     * The constant REGEX_ON_BOARD
     */
    public static final String REGEX_ON_BOARD = "[+]?[0]*[0-5]";
    /**
     * error message for wrong parameter count in place!!!!!!
     */
    public static final String PLACE_PARAMCOUNT_WRONG = "there has been an issue with the number of parameters "
            + "used with place (therefore turn will be reset)";

    private Constant() {
    }
}