package com.assignment.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -9079454849611061074L;

    public ResourceNotFoundException(String message) {
        super(String.format("Resource:  %s not found", message));
    }
}
