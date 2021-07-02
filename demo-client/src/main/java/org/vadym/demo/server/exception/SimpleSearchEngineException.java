package org.vadym.demo.server.exception;

/**
 * Exception describing the error response on the Simple Search Engine side.
 *
 * @author Vadym Pechenoha
 */
public class SimpleSearchEngineException extends RuntimeException {

    public SimpleSearchEngineException(String message) {
        super("The Simple Search Engine application responded with an error: " + message);
    }
}
