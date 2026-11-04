package org.kalinajug.projekat_rsw.controllers.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TerminRequest {
    @NotNull
    private LocalDateTime pocetak;

    @NotNull
    private LocalDateTime kraj;

    @NotNull
    private Long rokId;
}