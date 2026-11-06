package org.kalinajug.projekat_rsw.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


public class BadRequestException extends RuntimeException {
    public BadRequestException(String poruka) {super(poruka);}
}
