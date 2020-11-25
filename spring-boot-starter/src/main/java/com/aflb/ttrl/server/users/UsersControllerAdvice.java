package com.aflb.ttrl.server.users;

import com.aflb.ttrl.api.server.model.ErrorResponse;
import com.aflb.ttrl.server.api.InvalidApiSecretException;
import com.aflb.ttrl.server.users.exception.UserAlreadyExistsException;
import com.aflb.ttrl.server.users.exception.UserNonExistentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

@Slf4j
@ControllerAdvice("com.aflb.ttrl.server.users")
public class UsersControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(IllegalArgumentException e, WebRequest r) {
        return generateResponseAndLog(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidApiSecretException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(InvalidApiSecretException e, WebRequest r) {
        return generateResponseAndLog(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNonExistentException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(UserNonExistentException e, WebRequest r) {
        return generateResponseAndLog(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleConflict(UserAlreadyExistsException e, WebRequest r) {
        return generateResponseAndLog(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(UnsupportedOperationException e, WebRequest r) {
        return generateResponseAndLog(e, HttpStatus.NOT_IMPLEMENTED);
    }

    private ResponseEntity<ErrorResponse> generateResponseAndLog(Exception e, HttpStatus status) {
        // TODO uuid on context through entire req-resp lifecycle
        UUID uuid = UUID.randomUUID();

        ErrorResponse er = new ErrorResponse()
                .uuid(uuid)
                .reason(e.getMessage());

        log.warn("{} : {} : {}", uuid, status.toString(), e.getMessage());

        return ResponseEntity
                .status(status)
                .body(er);
    }
}
