package org.kalinajug.projekat_rsw;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.model.*;
import org.kalinajug.projekat_rsw.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {

    private final GrupaRepo grupaRepo;
    private final KomentarRepo komentarRepo;
    private final ListaPrijavljenihRepo listaPrijavljenihRepo;
    private final NastavnikRepo nastavnikRepo;
    private final PredatiZadatakRepo predatiZadatakRepo;
    private final PredmetRepo predmetRepo;
    private final RokRepo rokRepo;
    private final StudentRepo studentRepo;
    private final TerminRepo terminRepo;
    private final ZadatakZaIzraduRepo zadatakZaIzraduRepo;

    private final PasswordEncoder passwordEncoder;
    private final Environment env;


    @Override
    public void run(String... args) throws Exception {

        if(rokRepo.count()>0) return; //// DODAJ ATRIBUTE U FOROVE

        List<Grupa> gpList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Grupa gp = new Grupa();
            gp.setBrojGrupe(i+100);
            // gp.setTermin(); za termin pogledaj kasnije posto treba da se prosledi
            // gp.setZadaciZaIzradu();
            gpList.add(gp);
        }
        grupaRepo.saveAll(gpList);

        List<Komentar> komentarList = new ArrayList<>();
        for(int i=0;i<=5;i++)
        {
            Komentar k = new Komentar();
            k.setKomentar("komentar " + i);
            k.setPath("/komentarpath " + i);
            k.setPocetniRed(i);
            k.setKrajnjiRed(i+i);
            komentarList.add(k);
        }
        komentarRepo.saveAll(komentarList);

        List<ListaPrijavljenih> listePrijavljenih = new ArrayList<>();
        for(int i=0;i<=5;i++)
        {
            ListaPrijavljenih lp = new ListaPrijavljenih();
            // lp.setStudent();
            // lp.setTermin();
            listePrijavljenih.add(lp);
        }
        listaPrijavljenihRepo.saveAll(listePrijavljenih);

        List<Nastavnik> nastavnikList= new ArrayList<>();
        for(int i=0;i<=5;i++)
        {
            Nastavnik n = new Nastavnik();
            n.setEmail("nastavnik" + i + "@gmail.com");
            n.setIme("nastavnik" + i);
            //n.setZadaciZaIzradu();
            nastavnikList.add(n);
        }
        nastavnikRepo.saveAll(nastavnikList);

        List<PredatiZadatak> predatiZadatakList = new ArrayList<>();
        for(int i=0;i<=5;i++)
        {
            PredatiZadatak p = new PredatiZadatak();
            //p.setKomentari();
            p.setPoeni((i*2 + 100) /2);
            //p.setStudent();
            //p.setZadatakZaIzradu();
            predatiZadatakList.add(p);
        }
        predatiZadatakRepo.saveAll(predatiZadatakList);

        List<Predmet> predmetList= new ArrayList<>();
        for(int i=0;i<=5;i++)
        {
            Predmet p = new Predmet();
            p.setNaziv("predmet " + i);
            //p.setRokovi();
            predmetList.add(p);
        }
        predmetRepo.saveAll(predmetList);

        List<Rok> rokList = new ArrayList<>();
        for(int i=0;i<=5;i++)
        {
            Rok r = new Rok();
            r.setNaziv("rok " + i);
            //r.setTermini();
            //r.setPredmet();
            //r.setTipRoka();
            rokList.add(r);
        }
        rokRepo.saveAll(rokList);

        List<Student> studentList = new ArrayList<>();
        for(int i=0;i<=5;i++)
        {
            Student s = new Student();
            s.setEmail("student" + i + "@gmail.com");
            s.setIme("student" + i);
            s.setSmer("SI");
            s.setGodinaUpisa(202 + i);
            s.setBrIndeksa(i);
            s.setPrezime("studenkovic");
            //s.setListePrijavljenih();
            studentList.add(s);
        }
        studentRepo.saveAll(studentList);

        List<Termin> terminList = new ArrayList<>();
        for(int i=0;i<=5;i++)
        {
            Termin t = new Termin();
            //t.setGrupe();
            //t.setRok();
            //t.setKraj(); zab kako
            //t.setPocetak(); zab
            //t.setListePrijavljenih();
            terminList.add(t);
        }
        terminRepo.saveAll(terminList);

        List<ZadatakZaIzradu> zadatakZaIzraduList= new ArrayList<>();
        for(int i=0;i<=5;i++)
        {
            ZadatakZaIzradu z = new ZadatakZaIzradu();
            //z.setGrupa();
            //z.setNastavnik();
            //z.setPredatiZadaci();
            zadatakZaIzraduList.add(z);
        }
        zadatakZaIzraduRepo.saveAll(zadatakZaIzraduList);
    }
}
