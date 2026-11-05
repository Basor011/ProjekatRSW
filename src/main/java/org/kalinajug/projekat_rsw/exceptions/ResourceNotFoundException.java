package org.kalinajug.projekat_rsw.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String poruka) {super  (poruka);}
}
