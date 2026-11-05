package org.kalinajug.projekat_rsw.controllers.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.kalinajug.projekat_rsw.model.TipRoka;

@Data
public class RokRequest {
    @NotBlank
    private String naziv;

    @NotNull
    private TipRoka tipRoka;

    @NotNull
    private Long predmetId;
}