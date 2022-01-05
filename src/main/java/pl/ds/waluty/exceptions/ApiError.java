package pl.ds.waluty.exceptions;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@RequiredArgsConstructor
public class ApiError {
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
    private final ZonedDateTime timestamp;
}


