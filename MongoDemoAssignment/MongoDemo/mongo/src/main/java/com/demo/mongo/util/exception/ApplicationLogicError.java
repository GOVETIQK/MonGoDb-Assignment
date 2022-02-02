package com.demo.mongo.util.exception;

public class ApplicationLogicError extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApplicationLogicError() {
        super();
    }

    public ApplicationLogicError(String message) {
        super(message);
        this.message = message;
    }

    public ApplicationLogicError(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

}