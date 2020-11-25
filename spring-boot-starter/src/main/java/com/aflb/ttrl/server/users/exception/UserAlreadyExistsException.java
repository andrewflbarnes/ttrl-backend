package com.aflb.ttrl.server.users.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String msg) {
        super(msg);
    }

    public static UserAlreadyExistsException ofDiscordId(String discordId) {
        return new UserAlreadyExistsException("User already exists with discord ID: " + discordId);
    }
}
