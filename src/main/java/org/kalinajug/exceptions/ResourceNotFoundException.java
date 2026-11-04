package org.kalinajug.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String poruka) {super  (poruka);}
}
