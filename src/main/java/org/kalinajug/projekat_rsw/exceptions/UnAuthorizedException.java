package org.kalinajug.projekat_rsw.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException(String poruka) {super  (poruka);}
}
