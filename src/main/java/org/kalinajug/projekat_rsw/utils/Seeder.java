package org.kalinajug.projekat_rsw.utils;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.model.*;
import org.kalinajug.projekat_rsw.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {

    private final GrupaRepo grupaRepo;
    private final KomentarRepo komentarRepo;
    private final KomentarRepo korisnikRepo;
    private final ListaPrijavljenihRepo listaPrijavljenihRepo;
    private final NastavnikRepo nastavnikRepo;
    private final PredatiZadatakRepo predatiZadatakRepo;
    private final PredmetRepo predmetRepo;
    private final RokRepo rokRepo;
    private final StudentRepo studentRepo;
    private final TerminRepo terminRepo;
    private final ZadatakZaIzraduRepo zadatakZaIzraduRepo;


    @Override
    public void run(String... args) throws Exception {

        if(rokRepo.count()>0) return; //// DODAJ ATRIBUTE U FOROVE

        List<Grupa> gpList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Grupa gp = new Grupa();

            // ovde atributi
            gpList.add(gp);
        }

        List<Komentar> komentarList = new ArrayList<>();
        for(int i=0;i<=5;i++)
        {

        }

        List<Korisnik> korisnikList = new ArrayList<>();
        for(int i=0;i<=5;i++)
        {

        }

        List<ListaPrijavljenih> listaPrijavljenih = new ArrayList<>();
        for(int i=0;i<=5;i++)
        {

        }

        List<Nastavnik> nastavnikList= new ArrayList<>();
        for(int i=0;i<=5;i++)
        {

        }
        List<PredatiZadatak> predatiZadatakList = new ArrayList<>();
        for(int i=0;i<=5;i++)
        {

        }

        List<Predmet> predmetList= new ArrayList<>();
        for(int i=0;i<=5;i++)
        {

        }

        List<Rok> rokList = new ArrayList<>();
        for(int i=0;i<=5;i++)
        {

        }

        List<Student> studentList = new ArrayList<>();
        for(int i=0;i<=5;i++)
        {

        }

        List<Termin> terminList = new ArrayList<>();
        for(int i=0;i<=5;i++)
        {

        }

        List<ZadatakZaIzradu> zadatakZaIzraduList= new ArrayList<>();
        for(int i=0;i<=5;i++)
        {

        }






    }
}
