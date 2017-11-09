package edu.kit.informatik;

import edu.kit.informatik.exceptions.IllegalMethodCallException;

/**
 * The Class ConnectFourBoard.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class ConnectFourBoard {

    private static final char FIRST_PLAYER = '0';
    private static final char SECOND_PLAYER = '1';
    private static final char EMPTY_BOARD = '-';
    private static final int HEIGHT = 7;
    private static final int WIDTH = 7;

    /**
     * The game data. Empty cells are represented as character that is stored in
     * emptyBoard. Tokens are represented as characters that are stored in
     * firstPlayer and secondPlayer.
     */
    // data[row][column]
    private final char[][] data;

    /** Stores who's turn it is. */
    private boolean isFirstPlayersTurn;

    /** Stores the player who has won if a player has won. */
    private char playerWon;

    /**
     * Instantiates a new connect four board.
     * 
     * default configuration:
     * 
     * firstPlayer is instantiate with '0'
     * secondPlayer is instantiated with '1'
     * emptyBoard is instantiated with '-'
     * width is instantiated with 7;
     * height is instantiated with 7
     */
    public ConnectFourBoard() {
        // initializing the board
        playerWon = '-';
        data = new char[HEIGHT][WIDTH];
        isFirstPlayersTurn = true;

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = EMPTY_BOARD;
            }
        }
    }

    /**
     * Throws a token in the named column. The new token will be the character
     * of the player who's turn it is and it will be placed one cell above the
     * last thrown in token (row = height - existing tokens in column ).
     *
     * @param column
     *            the column the token should be thrown in
     */
    public void throwIn(final int column) {
        if (hasEnded()) {
            throw new IllegalMethodCallException("Game has ended! Additional Turns are not allowed!");
        } else if (column < 0 || column >= data[0].length) {
            throw new IllegalArgumentException(
                    "The column is outside of the board. It has to be a number from 0 to " + (WIDTH - 1));
        }

        final int row = (HEIGHT - 1) - getTokenInColumn(column);

        if (row < 0) {
            throw new IllegalArgumentException("column is full");
        }

        char currentPlayer;
        if (isFirstPlayersTurn) {
            currentPlayer = FIRST_PLAYER;
        } else {
            currentPlayer = SECOND_PLAYER;
        }
        data[row][column] = currentPlayer;

        // Check if someone has won

        if (hasWonDiagonalTopLeft(row, column, currentPlayer) || haskWonVertically(row, column, currentPlayer)
                || hasWonHorizontal(row, column, currentPlayer) || hasWonDiagonalTopRight(row, column, currentPlayer)) {
            playerWon = currentPlayer;
            return;
        }
        isFirstPlayersTurn = !isFirstPlayersTurn;
    }

    private boolean haskWonVertically(final int row, final int column, final char currentPlayer) {
        // Check below the token
        int below = 0;
        for (int i = 1; i <= 3; i++) {
            if (isOnBoard(row + i, column) && data[row + i][column] == currentPlayer) {
                below = i;
            } else {
                break;
            }
        }
        // return true if more than four tokens are aligned vertically
        return ((1 + below) >= 4);
    }

    private boolean hasWonHorizontal(final int row, final int column, final char currentPlayer) {
        // Check horizontal
        int horizontalLeft = 0;
        for (int i = 1; i <= 3; i++) {
            if (isOnBoard(row, column - i) && data[row][column - i] == currentPlayer) {
                horizontalLeft = i;
            } else {
                break;
            }
        }
        int horizontalRight = 0;
        for (int i = 1; i <= 3; i++) {
            if (isOnBoard(row, column + i) && data[row][column + i] == currentPlayer) {
                horizontalRight = i;
            } else {
                break;
            }
        }
        // return true if more than four tokens are aligned horizontally
        return ((horizontalLeft + 1 + horizontalRight) >= 4);
    }

    private boolean hasWonDiagonalTopRight(final int row, final int column, final char currentPlayer) {
        // Check diagonally topRight to bottomLeft
        int topRight = 0;
        for (int i = 1; i <= 3; i++) {
            if (isOnBoard(row - i, column + i) && data[row - i][column + i] == currentPlayer) {
                topRight = i;
            } else {
                break;
            }
        }
        int bottomLeft = 0;
        for (int i = 1; i <= 3; i++) {
            if (isOnBoard(row + i, column - i) && data[row + i][column - i] == currentPlayer) {
                bottomLeft = i;
            } else {
                break;
            }
        }
        // return true if more than four tokens are aligned diagonally
        return (topRight + 1 + bottomLeft >= 4);
    }

    private boolean hasWonDiagonalTopLeft(final int row, final int column, final char currentPlayer) {
        // Check diagonally topLeft to bottomRight
        int topLeft = 0;
        for (int i = 1; i <= 3; i++) {
            if (isOnBoard(row - i, column - i) && data[row - i][column - i] == currentPlayer) {
                topLeft = i;
            } else {
                break;
            }
        }
        int bottomRight = 0;
        for (int i = 1; i <= 3; i++) {
            if (isOnBoard(row + i, column + i) && data[row + i][column + i] == currentPlayer) {
                bottomRight = i;
            } else {
                break;
            }
        }
        // return true if more than four tokens are aligned diagonally
        return (bottomRight + 1 + topLeft >= 4);
    }

    private int getTokenInColumn(final int column) {
        for (int i = 0; i < data[column].length; i++) {
            // Count elements starting from the bottom of the field
            if (data[(HEIGHT - 1) - i][column] == '-') {
                return i;
            }
        }
        return HEIGHT;
    }

    /**
     * Checks for if game has ended.
     *
     * @return true, if has ended
     */
    public boolean hasEnded() {
        if (playerWon == FIRST_PLAYER || playerWon == SECOND_PLAYER) {
            return true;
        } else {
            return isFull();
        }
    }

    /**
     * Checks if the board is filled completely.
     *
     * @return true, if is full
     */
    private boolean isFull() {
        for (int i = 0; i < WIDTH; i++) {
            if (getTokenInColumn(i) < HEIGHT) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if first player has won.
     *
     * @return true, if first player has won
     */
    public boolean hasFirstPlayerWon() {
        return (playerWon == FIRST_PLAYER);
    }

    /**
     * Checks if second player has won.
     *
     * @return true, if seceond player has won
     */
    public boolean hasSecondPlayerWon() {
        return (playerWon == SECOND_PLAYER);
    }

    /**
     * Gets one field/cell of the board.
     *
     * @param row
     *            the row or y-coordinate of the field
     * @param column
     *            the column or x-coordinate of the field
     * @return the field's value
     */
    public char getField(final int row, final int column) {
        if (!isOnBoard(row, column)) {
            throw new IllegalArgumentException("Coordinates are not on the board.");
        }
        return data[row][column];
    }

    @Override
    public String toString() {
        String out = "";
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                out += Character.toString(data[y][x]);
            }
            out += "\n";
        }

        return out.trim();
    }

    /**
     * Checks if coordinates are on board.
     *
     * @param row
     *            the row or y-coordinate
     * @param column
     *            the column or x-coordinate
     * @return true, if is on board
     */
    public boolean isOnBoard(final int row, final int column) {
        return !(row < 0 || row >= HEIGHT || column < 0 || column >= WIDTH);

    }
}
