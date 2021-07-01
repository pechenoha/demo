package org.vadym.demo.server.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.vadym.demo.server.model.exception.CannotEditException;
import org.vadym.demo.server.model.exception.ExceptionResponseEntity;
import org.vadym.demo.server.model.exception.NoSuchDocumentException;

/**
 * Global exception handler.
 *
 * @author Vadym Pechenoha
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchDocumentException.class)
    protected ResponseEntity<ExceptionResponseEntity> handleException(NoSuchDocumentException e) {
        return new ResponseEntity<>(ExceptionResponseEntity.from(e), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CannotEditException.class)
    protected ResponseEntity<ExceptionResponseEntity> handleException(CannotEditException e) {
        return new ResponseEntity<>(ExceptionResponseEntity.from(e), HttpStatus.FORBIDDEN);
    }
}
