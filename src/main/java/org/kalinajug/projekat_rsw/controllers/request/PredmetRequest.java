package org.kalinajug.projekat_rsw.controllers.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.kalinajug.projekat_rsw.model.Rok;

import java.util.List;

@Data
public class PredmetRequest {

    @NotNull
    private Long predmetid;
    @NotBlank
    private String naziv;
    private List<Rok> rokovi;
}
