package edu.kit.informatik;

/**
 * The Class ExitException.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class ExitException extends SecurityException {

    /** The status. */
    private final int code;

    /**
     * Instantiates a new exit exception.
     *
     * @param code
     *            the status
     */
    public ExitException(final int code) {
        super("JVM exit is forbidden!");
        this.code = code;
    }
}
