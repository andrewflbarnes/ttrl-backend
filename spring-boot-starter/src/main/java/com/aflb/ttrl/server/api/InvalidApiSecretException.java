package com.aflb.ttrl.server.api;

public class InvalidApiSecretException extends RuntimeException {
    public InvalidApiSecretException() {
        super("The secret token provided was invalid");
    }
}
