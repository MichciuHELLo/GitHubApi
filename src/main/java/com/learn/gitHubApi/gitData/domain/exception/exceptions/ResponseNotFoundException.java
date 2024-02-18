package com.learn.gitHubApi.gitData.domain.exception.exceptions;

public class ResponseNotFoundException extends RuntimeException{
    public ResponseNotFoundException(String message) {
        super(message);
    }
}
