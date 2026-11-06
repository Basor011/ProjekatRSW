package org.kalinajug.projekat_rsw.services;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.exceptions.ConflictException;
import org.kalinajug.projekat_rsw.model.PredatiZadatak;
import org.kalinajug.projekat_rsw.model.Termin;
import org.kalinajug.projekat_rsw.repositories.PredatiZadatakRepo;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PredajaService {
    private final PredatiZadatakRepo predatiRepo;
    private final FileStorageService fileStorageService;

    @Transactional
    public PredatiZadatak predaj(Long studentId, Long zadatakId, MultipartFile resenje) throws IOException {
        PredatiZadatak p = predatiRepo.findByStudentIdAndZadatakZaIzraduId(studentId, zadatakId).orElseThrow(() -> new ConflictException("Zadatak nije preuzet"));

        Termin termin = p.getZadatakZaIzradu().getGrupa().getTermin();
        if (LocalDateTime.now().isAfter(termin.getKraj())) throw new ConflictException("Termin je istekao");

        String stara = p.getResenjePath();
        String nova = fileStorageService.save(resenje,"resenja/zadatak-" + zadatakId + "/student-" + studentId);

        p.setResenjePath(nova);
        p.setVremePredaje(LocalDateTime.now());
        PredatiZadatak sacuvan = predatiRepo.save(p);

        if (stara != null) fileStorageService.delete(stara);
        return sacuvan;
    }
}
