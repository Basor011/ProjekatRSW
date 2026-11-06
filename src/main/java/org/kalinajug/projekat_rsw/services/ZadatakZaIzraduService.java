package org.kalinajug.projekat_rsw.services;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.exceptions.ConflictException;
import org.kalinajug.projekat_rsw.exceptions.ResourceNotFoundException;
import org.kalinajug.projekat_rsw.model.Grupa;
import org.kalinajug.projekat_rsw.model.Nastavnik;
import org.kalinajug.projekat_rsw.model.ZadatakZaIzradu;
import org.kalinajug.projekat_rsw.repositories.GrupaRepo;
import org.kalinajug.projekat_rsw.repositories.NastavnikRepo;
import org.kalinajug.projekat_rsw.repositories.PredatiZadatakRepo;
import org.kalinajug.projekat_rsw.repositories.ZadatakZaIzraduRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ZadatakZaIzraduService {

    private final ZadatakZaIzraduRepo zadatakRepo;
    private final GrupaRepo grupaRepo;
    private final NastavnikRepo nastavnikRepo;
    private final PredatiZadatakRepo predatiZadatakRepo;
    private final FileStorageService fileStorageService;

    @Transactional
    public ZadatakZaIzradu save (Long grupaId, Long nastavnikId, String naziv, String opis, MultipartFile file) throws IOException {
        Grupa grupa = grupaRepo.findById(grupaId).orElseThrow(() -> new ResourceNotFoundException("Ne postoji grupa sa id " + grupaId));
        Nastavnik nastavnik = nastavnikRepo.findById(nastavnikId).orElseThrow(()-> new ResourceNotFoundException("Ne postoji nastavnik sa id " + nastavnikId));

        if(zadatakRepo.existsByGrupaId(grupaId)) throw new ConflictException("Za grupu " + grupaId + " vec postoji fajl.");

        String putanja = fileStorageService.save(file, "zadaci/grupa-" + grupaId);

        ZadatakZaIzradu z = new ZadatakZaIzradu();
        z.setNaziv(naziv);
        z.setOpis(opis);
        z.setGrupa(grupa);
        z.setNastavnik(nastavnik);
        z.setZadatakPath(putanja);
        z.setOriginalniNaziv(file.getOriginalFilename());
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
