package edu.kit.informatik.langton;

import edu.kit.informatik.Terminal;

/**
 * The Class Main.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public final class Main {

    private Main() {
    }

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     */
    public static void main(final String[] args) {
        LangtonGame lg;
        try {
            lg = new LangtonGame(args[0], args[1]);
            lg.run();
        } catch (ArrayIndexOutOfBoundsException e) {
            Terminal.printLine("Error: please use 2 arguments to start the program ('path', 'torus' | 'standard')");
        } catch (NullPointerException e) {
            Terminal.printLine("Error: " + e.getMessage());
        }

    }

}
