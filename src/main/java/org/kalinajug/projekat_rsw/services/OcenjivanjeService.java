package org.kalinajug.projekat_rsw.services;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.exceptions.BadRequestException;
import org.kalinajug.projekat_rsw.exceptions.ResourceNotFoundException;
import org.kalinajug.projekat_rsw.model.Komentar;
import org.kalinajug.projekat_rsw.model.PredatiZadatak;
import org.kalinajug.projekat_rsw.repositories.KomentarRepo;
import org.kalinajug.projekat_rsw.repositories.PredatiZadatakRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OcenjivanjeService {
    private final KomentarRepo komentarRepo;
    private final PredatiZadatakRepo predatiZadatakRepo;

    @Transactional
    public Komentar saveKomentar(Long predatiId, String path, Integer pocetniRed, Integer krajnjiRed, String tekst) {
        PredatiZadatak predatiZadatak = predatiZadatakRepo.findById(predatiId).orElseThrow(()-> new ResourceNotFoundException("Predati zadatak sa id " + predatiId + " ne postoji"));

        if(predatiZadatak.getVremePredaje() == null) {
            throw new BadRequestException("Student nije predao resenje!");
        }
        if(pocetniRed == null || krajnjiRed == null || pocetniRed < 1 || krajnjiRed < pocetniRed) {
            throw new BadRequestException("Los interval linija komentara!");
        }
        Komentar k = new Komentar();
        k.setPredatiZadatak(predatiZadatak);
        k.setPath(path);
        k.setPocetniRed(pocetniRed);
        k.setKrajnjiRed(krajnjiRed);
        k.setTekst(tekst);
        return komentarRepo.save(k);
    }

    @Transactional
    public PredatiZadatak savePoene(Long predatiId, Double poeni) {
        if (poeni == null || poeni < 0) {
            throw new BadRequestException("Poeni ne mogu biti negativni");
        }
        PredatiZadatak predati = predatiZadatakRepo.findById(predatiId).orElseThrow(()-> new ResourceNotFoundException("Predati zadatak sa id " + predatiId + " ne postoji."));
        predati.setPoeni(poeni);
        return predatiZadatakRepo.save(predati);
    }

    @Transactional(readOnly = true)
    public List<Komentar> findKomentarByPredatiZadatakId(Long predatiId) {
        if(!predatiZadatakRepo.existsById(predatiId)) {
            throw new ResourceNotFoundException("Predati zadatak sa id " + predatiId + " ne postoji.");
        }
        return komentarRepo.findByPredatiZadatakId(predatiId);
    }

    @Transactional(readOnly = true)
    public List<Komentar> findKomentarByStudentAndZadatak(Long predatiId, Long studentId) {
        predatiZadatakRepo.findByIdAndStudentId(predatiId, studentId).orElseThrow(() -> new ResourceNotFoundException("Resenje ne postoji"));
        return komentarRepo.findByPredatiZadatakId(predatiId);
    }

    @Transactional
    public void deleteKomentar(Long komentarId) {
        if (!komentarRepo.existsById(komentarId))
            throw new ResourceNotFoundException("Komentar ne postoji");
        komentarRepo.deleteById(komentarId);
    }

    @Transactional(readOnly = true)
    public PredatiZadatak findMoje(Long predatiId, Long studentId) {
        return predatiZadatakRepo.findByIdAndStudentId(predatiId, studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Resenje ne postoji"));
    }
}
