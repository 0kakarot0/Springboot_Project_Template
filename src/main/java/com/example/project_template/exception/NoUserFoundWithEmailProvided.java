package com.example.project_template.exception;

public class NoUserFoundWithEmailProvided extends RuntimeException {
    public NoUserFoundWithEmailProvided(String message) {
        super(message);
    }
}
