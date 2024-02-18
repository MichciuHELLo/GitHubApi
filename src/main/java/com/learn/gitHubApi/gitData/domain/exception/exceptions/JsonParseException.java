package com.learn.gitHubApi.gitData.domain.exception.exceptions;

public class JsonParseException extends RuntimeException{
    public JsonParseException(String message) {
        super(message);
    }
}
