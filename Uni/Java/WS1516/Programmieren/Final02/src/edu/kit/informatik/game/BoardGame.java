package edu.kit.informatik.game;

import java.util.ArrayList;
import java.util.Collection;

import edu.kit.informatik.Constant;
import edu.kit.informatik.exception.IllegalMethodCallException;
import edu.kit.informatik.exception.IllegalParameterException;

/**
 * The Class BoardGame.
 *
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class BoardGame {
    /**
     * The constant that defines the number of fields per row
     */
    public static final int ROW_COUNT = 6;
    /**
     * The constant that defines the number of fields per row
     */
    public static final int COLUMN_COUNT = 6;

    private int lastTurnRow;
    private int lastTurnColumn;

    private int turnCount;
    private Token toPlace;

    private Collection<Token> bag;
    private EmptyToken[][] board;

    /**
     * Initializes the game. This means the bag will be filled with the standard
     * tokens and that all fields on the board will be set as Empty Tokens
     */
    public BoardGame() {
        toPlace = null;
        turnCount = 0;

        lastTurnColumn = 0;
        lastTurnRow = 0;

        initBoard();
        initBag();
    }

    private void initBoard() {
        board = new EmptyToken[ROW_COUNT][COLUMN_COUNT];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                board[r][c] = new EmptyToken();
            }
        }
    }

    private void initBag() {
        bag = new ArrayList<>();
        bag.add(new Token(0, true, true, false, true));
        bag.add(new Token(1, true, true, false, false));
        bag.add(new Token(2, true, true, true, true));
        bag.add(new Token(3, true, true, true, false));

        bag.add(new Token(4, true, false, false, true));
        bag.add(new Token(5, true, false, false, false));
        bag.add(new Token(6, true, false, true, true));
        bag.add(new Token(7, true, false, true, false));

        bag.add(new Token(8, true, true, false, true));
        bag.add(new Token(9, true, true, false, false));
        bag.add(new Token(10, true, true, true, true));
        bag.add(new Token(11, true, true, true, false));

        bag.add(new Token(12, true, false, false, true));
        bag.add(new Token(13, true, false, false, false));
        bag.add(new Token(14, true, false, true, true));
        bag.add(new Token(15, true, false, true, false));
    }

    /**
     * Selects the next token to be placed
     * 
     * @param identifier
     *            the identifier of the token that should be placed
     * @throws IllegalParameterException
     *             is thrown if the bag does not contain a token with the
     *             <code></>identifier</code>
     * @throws IllegalMethodCallException
     *             is thrown if the another method needs to be called before
     *             this method
     */
    public void select(final int identifier) throws IllegalParameterException, IllegalMethodCallException {
        if (toPlace != null)
            throw new IllegalMethodCallException(Constant.COMMAND_PLACE_NEXT);
        for (final Token t : bag) {
            if (identifier == t.getIdentifier()) {
                toPlace = t;
                bag.remove(t);
                break;
            }
        }
        if (toPlace == null)
            throw new IllegalParameterException(Constant.BAG_NOT_FOUND);
    }

    /**
     * Places the selected token on the board
     * 
     * @param row
     *            row coordinate
     * @param column
     *            column coordinate
     * @throws IllegalMethodCallException
     *             is thrown if another method needs to be called first
     * @throws IllegalParameterException
     *             is thrown if parameters are not valid
     */
    public void place(final int row, final int column) throws IllegalMethodCallException, IllegalParameterException {
        if (toPlace == null) {
            // just to be sure that it will be restarted
            restartTurn();
            throw new IllegalMethodCallException(Constant.COMMAND_SELECT_NEXT);
        }
        final int tmpRow = modifyCoordinate(row);
        final int tmpColumn = modifyCoordinate(column);

        if (!isOnField(tmpRow, tmpColumn) || board[tmpRow][tmpColumn] instanceof Token) {
            // just to be sure that it will be restarted
            restartTurn();
            throw new IllegalParameterException(Constant.COORDINATE_WRONG);
        }
        board[tmpRow][tmpColumn] = toPlace;
        lastTurnRow = tmpRow;
        lastTurnColumn = tmpColumn;
        toPlace = null;
        if (!hasEnded())
            turnCount += 1;
    }

    /**
     * Restarts the turn which mean that the token which is ready to be placed
     * will be returned to the bag
     */
    public void restartTurn() {
        if (toPlace != null)
            bag.add(toPlace);
        toPlace = null;
    }

    /**
     * 
     * @return returns all tokens contained in the "bag" by their id separated
     *         with "\n"
     */
    public String bagToString() {
        String ret = "";
        for (final Token t : bag) {
            ret += t.getIdentifier() + " ";
        }
        return ret.trim();
    }

    /**
     * Returns a column as String (which means the tokens are separated by "\n")
     * 
     * @param column
     *            the coordinate of the column which should be returned
     * @return returns the column
     * @throws IllegalParameterException
     *             is thrown if column coordinate is not correct
     */
    public String columnToString(final int column) throws IllegalParameterException {
        String ret = "";
        if (!isOnField(0, column))
            throw new IllegalParameterException(Constant.COORDINATE_WRONG);

        for (int i = 0; i < ROW_COUNT; i++) {
            ret += board[i][column].toString() + " ";
        }
        return ret.trim();
    }

    /**
     * Returns a row as String (which means that tokens are printed by their
     * identifier seperated with "\n")
     * 
     * @param row
     *            the row coordinates
     * @return returns the row as string
     * @throws IllegalParameterException
     *             is thrown if the row coordinate is wrong
     */
    public String rowToString(final int row) throws IllegalParameterException {
        String ret = "";
        if (!isOnField(row, 0))
            throw new IllegalParameterException(Constant.COORDINATE_WRONG);

        for (int i = 0; i < COLUMN_COUNT; i++) {
            ret += board[row][i].toString() + " ";
        }
        return ret.trim();
    }

    /**
     * Indicates if the game has enden
     * 
     * @return returns true if the game is over
     */
    public boolean hasEnded() {
        return (bag.size() == 0 && toPlace == null) || hasWon();
    }

    /**
     * tests if last turn was a winning turn
     * 
     * @return returns true if last turn was a winning turn
     */
    public boolean hasWon() {
        if (!(board[lastTurnRow][lastTurnColumn] instanceof Token))
            return false;

        final Token lastTurnToken = (Token) board[modifyCoordinate(lastTurnRow)][modifyCoordinate(lastTurnColumn)];

        final TokenCounter topLeft = new TokenCounter(lastTurnToken);
        final TokenCounter topRight = new TokenCounter(lastTurnToken);

        final TokenCounter top = new TokenCounter(lastTurnToken);
        final TokenCounter bottom = new TokenCounter(lastTurnToken);

        final TokenCounter bottomLeft = new TokenCounter(lastTurnToken);
        final TokenCounter bottomRight = new TokenCounter(lastTurnToken);

        final TokenCounter left = new TokenCounter(lastTurnToken);
        final TokenCounter right = new TokenCounter(lastTurnToken);

        for (int i = 1; i < 4; i++) {

            topLeft.addToCount(getLeftTop(lastTurnRow, lastTurnColumn, i));
            topRight.addToCount(getRightTop(lastTurnRow, lastTurnColumn, i));

            top.addToCount(getTop(lastTurnRow, lastTurnColumn, i));
            bottom.addToCount(getBottom(lastTurnRow, lastTurnColumn, i));

            bottomLeft.addToCount(getLeftBottom(lastTurnRow, lastTurnColumn, i));
            bottomRight.addToCount(getRightBottom(lastTurnRow, lastTurnColumn, i));

            left.addToCount(getLeft(lastTurnRow, lastTurnColumn, i));
            right.addToCount(getRight(lastTurnRow, lastTurnColumn, i));
        }
        // only 3 because last placed token is not counted therefore 3
        // neighbouring tokens with one attribute that is the same have to be
        // found
        return TokenCounter.combinedCount(left, right) >= 3 || TokenCounter.combinedCount(top, bottom) >= 3
                || TokenCounter.combinedCount(topLeft, bottomRight) >= 3
                || TokenCounter.combinedCount(topRight, bottomLeft) >= 3;
    }

    private Token getLeftTop(final int row, final int column, final int offset) {
        final int tmpRow = modifyCoordinate(row - offset);
        final int tmpColumn = modifyCoordinate(column - offset);
        if (!isOnField(tmpRow, tmpColumn) || !board[tmpRow][tmpColumn].getClass().equals(Token.class)) {
            return null;
        }
        return (Token) board[tmpRow][tmpColumn];
    }

    private Token getLeftBottom(final int row, final int column, final int offset) {
        final int tmpRow = modifyCoordinate(row + offset);
        final int tmpColumn = modifyCoordinate(column - offset);
        if (!isOnField(tmpRow, tmpColumn) || !board[tmpRow][tmpColumn].getClass().equals(Token.class)) {
            return null;
        }
        return (Token) board[tmpRow][tmpColumn];
    }

    private Token getTop(final int row, final int column, final int offset) {
        final int tmpRow = modifyCoordinate(row - offset);
        final int tmpColumn = modifyCoordinate(column);
        if (!isOnField(tmpRow, tmpColumn) || !board[tmpRow][tmpColumn].getClass().equals(Token.class)) {
            return null;
        }
        return (Token) board[tmpRow][tmpColumn];
    }

    private Token getBottom(final int row, final int column, final int offset) {
        final int tmpRow = modifyCoordinate(row + offset);
        final int tmpColumn = modifyCoordinate(column);
        if (!isOnField(tmpRow, tmpColumn) || !board[tmpRow][tmpColumn].getClass().equals(Token.class)) {
            return null;
        }
        return (Token) board[tmpRow][tmpColumn];
    }

    private Token getRightTop(final int row, final int column, final int offset) {
        final int tmpRow = modifyCoordinate(row - offset);
        final int tmpColumn = modifyCoordinate(column + offset);
        if (!isOnField(tmpRow, tmpColumn) || !board[tmpRow][tmpColumn].getClass().equals(Token.class)) {
            return null;
        }
        return (Token) board[tmpRow][tmpColumn];
    }

    private Token getRightBottom(final int row, final int column, final int offset) {
        final int tmpRow = modifyCoordinate(row + offset);
        final int tmpColumn = modifyCoordinate(column + offset);
        if (!isOnField(tmpRow, tmpColumn) || !board[tmpRow][tmpColumn].getClass().equals(Token.class)) {
            return null;
        }
        return (Token) board[tmpRow][tmpColumn];
    }

    private Token getLeft(final int row, final int column, final int offset) {
        final int tmpRow = modifyCoordinate(row);
        final int tmpColumn = modifyCoordinate(column - offset);
        if (!isOnField(tmpRow, tmpColumn) || !board[tmpRow][tmpColumn].getClass().equals(Token.class)) {
            return null;
        }
        return (Token) board[tmpRow][tmpColumn];
    }

    private Token getRight(final int row, final int column, final int offset) {
        final int tmpRow = modifyCoordinate(row);
        final int tmpColumn = modifyCoordinate(column + offset);
        if (!isOnField(tmpRow, tmpColumn) || !board[tmpRow][tmpColumn].getClass().equals(Token.class)) {
            return null;
        }
        return (Token) board[tmpRow][tmpColumn];
    }

    private boolean isOnField(final int row, final int column) {
        return 0 <= row && row < ROW_COUNT && 0 <= column && column < COLUMN_COUNT;
    }

    /**
     * placeholder to allow modification of coordinates which allows to change
     * the coordinates if they are for example invalid (see torusboard)
     * 
     * @param coordinate
     *            coordinate that should be corrected
     * @return returns the corrected coordinate
     */
    protected int modifyCoordinate(final int coordinate) {
        return coordinate;
    }

    /**
     * Returns the number of done turns
     * 
     * @return number of turn that have been done
     */
    public int getTurnCount() {
        return turnCount;
    }
}
