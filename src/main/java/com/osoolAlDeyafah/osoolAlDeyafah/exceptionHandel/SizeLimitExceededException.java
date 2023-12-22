package com.osoolAlDeyafah.osoolAlDeyafah.exceptionHandel;

import org.springframework.http.HttpStatus;

public class SizeLimitExceededException extends ApiBaseException{
    public SizeLimitExceededException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
