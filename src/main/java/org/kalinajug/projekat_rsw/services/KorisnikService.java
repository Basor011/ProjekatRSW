package org.kalinajug.projekat_rsw.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.exceptions.BadRequestException;
import org.kalinajug.projekat_rsw.exceptions.ConflictException;
import org.kalinajug.projekat_rsw.model.Korisnik;
import org.kalinajug.projekat_rsw.model.Nastavnik;
import org.kalinajug.projekat_rsw.model.Student;
import org.kalinajug.projekat_rsw.repositories.KorisnikRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KorisnikService {
    private final KorisnikRepo korisnikRepo;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Student saveStudent(String ime, String prezime, String email, String password, Integer brIndeksa, String smer, Integer godinaUpisa) {
        proveriEmail(email);
        Student s = new Student();
        s.setIme(ime);
        s.setPrezime(prezime);
        s.setEmail(email);
        s.setLozinka(passwordEncoder.encode(password));
        s.setSmer(smer);
        s.setGodinaUpisa(godinaUpisa);
        s.setBrIndeksa(brIndeksa);
        return korisnikRepo.save(s);
    }

    @Transactional
    public Nastavnik saveNastavnik(String ime, String prezime, String email, String password){
        proveriEmail(email);
        Nastavnik n = new Nastavnik();
        n.setIme(ime);
        n.setPrezime(prezime);
        n.setEmail(email);
        n.setLozinka(passwordEncoder.encode(password));
        return korisnikRepo.save(n);
    }

    private void proveriEmail(String email) {
        if(korisnikRepo.existsByEmail(email)) throw new ConflictException("Nalog sa tim emailom vec postoji");
    }
}
