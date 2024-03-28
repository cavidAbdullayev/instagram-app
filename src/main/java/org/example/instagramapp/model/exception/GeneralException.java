package org.example.instagramapp.model.exception;

import org.example.instagramapp.enums.ErrorResponseEnum;

public class GeneralException extends RuntimeException {
    public GeneralException(ErrorResponseEnum response) {
        super(response.getMessage());
    }
}