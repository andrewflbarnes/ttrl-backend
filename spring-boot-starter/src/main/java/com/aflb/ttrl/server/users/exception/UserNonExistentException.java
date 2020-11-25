package com.aflb.ttrl.server.users.exception;

public class UserNonExistentException extends RuntimeException {
    public UserNonExistentException(String msg) {
        super(msg);
    }

    public static UserNonExistentException ofDiscordId(String discordId) {
        return new UserNonExistentException("User matching discord ID could not be found: " + discordId);
    }
}
