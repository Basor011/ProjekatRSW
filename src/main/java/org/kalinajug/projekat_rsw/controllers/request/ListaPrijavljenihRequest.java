package org.kalinajug.projekat_rsw.controllers.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ListaPrijavljenihRequest {
    @NotNull
    private Long studentId;

    @NotNull
    private Long terminId;
}