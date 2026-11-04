package org.kalinajug.projekat_rsw.controllers.response;


import lombok.Data;
import org.kalinajug.projekat_rsw.model.ZadatakZaIzradu;

import java.util.List;
@Data
public class NastavnikResponse
{

    private String ime;
    private String prezime;
    private String email;
    private List<ZadatakZaIzradu> zadaciZaIzradu;
}
