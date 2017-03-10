package de.isb.demo.model.rest;

public class ErrorResponse extends RestResponse {
    private final String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public static ErrorResponse error(String message) {
        return new ErrorResponse(message);
    }
}
