package org.kalinajug.projekat_rsw.controllers.response;

import lombok.Data;

import java.util.List;

@Data
public class PredatiZadatakResponse {
    private Long id;
    private Double poeni;
    private Long studentId;
    private Long zadatakZaIzraduId;
    private List<KomentarResponse> komentari;
}