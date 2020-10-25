package com.wasylmowczan.library.exceptions;

public class PublicationAlreadyExistsException  extends RuntimeException {
    public PublicationAlreadyExistsException(String message) {
        super(message);
    }
}