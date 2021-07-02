package org.vadym.demo.server.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.vadym.demo.server.exception.ExceptionResponseEntity;
import org.vadym.demo.server.exception.SimpleSearchEngineException;

/**
 * Global exception handler for the Client application.
 *
 * @author Vadym Pechenoha
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(SimpleSearchEngineException.class)
    protected ResponseEntity<ExceptionResponseEntity> handleException(SimpleSearchEngineException e) {
        logger.warn(e.getMessage());
        return new ResponseEntity<>(ExceptionResponseEntity.from(e), HttpStatus.BAD_REQUEST);
    }
}
