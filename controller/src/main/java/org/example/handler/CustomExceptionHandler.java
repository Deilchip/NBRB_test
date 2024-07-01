package org.example.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<Object> handleNonValidData(InvalidParameterException ex) {
        return new ResponseEntity<>(getMessage(ex), getHeaders(), HttpStatus.BAD_REQUEST);
    }

    private String getMessage(Exception exception) {
        return exception.getMessage();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "plain", StandardCharsets.UTF_8));
        return headers;
    }
}