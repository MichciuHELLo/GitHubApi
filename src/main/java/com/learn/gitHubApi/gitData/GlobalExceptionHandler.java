package com.learn.gitHubApi.gitData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(ResponseNotFoundException e) {
        return new ResponseEntity<>(messageBuilder(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<?> handleJsonParseException(JsonParseException e) {
        return new ResponseEntity<>(messageBuilder(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequestSenderException.class)
    public ResponseEntity<?> handleRequestSenderException(RequestSenderException e) {
        return new ResponseEntity<>(messageBuilder(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    private Map<String, String> messageBuilder(String message, HttpStatus status) {
        Map<String, String> response = new HashMap<>();
        response.put("status", status.name());
        response.put("message", message);
        return response;
    }

}
