package org.kalinajug.projekat_rsw.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String poruka) {super(poruka);}
}
