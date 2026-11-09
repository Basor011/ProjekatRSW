package org.kalinajug.projekat_rsw.services;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.exceptions.ConflictException;
import org.kalinajug.projekat_rsw.exceptions.ResourceNotFoundException;
import org.kalinajug.projekat_rsw.model.PredatiZadatak;
import org.kalinajug.projekat_rsw.model.Termin;
import org.kalinajug.projekat_rsw.model.ZadatakZaIzradu;
import org.kalinajug.projekat_rsw.repositories.ListaPrijavljenihRepo;
import org.kalinajug.projekat_rsw.repositories.PredatiZadatakRepo;
import org.kalinajug.projekat_rsw.repositories.StudentRepo;
import org.kalinajug.projekat_rsw.repositories.ZadatakZaIzraduRepo;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StudentPreuzimanjeService {

    private final ZadatakZaIzraduRepo zadatakZaIzraduRepo;
    private final ListaPrijavljenihRepo listaPrijavljenihRepo;
    private final PredatiZadatakRepo predatiZadatakRepo;
    private final StudentRepo studentRepo;
    private final FileStorageService fileStorageService;

    public record Preuzimanje(Resource file, String naziv){}

    @Transactional
    public Preuzimanje preuzmi(Long studentId, Long terminId, Integer brGrupe) {
        ZadatakZaIzradu zadatak = zadatakZaIzraduRepo.findByGrupaTerminIdAndGrupaBrojGrupe(terminId, brGrupe).orElseThrow(()-> new ResourceNotFoundException("ne postoji zadatak sa id " + studentId));

        Termin termin = zadatak.getGrupa().getTermin();
        LocalDateTime sada = LocalDateTime.now();

        if(sada.isBefore(termin.getPocetak()) || sada.isAfter(termin.getKraj())){
            throw new ConflictException("Termin nije u toku");
        }
        if(termin.getRok().isIspit() && !listaPrijavljenihRepo.existsByStudentIdAndTerminId(studentId, terminId)){
            throw new ConflictException("Student nije na spisku za polaganje");
        }
        predatiZadatakRepo.findByStudentIdAndZadatakZaIzraduId(studentId, zadatak.getId()).orElseGet(()->{
            PredatiZadatak p = new PredatiZadatak();
            p.setStudent(studentRepo.getReferenceById(studentId));
            p.setZadatakZaIzradu(zadatak);
            p.setVremePreuzimanja(sada);
            return predatiZadatakRepo.save(p);
        });
        return new Preuzimanje(fileStorageService.load(zadatak.getZadatakPath()), zadatak.getOriginalniNaziv());
    }
}
