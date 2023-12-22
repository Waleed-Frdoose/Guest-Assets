package com.osoolAlDeyafah.osoolAlDeyafah.exceptionHandel;

import org.springframework.http.HttpStatus;

public class UploadException extends ApiBaseException{
    public UploadException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
