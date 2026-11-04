package org.kalinajug.projekat_rsw.controllers.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PredatiZadatakRequest {
    @NotNull
    private Long zadatakZaIzraduId;

    @NotNull
    private Long studentId;

    @Min(0)
    private Integer poeni;
}