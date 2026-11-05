package org.kalinajug.projekat_rsw.services;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.exceptions.ResourceNotFoundException;
import org.kalinajug.projekat_rsw.model.ListaPrijavljenih;
import org.kalinajug.projekat_rsw.model.Student;
import org.kalinajug.projekat_rsw.model.Termin;
import org.kalinajug.projekat_rsw.repositories.ListaPrijavljenihRepo;
import org.kalinajug.projekat_rsw.repositories.StudentRepo;
import org.kalinajug.projekat_rsw.repositories.TerminRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListaPrijavljenihService {
    private final ListaPrijavljenihRepo listaPrijavljenihRepo;
    private final TerminRepo terminRepo;
    private final StudentRepo studentRepo;

    public record RezultatUnosa(int uneto, int vecPrijavljeni, List<String> nepronadjeniEmailovi) {}

    @Transactional
    public RezultatUnosa unesiSpisak(Long terminId, List<String> emailovi){
        Termin termin = terminRepo.findById(terminId).orElseThrow(() -> new ResourceNotFoundException("Ne postoji termin sa id: " + terminId));

        List<String> distinct = emailovi.stream().map(String::trim).map(String::toLowerCase).distinct().toList();

        Map<String, Student> studenti = studentRepo.findByEmailIn(distinct).stream()    .collect(Collectors.toMap(s -> s.getEmail().toLowerCase(), Function.identity(), (a, b) -> a));

        List<String> nepronadjeni = distinct.stream().filter(e -> !studenti.containsKey(e)).toList();

        List<ListaPrijavljenih> zaCuvanje = new ArrayList<>();
        int vecPrijavljeni = 0;
        for (Student s : studenti.values()) {
            if (listaPrijavljenihRepo.existsByStudentIdAndTerminId(s.getId(), terminId)) {
                vecPrijavljeni++;
                continue;
            }
            ListaPrijavljenih lp = new ListaPrijavljenih();
            lp.setStudent(s);
            lp.setTermin(termin);
            zaCuvanje.add(lp);
        }
        listaPrijavljenihRepo.saveAll(zaCuvanje);
        return new RezultatUnosa(zaCuvanje.size(), vecPrijavljeni, nepronadjeni);
    }
}
