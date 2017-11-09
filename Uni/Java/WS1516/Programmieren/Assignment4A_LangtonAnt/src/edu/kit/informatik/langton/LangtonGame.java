package edu.kit.informatik.langton;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.exceptions.FileNotCompalitbleException;

/**
 * The Class LangtonGame.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class LangtonGame {

    /**
     * User input manager which manages input from the user and manipulates the
     * game accordingly.
     */
    private UserInputManager uInputMngr;

    /**
     * Instantiates a new langton game.
     *
     * @param path
     *            the path
     * @param gameType
     *            the game type
     */
    public LangtonGame(final String path, final String gameType) {
        try {
            uInputMngr = new UserInputManager(readFile(path, gameType));
        } catch (IllegalArgumentException | FileNotCompalitbleException e) {
            Terminal.printLine("Error: " + e.getMessage());
        }
    }

    /**
     * Runs the gameloop.
     * 
     * @throws NullPointerException
     *             game has not been initilized correctly
     */
    public void run() throws NullPointerException {
        if (uInputMngr == null)
            throw new NullPointerException("game cannot be run because UserInputManager has not bean set");
        while (!uInputMngr.isReadyToQuit()) {
            uInputMngr.doCommand(Terminal.readLine());
        }
    }

    private Board readFile(final String path, final String gameType)
            throws FileNotCompalitbleException, IllegalArgumentException {
        final String[] file = FileInputHelper.read(path);
        Field[][] fieldArr;
        final int width = file[0].length();
        final int height = file.length;
        Ant ant = null;

        fieldArr = new Field[height][width];
        for (int i = 0; i < file.length; i++) {
            final char[] c = file[i].toCharArray();
            if (c.length != width)
                throw new FileNotCompalitbleException("file is not formatted correctly");
            for (int j = 0; j < c.length; j++) {
                switch (c[j]) {
                    case 'N':
                        ant = new Ant(j, i, 0);
                        fieldArr[i][j] = Field.White;
                        break;
                    case 'E':
                        ant = new Ant(j, i, 90);
                        fieldArr[i][j] = Field.White;
                        break;
                    case 'S':
                        ant = new Ant(j, i, 180);
                        fieldArr[i][j] = Field.White;
                        break;
                    case 'W':
                        ant = new Ant(j, i, 270);
                        fieldArr[i][j] = Field.White;
                        break;
                    case '0':
                        fieldArr[i][j] = Field.White;
                        break;
                    case '1':
                        fieldArr[i][j] = Field.Black;
                        break;
                    default:
                        throw new FileNotCompalitbleException("file is not formatted correctly");
                }
            }
        }
        if (gameType.equals("torus"))
            return new Torusboard(fieldArr, ant);
        else if (gameType.equals("standard"))
            return new Board(fieldArr, ant);
        else
            throw new IllegalArgumentException("gameType has to be either 'torus' or 'standard'");
    }
}
