package pl.edu.pjwstk.jaz.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = CategoryAlreadyExistsException.class)
    protected ResponseEntity<Object> handleCategoryAlreadyExists(RuntimeException exception, WebRequest request) {
        String bodyOfResponse = "Category already exist!";
        return handleExceptionInternal(
                exception,
                bodyOfResponse,
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(value = SectionAlreadyExistsException.class)
    protected ResponseEntity<Object> handleSectionAlreadyExists(RuntimeException exception, WebRequest request) {
        String bodyOfResponse = "Section already exist!";
        return handleExceptionInternal(
                exception,
                bodyOfResponse,
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(value = UserDoesNotExistException.class)
    protected ResponseEntity<Object> handleUserDoesNotExists(RuntimeException exception, WebRequest request) {
        String bodyOfResponse = "User does not exist!";
        return handleExceptionInternal(
                exception,
                bodyOfResponse,
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    protected ResponseEntity<Object> handleUnauthorizedException(RuntimeException exception, WebRequest request) {
        String bodyOfResponse = "";
        return handleExceptionInternal(
                exception,
                bodyOfResponse,
                new HttpHeaders(),
                HttpStatus.UNAUTHORIZED,
                request);
    }
}
