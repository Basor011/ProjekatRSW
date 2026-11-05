package org.kalinajug.projekat_rsw.controllers.response;

import lombok.Data;

@Data
public class KomentarResponse {
    private Long id;
    private String path;
    private Integer pocetniRed;
    private Integer krajnjiRed;
    private String komentar;
    private Long predatiZadatakId;
}