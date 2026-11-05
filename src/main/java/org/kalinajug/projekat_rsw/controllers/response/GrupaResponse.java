package org.kalinajug.projekat_rsw.controllers.response;

import lombok.Data;

import java.util.List;

@Data
public class GrupaResponse {
    private Long id;
    private Integer brojGrupe;
    private Long terminId;
    private List<Long> zadaciIds;
}