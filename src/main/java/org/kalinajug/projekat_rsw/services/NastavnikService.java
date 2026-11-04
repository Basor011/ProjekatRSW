package org.kalinajug.projekat_rsw.services;


import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.model.Nastavnik;
import org.kalinajug.projekat_rsw.repositories.NastavnikRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NastavnikService {

    final NastavnikRepo nastavnikRepository;

    public Nastavnik save(Nastavnik nastavnik) {
        return nastavnikRepository.save(nastavnik);
    }

    public List<Nastavnik> findAll() {
        return nastavnikRepository.findAll();
    }

    public Optional<Nastavnik> findById(Long id) {
        return nastavnikRepository.findById(id);
    }

    public List<Nastavnik> findByImeAndPrezime(String ime, String prezime) {
        return nastavnikRepository.findByImeAndPrezime(ime, prezime);
    }

}
