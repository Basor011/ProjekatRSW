package org.kalinajug.exceptions;

public class BadRequestException extends RuntimeException {
    BadRequestException(String poruka) {super(poruka);}
}
