package org.kalinajug.projekat_rsw.services;


import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.exceptions.ResourceNotFoundException;
import org.kalinajug.projekat_rsw.model.Nastavnik;
import org.kalinajug.projekat_rsw.repositories.NastavnikRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NastavnikService {

    private final NastavnikRepo nastavnikRepository;

    public Nastavnik save(Nastavnik nastavnik) {
        return nastavnikRepository.save(nastavnik);
    }

    @Transactional(readOnly = true)
    public List<Nastavnik> findAll() {
        return nastavnikRepository.findAllWithZadaci();
    }

    @Transactional(readOnly = true)
    public Nastavnik findById(Long id) {
        return nastavnikRepository.findByIdWithZadaci(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ne postoji nastavnik sa id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Nastavnik> findByImeAndPrezime(String ime, String prezime) {
        return nastavnikRepository.findByImeAndPrezime(ime, prezime);
    }

}
