package com.sweethome.bookingservice.eh;

public class GlobalExceptionHandler extends RuntimeException {
    public GlobalExceptionHandler() {
    }

    public GlobalExceptionHandler(String message) {
        super(message);
    }
}
