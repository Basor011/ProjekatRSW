package org.kalinajug.projekat_rsw.services;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.exceptions.ConflictException;
import org.kalinajug.projekat_rsw.exceptions.ResourceNotFoundException;
import org.kalinajug.projekat_rsw.model.Predmet;
import org.kalinajug.projekat_rsw.model.Rok;
import org.kalinajug.projekat_rsw.model.TipRoka;
import org.kalinajug.projekat_rsw.repositories.PredmetRepo;
import org.kalinajug.projekat_rsw.repositories.RokRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RokService {
    private final RokRepo rokRepo;
    private final PredmetRepo predmetRepo;

    @Transactional
    public Rok save(String naziv, TipRoka tipRoka, Long predmetId){
        Predmet predmet = predmetRepo.findById(predmetId).orElseThrow(()->new ResourceNotFoundException("Ne postoji predmet sa id " + predmetId));
        Rok rok = new Rok();
        rok.setNaziv(naziv);
        rok.setTipRoka(tipRoka);
        rok.setPredmet(predmet);
        return rokRepo.save(rok);
    }
    @Transactional(readOnly = true)
    public List<Rok> findByPredmetId(Long predmetId){
        if(!predmetRepo.existsById(predmetId)) throw new ResourceNotFoundException("Ne postoji predmet sa id " + predmetId);
        return rokRepo.findByPredmetId(predmetId);
    }
    @Transactional
    public void deleteById(Long id){
        if (!rokRepo.existsById(id))
            throw new ResourceNotFoundException("Ne postoji rok sa id: " + id);
        rokRepo.deleteById(id);
    }
}
