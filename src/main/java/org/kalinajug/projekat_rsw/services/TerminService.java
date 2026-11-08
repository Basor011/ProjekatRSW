package org.kalinajug.projekat_rsw.services;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.exceptions.BadRequestException;
import org.kalinajug.projekat_rsw.exceptions.ResourceNotFoundException;
import org.kalinajug.projekat_rsw.model.Rok;
import org.kalinajug.projekat_rsw.model.Student;
import org.kalinajug.projekat_rsw.model.Termin;
import org.kalinajug.projekat_rsw.repositories.RokRepo;
import org.kalinajug.projekat_rsw.repositories.TerminRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TerminService {
    //save(naziv,tiproka,premetId), findbyRok, deletebyId
    private final TerminRepo terminRepo;
    private final RokRepo rokRepo;

    @Transactional
    public Termin save(Long rokId, LocalDateTime start, LocalDateTime end) {
        Rok rok = rokRepo.findById(rokId).orElseThrow(()-> new ResourceNotFoundException("Rok sa id " + rokId + " ne postoji"));
        if(!end.isAfter(start)){
            throw new BadRequestException("Kraj termina je pre pocetka");
        }
        Termin termin = new Termin();
        termin.setRok(rok);
        termin.setPocetak(start);
        termin.setKraj(end);
        return terminRepo.save(termin);
    }

    @Transactional(readOnly = true)
    public List<Termin> findByRokId(Long rokId) {
        if(!rokRepo.existsById(rokId)){
            throw new ResourceNotFoundException("Ne postoji rok sa id " + rokId);
        }
        return terminRepo.findByRokId(rokId);
    }

    @Transactional
    public void deleteById(Long terminId) {
        if(!terminRepo.existsById(terminId)){
            throw new ResourceNotFoundException("Ne postoji termin sa id " + terminId);
        }
        terminRepo.deleteById(terminId);
    }
    public List<Termin> findAll() {
        return terminRepo.findAllWithListaPrijavljenih();
    }
}
