package org.kalinajug.projekat_rsw.controllers.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ZadatakZaIzraduRequest {
    @NotNull
    private Long grupaId;

    @NotNull
    private Long nastavnikId;
}