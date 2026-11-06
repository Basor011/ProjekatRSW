package org.kalinajug.projekat_rsw.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;


public class ConflictException extends RuntimeException {
    public ConflictException(String poruka) {super  (poruka);}
}
