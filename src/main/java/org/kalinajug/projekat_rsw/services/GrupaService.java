package org.kalinajug.projekat_rsw.services;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.exceptions.ConflictException;
import org.kalinajug.projekat_rsw.exceptions.ResourceNotFoundException;
import org.kalinajug.projekat_rsw.model.Grupa;
import org.kalinajug.projekat_rsw.model.Termin;
import org.kalinajug.projekat_rsw.repositories.GrupaRepo;
import org.kalinajug.projekat_rsw.repositories.TerminRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GrupaService{
    //existsbyterminidandbrojgrupe

    private final GrupaRepo grupaRepo;
    private final TerminRepo terminRepo;

    @Transactional
    public Grupa save(Long terminId, Integer brojGrupe){
        Termin termin = terminRepo.findById(terminId).orElseThrow(()-> new ResourceNotFoundException("Termin " + terminId + "ne postoji"));

        if(grupaRepo.existsByTerminIdAndBrojGrupe(terminId, brojGrupe)){
            throw new ConflictException("Grupa " + brojGrupe + " vec postoji u terminu " + terminId);
        }
        Grupa grupa = new Grupa();
        grupa.setTermin(termin);
        grupa.setBrojGrupe(brojGrupe);
        return grupaRepo.save(grupa);
    }

    @Transactional(readOnly = true)
    public List<Grupa> findByTerminId(Long terminId){
        if(!terminRepo.existsById(terminId)){
            throw new ResourceNotFoundException("Termin " + terminId + " ne postoji");
        }
        return grupaRepo.findByTerminId(terminId);
    }
}
