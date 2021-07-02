package org.vadym.demo.client.exception;

/**
 * Response entity representing any type of exception occurring in the application.
 *
 * @author Vadym Pechenoha
 */
public class ExceptionResponseEntity {

    private final String message;

    private ExceptionResponseEntity(String message) {
        this.message = message;
    }

    public static ExceptionResponseEntity from(Exception e) {
        return new ExceptionResponseEntity(e.getMessage());
    };

    public String getMessage() {
        return message;
    }
}
