package org.kalinajug.projekat_rsw.controllers.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NastavnikRequest {
    @NotBlank
    private String ime;

    @NotBlank
    private String prezime;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8)
    private String lozinka;
}