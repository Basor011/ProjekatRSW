package org.kalinajug.projekat_rsw.controllers.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class GrupaRequest {
    @NotNull
    @Positive
    private Integer brojGrupe;

    @NotNull
    private Long terminId;
}