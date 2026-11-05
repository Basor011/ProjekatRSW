package org.kalinajug.projekat_rsw.controllers.response;

import lombok.Data;

@Data
public class StudentResponse {
    private Long id;
    private String ime;
    private String prezime;
    private String email;
    private Integer brIndeksa;
    private String smer;
    private Integer godinaUpisa;
}