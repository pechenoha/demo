package org.vadym.demo.server.model.exception;

/**
 * Exception indicating that it's not possible to update documents that are already present in the system.
 *
 * @author Vadym Pechenoha
 */
public class CannotEditException extends RuntimeException {

    public CannotEditException() {
        super("The document is already present in the system. It cannot be updated.");
    }
}
