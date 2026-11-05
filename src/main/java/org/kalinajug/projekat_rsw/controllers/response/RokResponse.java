package org.kalinajug.projekat_rsw.controllers.response;

import lombok.Data;
import org.kalinajug.projekat_rsw.model.TipRoka;

import java.util.List;

@Data
public class RokResponse {
    private Long id;
    private String naziv;
    private TipRoka tipRoka;
    private Long predmetId;
    private List<Long> terminiIds;
}