package org.kalinajug.projekat_rsw.services;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.repositories.ListaPrijavljenihRepo;
import org.kalinajug.projekat_rsw.repositories.PredatiZadatakRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MonitoringService {
    private final PredatiZadatakRepo predatiZadatakRepo;
    private final ListaPrijavljenihRepo listaPrijavljenihRepo;

    public  record  Statistika(long prijavljeno, long preuzelo, long predalo, Double prosecnoMinuta){}
    public record RedStudenta(Long studentId, String ime, String prezime, Integer brIndexa, LocalDateTime preuzeto, LocalDateTime predato, Long minuta){ }

    @Transactional(readOnly = true)
    public Statistika statistika(Long terminId){
        long prijavljeno = listaPrijavljenihRepo.countByTerminId(terminId);
        long preuzeto = predatiZadatakRepo.countByZadatakZaIzraduGrupaTerminId(terminId);
        long predalo = predatiZadatakRepo.countByZadatakZaIzraduGrupaTerminIdAndVremePredajeNotNull(terminId);
        Double prosek = predatiZadatakRepo.prosecnoVremeIzrade(terminId);
        return new Statistika(prijavljeno, preuzeto, predalo, prosek);
    }

    @Transactional(readOnly = true)
    public List<RedStudenta> pregled(Long terminId){
        return predatiZadatakRepo.findByZadatakZaIzraduGrupaTerminId(terminId).stream().map(p -> new RedStudenta(p.getStudent().getId(), p.getStudent().getIme(), p.getStudent().getPrezime(), p.getStudent().getBrIndeksa(), p.getVremePreuzimanja(), p.getVremePredaje(), p.getVremePredaje() == null ? null : Duration.between(p.getVremePreuzimanja(), p.getVremePredaje()).toMinutes())).toList();
    }
}
