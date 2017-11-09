package edu.kit.informatik;
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
        final BankRegistry bankRegistry = new BankRegistry();
        bankRegistry.run();
    }
}
