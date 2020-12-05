package pl.edu.pjwstk.jaz.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.edu.pjwstk.jaz.exceptions.UnauthorizedException;
import pl.edu.pjwstk.jaz.exceptions.UserAlreadyExistsException;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = UserAlreadyExistsException.class)
    protected ResponseEntity<Object> handleUserAlreadyExistsException(RuntimeException exception, WebRequest request) {
        String bodyOfResponse = "User already exists";
        return handleExceptionInternal(
                exception,
                bodyOfResponse,
                new HttpHeaders(),
                HttpStatus.CONFLICT,
                request);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    protected ResponseEntity<Object> handleUnauthorizedException(RuntimeException exception, WebRequest request) {
        String bodyOfResponse = "";
        return handleExceptionInternal(
                exception,
                bodyOfResponse,
                new HttpHeaders(),
                HttpStatus.CONFLICT,
                request);
    }
}
