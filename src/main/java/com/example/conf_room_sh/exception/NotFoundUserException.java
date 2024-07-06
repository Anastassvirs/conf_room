package com.example.conf_room_sh.exception;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException(final String message) {
        super(message);
    }
}
