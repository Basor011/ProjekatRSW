package org.kalinajug.projekat_rsw.controllers.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class KomentarRequest {
    @NotNull
    private Long predatiZadatakId;

    @NotBlank
    private String path;

    @NotNull
    private Integer pocetniRed;

    @NotNull
    private Integer krajnjiRed;

    @NotBlank
    private String komentar;

}