package com.osoolAlDeyafah.osoolAlDeyafah.exceptionHandel;

import org.springframework.http.HttpStatus;

public class EmailException extends ApiBaseException{
    public EmailException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.FORBIDDEN;
    }
}
