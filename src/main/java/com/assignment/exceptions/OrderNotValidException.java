package com.assignment.exceptions;

public class OrderNotValidException extends RuntimeException {

    private static final long serialVersionUID = -9079454849611061074L;

    public OrderNotValidException(String message) {
        super(String.format("Order is invalid since: %s", message));
    }
}
