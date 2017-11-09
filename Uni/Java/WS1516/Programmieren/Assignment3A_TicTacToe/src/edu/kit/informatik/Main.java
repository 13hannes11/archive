package edu.kit.informatik;

/**
 * The Class Main.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public final class Main {

    /**
     * Instantiates a new main.
     */
    private Main() {

    }

    /**
     * The main method.
     *
     * @param args
     *            the arguments used to start the program
     */
    public static void main(String[] args) {
        TicTacToeGame myGame = new TicTacToeGame();
        System.out.println(myGame.calcWinner(parseToIntArray(args)));
    }

    /**
     * Parses String array to int array.
     *
     * @param arr
     *            the String[] that should be parsed
     * @return the parsed int[]
     */
    private static int[] parseToIntArray(String[] arr) {
        int[] parsedArr = new int[arr.length];
        for (int i = 0; i < parsedArr.length; i++) {
            parsedArr[i] = Integer.parseInt(arr[i]);
        }
        return parsedArr;
    }

}
