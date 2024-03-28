package org.example.instagramapp.handler;

import org.example.instagramapp.enums.ErrorResponseEnum;
import org.example.instagramapp.model.exception.ErrorResponse;
import org.example.instagramapp.model.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class TotalExceptionHandler {
    @ExceptionHandler(GeneralException.class)
    public ErrorResponse handleGeneralException(GeneralException exception) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationError(MethodArgumentNotValidException exception) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInputValidation() {
        return ErrorResponse.builder()
                .message(ErrorResponseEnum.INPUT_ERROR.getMessage())
                .build();
    }
}