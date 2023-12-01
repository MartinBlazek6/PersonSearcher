package com.personsearcher.personsearcher.entity.exception;

public class PersonNotFoundException extends RuntimeException{
    public PersonNotFoundException(String message) {
        super(message);
    }
}
