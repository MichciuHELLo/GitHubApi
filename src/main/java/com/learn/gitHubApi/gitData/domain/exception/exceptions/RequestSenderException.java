package com.learn.gitHubApi.gitData.domain.exception.exceptions;

public class RequestSenderException extends RuntimeException{
    public RequestSenderException(String message) {
        super(message);
    }
}
