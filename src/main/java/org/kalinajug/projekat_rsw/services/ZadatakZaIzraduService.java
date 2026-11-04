package org.kalinajug.projekat_rsw.services;

import lombok.RequiredArgsConstructor;
import org.kalinajug.exceptions.ConflictException;
import org.kalinajug.exceptions.ResourceNotFoundException;
import org.kalinajug.projekat_rsw.model.Grupa;
import org.kalinajug.projekat_rsw.model.Nastavnik;
import org.kalinajug.projekat_rsw.model.ZadatakZaIzradu;
import org.kalinajug.projekat_rsw.repositories.GrupaRepo;
import org.kalinajug.projekat_rsw.repositories.NastavnikRepo;
import org.kalinajug.projekat_rsw.repositories.PredatiZadatakRepo;
import org.kalinajug.projekat_rsw.repositories.ZadatakZaIzraduRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Service
@RequiredArgsConstructor
public class ZadatakZaIzraduService {

    private final ZadatakZaIzraduRepo zadatakRepo;
    private final GrupaRepo grupaRepo;
    private final NastavnikRepo nastavnikRepo;
    private final PredatiZadatakRepo predatiZadatakRepo;

    @Transactional
    public ZadatakZaIzradu save (Long grupaId, Long nastavnikId) {
        Grupa grupa = grupaRepo.findById(grupaId).orElseThrow(() -> new ResourceNotFoundException("Ne postoji grupa sa id " + grupaId));
        Nastavnik nastavnik = nastavnikRepo.findById(nastavnikId).orElseThrow(()-> new ResourceNotFoundException("Ne postoji nastavnik sa id " + nastavnikId));
        ZadatakZaIzradu z = new ZadatakZaIzradu();
        z.setGrupa(grupa);
        z.setNastavnik(nastavnik);
        return zadatakRepo.save(z);
    }
    @Transactional
    public void deleteById(Long id) {
        if (!zadatakRepo.existsById(id))
            throw new ResourceNotFoundException("Ne postoji zadatak sa id: " + id);
        if (predatiZadatakRepo.existsByZadatakZaIzraduId(id))
            throw new ConflictException("Zadatak " + id + " ima predata resenja");
        zadatakRepo.deleteById(id);
    }
}
