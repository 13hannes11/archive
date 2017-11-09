package edu.kit.informatik;

/**
 * The Class Eulerapproximator.
 * 
 * @author Hannes Kuchelmesiter
 * @version 1.0
 */
public final class Eulerapproximator {
    private static final String NOT_NATURAL = "the number is not a natural number";

    private Eulerapproximator() {
    }

    /**
     * Calculates eulers number.
     *
     * @param n
     *            the n
     * @return the double
     * @throws IllegalNumberException
     *             the illegal number exception
     */
    public static double calcEuler(final int n) throws IllegalNumberException {
        // Check if n is natural number
        if (n < 0) {
            throw new IllegalNumberException(NOT_NATURAL);
        }

        final double tmp = 1.0 / faculty(n);
        if (n == 0) {
            return tmp;
        } else {
            return calcEuler(n - 1) + tmp;
        }

    }

    private static double faculty(final int n) {
        assert n >= 0;
        if (n == 0) {
            return 1;
        } else {
            return faculty(n - 1) * n;
        }
    }
}
