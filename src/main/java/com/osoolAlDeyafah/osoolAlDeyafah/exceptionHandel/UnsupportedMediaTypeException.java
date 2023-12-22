package com.osoolAlDeyafah.osoolAlDeyafah.exceptionHandel;

import org.springframework.http.HttpStatus;

public class UnsupportedMediaTypeException extends ApiBaseException{
    public UnsupportedMediaTypeException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.UNSUPPORTED_MEDIA_TYPE;
    }
}
