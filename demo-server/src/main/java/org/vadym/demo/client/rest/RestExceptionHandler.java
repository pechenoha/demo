package org.vadym.demo.client.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.vadym.demo.client.model.exception.CannotEditException;
import org.vadym.demo.client.model.exception.ExceptionResponseEntity;
import org.vadym.demo.client.model.exception.NoSuchDocumentException;

/**
 * Global exception handler for the Server application.
 *
 * @author Vadym Pechenoha
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(NoSuchDocumentException.class)
    protected ResponseEntity<ExceptionResponseEntity> handleException(NoSuchDocumentException e) {
        logger.warn(e.getMessage());
        return new ResponseEntity<>(ExceptionResponseEntity.from(e), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CannotEditException.class)
    protected ResponseEntity<ExceptionResponseEntity> handleException(CannotEditException e) {
        logger.warn(e.getMessage());
        return new ResponseEntity<>(ExceptionResponseEntity.from(e), HttpStatus.FORBIDDEN);
    }
}
