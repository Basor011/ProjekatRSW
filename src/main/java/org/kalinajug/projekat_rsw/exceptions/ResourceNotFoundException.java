package org.kalinajug.projekat_rsw.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;


public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String poruka) {super  (poruka);}
}
