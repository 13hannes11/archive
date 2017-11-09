package edu.kit.informatik;

/**
 * The Class TicTacToeGame.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class TicTacToeGame {

    /** The board. */
    private FieldState[] board;

    /** The turn count. */
    private int          turnCount;

    /**
     * Instantiates a new tic tac toe game.
     */
    public TicTacToeGame() {
        turnCount = 0;
        board = initFieldStateArray(9);
    }

    /**
     * Calculate the winner and the turn in which the winning move was made and
     * returns it.
     *
     * @param turns
     *            all turns that were made in the game
     * @return winner and turn in which the winning move was made as String
     */
    public String calcWinner(int[] turns) {
        int winningTurn = 0;
        FieldState winner = FieldState.Empty;

        for (int turn : turns) {
            makeTurn(turn);
            if (winner.equals(FieldState.Empty) && !gameWinner(turn).equals(FieldState.Empty)) {
                winningTurn = turnCount;
                winner = gameWinner(turn);
            }
        }
        if (winner.equals(FieldState.Empty))
            return "draw";
        else
            return winner + " wins " + winningTurn;
    }

    /**
     * Determines if the game in it's current state has a winner. If not
     * FieldState.Empty will be returned
     *
     * @param lastTurn
     *            the field occupied with last turn
     * @return the player who is the winner
     */
    private FieldState gameWinner(int lastTurn) {
        FieldState comperator = null;
        int row = 0;
        int column = 0;
        boolean hasWon = false;

        if (turnCount < 5) {
            return FieldState.Empty;
        }

        if (isFirstPlayersTurn())
            comperator = FieldState.Player1;
        else
            comperator = FieldState.Player2;

        /*
         * Checks row of newly occupied field -> if all in row are occupied by
         * the player who made the last turn return him as winner
         */
        hasWon = true;
        row = convertFieldIdToRow(lastTurn);

        for (int i = (row * 3) - 1; i >= (row * 3) - 3; i--) { // start with 2,
                                                               // 5 or 8 and
                                                               // move down the
                                                               // row from back
                                                               // to front (-1
                                                               // for next
                                                               // field)
            if (!comperator.equals(this.board[i]))
                hasWon = false;
        }
        if (hasWon)
            return comperator;

        /*
         * Checks column of newly occupied field -> if all in column are
         * occupied by the player who made the last turn return him as winner
         */
        hasWon = true;
        column = convertFieldIdToColumn(lastTurn);

        for (int i = 0; i < 3; i++) { // start with 0, 1 or 2 and move down the
                                      // column from from top to bottom (+3 for
                                      // next field)
            if (!comperator.equals(this.board[i * 3 + column - 1]))
                hasWon = false;
        }
        if (hasWon)
            return comperator;

        /*
         * Checks diagonals of newly occupied field -> if all in diagonals are
         * occupied by the player who made the last turn return him as winner
         */
        hasWon = true;
        if ((lastTurn % 2) == 0) { // if lastTurn is in a corner or the center,
                                   // diagonals need to be checked
            if (lastTurn != 2 && lastTurn != 6) { // Diagonal from top-left to
                                                  // bottom-right
                for (int i = 0; i <= 8; i += 4) {
                    if (!comperator.equals(this.board[i]))
                        hasWon = false;
                }
            }
            if (hasWon)
                return comperator;
            if (lastTurn != 0 && lastTurn != 8) { // Diagonal from top-right to
                                                  // bottom-left
                for (int i = 2; i <= 6; i += 2) {
                    if (!comperator.equals(this.board[i]))
                        hasWon = false;
                }
            }
            if (hasWon)
                return comperator;
        }

        return FieldState.Empty;
    }

    /**
     * Convert field Id to row.
     *
     * @param fieldId
     *            the field Id
     * @return the row
     */
    private int convertFieldIdToRow(int fieldId) {
        return fieldId / 3 + 1; // First row has Id 1
    }

    /**
     * Convert field Id to column.
     *
     * @param fieldId
     *            the field Id
     * @return the column
     */
    private int convertFieldIdToColumn(int fieldId) {
        return fieldId % 3 + 1; // First column has Id 1
    }

    /**
     * Makes turn. (Field with fieldId gets occupied by the player that does the
     * turn)
     *
     * @param fieldId
     *            the Id of the field that should be occupied
     */
    private void makeTurn(int fieldId) {
        if (board[fieldId].equals(FieldState.Empty)) {
            turnCount++;

            if (isFirstPlayersTurn()) {
                board[fieldId] = FieldState.Player1;
            } else {
                board[fieldId] = FieldState.Player2;
            }

        }
    }

    /**
     * Checks if it is first players turn.
     *
     * @return true, if it is first players turn
     */
    private boolean isFirstPlayersTurn() {
        // After second Player has made a turn turnCount will be an even number
        if ((turnCount % 2) == 0)
            return false;
        else
            return true;
    }

    /**
     * Initializes the field state array. Every field has the state
     * FieldState.Empty
     *
     * @param size
     *            the size
     * @return the field state[]
     */
    private FieldState[] initFieldStateArray(int size) {
        FieldState[] fieldStates = new FieldState[size];
        for (int i = 0; i < size; i++) {
            fieldStates[i] = FieldState.Empty;
        }
        return fieldStates;
    }
}
