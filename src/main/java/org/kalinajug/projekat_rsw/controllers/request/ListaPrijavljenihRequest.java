package org.kalinajug.projekat_rsw.controllers.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ListaPrijavljenihRequest {
    @NotNull
    private List<String> emailovi;

    @NotNull
    private Long terminId;
}