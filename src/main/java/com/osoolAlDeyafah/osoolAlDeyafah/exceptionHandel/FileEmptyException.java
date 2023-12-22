package com.osoolAlDeyafah.osoolAlDeyafah.exceptionHandel;

import org.springframework.http.HttpStatus;

public class FileEmptyException extends ApiBaseException{
    public FileEmptyException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
