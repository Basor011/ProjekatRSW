package org.kalinajug.projekat_rsw.services;


import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.exceptions.ConflictException;
import org.kalinajug.projekat_rsw.exceptions.ResourceNotFoundException;
import org.kalinajug.projekat_rsw.model.Predmet;
import org.kalinajug.projekat_rsw.model.Rok;
import org.kalinajug.projekat_rsw.repositories.PredmetRepo;
import org.kalinajug.projekat_rsw.repositories.RokRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PredmetService {
    private final PredmetRepo predmetRepo;

    @Transactional
    public Predmet save(String naziv){
        Predmet predmet = new Predmet();
        predmet.setNaziv(naziv);
        return predmetRepo.save(predmet);
    }

    @Transactional(readOnly = true)
    public List<Predmet> findAll(){
        return predmetRepo.findAllWithRokovi();
    }

    @Transactional
    public void deleteById(Long id){
        if (!predmetRepo.existsById(id))
            throw new ResourceNotFoundException("Ne postoji predmet sa id: " + id);
        predmetRepo.deleteById(id);
    }
}
