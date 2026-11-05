package org.kalinajug.projekat_rsw.controllers.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PredmetRequest {
    @NotBlank
    private String naziv;
}