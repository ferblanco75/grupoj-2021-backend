package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices.errorHandling;

import ar.edu.unq.desapp.grupoj.backenddesappapi.service.exceptions.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllersExceptionHandler {

    //@ExceptionHandler({NullPointerException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Object> NullPointerAndOtherExceptions
            (Exception ex) {
        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());
        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus  .BAD_REQUEST,
                "Malformation Errors" ,
                details);

        return ResponseEntityBuilder.build(err);

    }



    @ExceptionHandler({NonExistentReviewException.class,UserAlreadyReviewTitle.class,NonExistentTitleException.class, NonExistentUserException.class, NonExistentSourceException.class, NonExistentLocationException.class, NonExistentLanguageException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Object> NonExistentExceptions
            (Exception ex) {
        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());
        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Validation Errors" ,
                details);

        return ResponseEntityBuilder.build(err);

    }

    @ExceptionHandler({BadCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseEntity<Object> badCredentialsHandler(Exception ex) {
        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED,
                "Wrong Username or password", Collections.emptyList());
        return ResponseEntityBuilder.build(err);

    }

    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Object> duplicateEntryHandler(Exception ex) {
        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "User already exists", Collections.emptyList());
        return ResponseEntityBuilder.build(err);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Object> processValidationError(MethodArgumentNotValidException ex) {

        List<String> details = new ArrayList<String>();
        details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Validation Errors" ,
                details);

        return ResponseEntityBuilder.build(err);
    }




}
