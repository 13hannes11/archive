package edu.kit.informatik;

import java.security.Permission;

/**
 * The Class NoExitSecurityManager.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class NoExitSecurityManager extends SecurityManager {

    /**
     * Instantiates a new no exit security manager.
     */
    public NoExitSecurityManager() {
        super();
    }

    @Override
    public void checkPermission(final Permission perm) {
    }

    @Override
    public void checkPermission(final Permission perm, final Object context) {
    }

    @Override
    public void checkExit(final int status) {
        super.checkExit(status);
        throw new ExitException(status);
    }
}
