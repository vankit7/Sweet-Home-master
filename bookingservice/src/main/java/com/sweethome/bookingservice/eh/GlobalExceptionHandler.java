package com.sweethome.bookingservice.eh;

import com.sweethome.bookingservice.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandler {
        @ExceptionHandler(CustomException.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public CustomException handleCustomException(CustomException ce) {
            return ce;
    }
}
