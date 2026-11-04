package org.kalinajug.projekat_rsw.controllers.response;



import lombok.Data;
import org.kalinajug.projekat_rsw.model.Rok;

import java.util.List;

@Data
public class PredmetResponse {

    private Long predmetid;
    private String naziv;
    private List<Rok> rokovi;
}
