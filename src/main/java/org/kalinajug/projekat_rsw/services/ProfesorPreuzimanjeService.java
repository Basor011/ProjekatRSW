package org.kalinajug.projekat_rsw.services;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.exceptions.ResourceNotFoundException;
import org.kalinajug.projekat_rsw.model.PredatiZadatak;
import org.kalinajug.projekat_rsw.model.Termin;
import org.kalinajug.projekat_rsw.repositories.PredatiZadatakRepo;
import org.kalinajug.projekat_rsw.repositories.TerminRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@RequiredArgsConstructor
public class ProfesorPreuzimanjeService {
    private final PredatiZadatakRepo predatiZadatakRepo;
    private final TerminRepo terminRepo;
    private final FileStorageService fileStorageService;

    public record ResenjeStudenta(Long predatiId, Long studentId, int brojGrupe, String ime, String prezime, Integer brIndeksa, LocalDateTime vremePredaje, Double poeni, String resenjePath) {}
    public record Kriterijum(Long terminId, Integer brojGrupe, boolean samoPredat, boolean samoNeocenjen){}

    @Transactional(readOnly = true)
    public List<ResenjeStudenta> pronadji(Kriterijum kriterijum){
        if(!terminRepo.existsById(kriterijum.terminId()))
            throw new ResourceNotFoundException("Termin sa zadatim id " + kriterijum.terminId + " ne postoji");

        return predatiZadatakRepo.pronadjiPoKriterijumima(kriterijum.terminId(), kriterijum.brojGrupe(), kriterijum.samoPredat(), kriterijum.samoNeocenjen()).stream().filter(p -> p.getResenjePath()!= null).map(this::mapiraj).toList();
    }

    public void zipuj(List<ResenjeStudenta> resenja, OutputStream out) throws IOException {
        Set<String> iskorisceno = new HashSet<>();
        try(ZipOutputStream zos = new ZipOutputStream(out)){
            for(ResenjeStudenta r : resenja){
                Path file = fileStorageService.resolve(r.resenjePath());
                if(!Files.exists(file)) continue;

                String naziv = "grupa-%d/%s_%s_%s".formatted(r.brojGrupe(), ocisti(r.prezime()), ocisti(r.ime()), r.brIndeksa() != null ? r.brIndeksa() : r.studentId());
                String entry = naziv + ".zip";
                for (int i = 2; !iskorisceno.add(entry); i++) entry = naziv + "_" + i + ".zip";

                zos.putNextEntry(new ZipEntry(entry));
                Files.copy(file, zos);
                zos.closeEntry();
            }
        }
    }
    private String ocisti(String s) {
        return s == null ? "x" : s.replaceAll("[^\\p{L}\\p{N}]", "");
    }
    private ResenjeStudenta mapiraj(PredatiZadatak p) {
        var s = p.getStudent();
        return new ResenjeStudenta(p.getId(), s.getId(), p.getZadatakZaIzradu().getGrupa().getBrojGrupe(), s.getIme(), s.getPrezime(), s.getBrIndeksa(), p.getVremePredaje(), p.getPoeni(), p.getResenjePath());
    }
}

