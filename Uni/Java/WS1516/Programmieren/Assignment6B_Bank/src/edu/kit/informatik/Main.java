package edu.kit.informatik;

import edu.kit.informatik.commandline.CommandLineParser;

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
     *            the arguments
     */
    public static void main(final String[] args) {
        final CommandLineParser cParser = new CommandLineParser();
        cParser.run();
    }
}
