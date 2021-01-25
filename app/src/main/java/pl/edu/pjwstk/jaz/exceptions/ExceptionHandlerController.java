package pl.edu.pjwstk.jaz.exceptions;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.edu.pjwstk.jaz.exceptions.UnauthorizedException;
import pl.edu.pjwstk.jaz.exceptions.UserAlreadyExistsException;

import javax.persistence.NoResultException;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = CategoryAlreadyExists.class)
    protected ResponseEntity<Object> handleCategoryAlreadyExists(RuntimeException exception, WebRequest request) {
        String bodyOfResponse = "Category already exists!";
        return handleExceptionInternal(
                exception,
                bodyOfResponse,
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(value = SectionAlreadyExists.class)
    protected ResponseEntity<Object> handleSectionAlreadyExists(RuntimeException exception, WebRequest request) {
        String bodyOfResponse = "Section already exists!";
        return handleExceptionInternal(
                exception,
                bodyOfResponse,
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }
//    @ExceptionHandler(value = ConstraintViolationException.class)
//    protected ResponseEntity<Object> handleUserAlreadyExistsException(RuntimeException exception, WebRequest request) {
//        String bodyOfResponse = "User already exists";
//        return handleExceptionInternal(
//                exception,
//                bodyOfResponse,
//                new HttpHeaders(),
//                HttpStatus.CONFLICT,
//                request);
//    }
//
//    @ExceptionHandler(value = NoResultException.class)
//    protected ResponseEntity<Object> handleNoResultException(RuntimeException exception, WebRequest request) {
//        String bodyOfResponse = "User does not exist";
//        return handleExceptionInternal(
//                exception,
//                bodyOfResponse,
//                new HttpHeaders(),
//                HttpStatus.CONFLICT,
//                request);
//    }
//
//    @ExceptionHandler(value = UnauthorizedException.class)
//    protected ResponseEntity<Object> handleUnauthorizedException(RuntimeException exception, WebRequest request) {
//        String bodyOfResponse = "";
//        return handleExceptionInternal(
//                exception,
//                bodyOfResponse,
//                new HttpHeaders(),
//                HttpStatus.UNAUTHORIZED,
//                request);
//    }
}
