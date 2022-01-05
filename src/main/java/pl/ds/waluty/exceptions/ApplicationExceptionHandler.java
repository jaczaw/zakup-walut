package pl.ds.waluty.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.time.ZonedDateTime;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleException(ResourceNotFoundException e) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), e.getLocalizedMessage(), ZonedDateTime.now());
        log.info(String.format("Nie znaleziono zasobu - %s", e.getLocalizedMessage()));
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }

    @ExceptionHandler(value = HttpClientErrorException.class)
    public ResponseEntity<ApiError> handleException(HttpClientErrorException e) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), e.getLocalizedMessage(), ZonedDateTime.now());
        log.info(String.format("Problem z wywołaniem zasobu - %s", e.getLocalizedMessage()));
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }
}

