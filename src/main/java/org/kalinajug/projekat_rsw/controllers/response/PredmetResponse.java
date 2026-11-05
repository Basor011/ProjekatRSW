package org.kalinajug.projekat_rsw.controllers.response;

import lombok.Data;

import java.util.List;

@Data
public class PredmetResponse {
    private Long id;
    private String naziv;
    private List<Long> rokoviIds;
}