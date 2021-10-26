package com.sweethome.bookingservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class CustomException extends RuntimeException {
        public CustomException() {
            super();
        }
        public CustomException(String message, Throwable cause) {
            super(message, cause);
        }
        public CustomException(String message) {
            super(message);
        }
        public CustomException(Throwable cause) {
            super(cause);
        }

    }
