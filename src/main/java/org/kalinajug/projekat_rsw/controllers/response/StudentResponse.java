package org.kalinajug.projekat_rsw.controllers.response;


import lombok.Data;
import org.kalinajug.projekat_rsw.model.ListaPrijavljenih;
import org.kalinajug.projekat_rsw.model.PredatiZadatak;

import java.util.List;
@Data
public class StudentResponse {

    private String ime;
    private String prezime;
    private String email;
    private Integer brIndeksa;
    private String smer;
    private Integer godinaUpisa;

    private List<PredatiZadatak> predatiZadatak;
    private List<ListaPrijavljenih> listePrijavljenih;
}
