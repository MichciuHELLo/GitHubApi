package com.learn.gitHubApi.gitData;

import org.springframework.http.HttpStatus;

public class ResponseNotFoundException extends RuntimeException{
    public ResponseNotFoundException(String message) {
        super(message);
    }
}
