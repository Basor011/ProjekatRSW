package org.kalinajug.projekat_rsw.controllers.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentRequest {
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

    @NotNull
    private Integer brIndeksa;

    @NotBlank
    private String smer;

    @NotNull
    private Integer godinaUpisa;
}