package org.kalinajug.projekat_rsw.controllers.response;

import lombok.Data;

import java.util.List;

@Data
public class NastavnikResponse {
    private Long id;
    private String ime;
    private String prezime;
    private String email;
    private List<Long> zadaciIds;
}