package org.vadym.demo.client.model.exception;

/**
 * Exception indicating that the requested document is not present.
 *
 * @author Vadym Pechenoha
 */
public class NoSuchDocumentException extends RuntimeException {

    public NoSuchDocumentException() {
        super("The requested document hasn't been found");
    }
}
