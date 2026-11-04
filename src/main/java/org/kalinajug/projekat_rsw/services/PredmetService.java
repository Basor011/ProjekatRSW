package org.kalinajug.projekat_rsw.services;


import lombok.AllArgsConstructor;
import org.kalinajug.projekat_rsw.model.Predmet;
import org.kalinajug.projekat_rsw.repositories.PredmetRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PredmetService {

    final PredmetRepo predmetRepository;

    public List<Predmet> findAll(){
        return predmetRepository.findAll();
    }


}
