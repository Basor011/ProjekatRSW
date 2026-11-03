package org.kalinajug.projekat_rsw;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.model.*;
import org.kalinajug.projekat_rsw.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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

        List<Predmet> predmetList= new ArrayList<>();
        for(int i=0; i <= 5;i++)
        {
            Predmet p = new Predmet();
            p.setNaziv("predmet " + i);
            predmetList.add(predmetRepo.save(p));
        }

        List<Rok> rokList = new ArrayList<>();
        for(int i=0; i < predmetList.size();i++)
        {
            Rok r = new Rok();
            r.setNaziv("rok " + i);
            r.setPredmet(predmetList.get(i));
            r.setTipRoka(i%2==0 ? TipRoka.KOLOKVIJUM : TipRoka.ISPIT);
            rokList.add(rokRepo.save(r));
        }

        List<Termin> terminList = new ArrayList<>();
        LocalDateTime terminT  = LocalDateTime.now();
        for(int i=0; i < rokList.size();i++)
        {
            Termin t = new Termin();
            t.setRok(rokList.get(i));
            t.setKraj(terminT.plusDays(i).withHour(9));
            t.setPocetak(terminT.plusDays(i).withHour(12));
            terminList.add(terminRepo.save(t));
        }

        List<Grupa> gpList = new ArrayList<>();
        for (int i = 1; i < terminList.size(); i++) {
            Grupa gp = new Grupa();
            gp.setBrojGrupe(i+100);
            gp.setTermin(terminList.get(i-1));
            gpList.add(grupaRepo.save(gp));
        }

        String enkodovanaLozinka = passwordEncoder.encode("lozinka123");

        List<Nastavnik> nastavnikList= new ArrayList<>();
        for(int i=0; i<=5 ;i++)
        {
            Nastavnik n = new Nastavnik();
            n.setEmail("nastavnik" + i + "@gmail.com");
            n.setIme("Nastavnik");
            n.setPrezime("Nastavnikovic" + i);
            n.setLozinka(enkodovanaLozinka);
            nastavnikList.add(nastavnikRepo.save(n));
        }

        List<ZadatakZaIzradu> zadatakZaIzraduList= new ArrayList<>();
        for(int i=0; i < nastavnikList.size() && i < gpList.size();i++)
        {
            ZadatakZaIzradu z = new ZadatakZaIzradu();
            z.setGrupa(gpList.get(i));
            z.setNastavnik(nastavnikList.get(i));
            zadatakZaIzraduList.add(zadatakZaIzraduRepo.save(z));
        }

        List<Student> studentList = new ArrayList<>();
        for(int i=0; i<=5 ;i++)
        {
            Student s = new Student();
            s.setEmail("student" + i + "@gmail.com");
            s.setIme("Student");
            s.setSmer(i%2==0 ? "SI" : "RN");
            s.setGodinaUpisa(2020 + i);
            s.setBrIndeksa(i);
            s.setPrezime("Studenkovic" + i);
            s.setLozinka(enkodovanaLozinka);
            studentList.add(studentRepo.save(s));
        }

        List<ListaPrijavljenih> listePrijavljenih = new ArrayList<>();
        for(int i=0; i<terminList.size() && i < studentList.size();i++)
        {
            ListaPrijavljenih lp = new ListaPrijavljenih();
            lp.setStudent(studentList.get(i));
            lp.setTermin(terminList.get(i));
            listePrijavljenih.add(listaPrijavljenihRepo.save(lp));
        }

        List<PredatiZadatak> predatiZadatakList = new ArrayList<>();
        for(int i=0;i<zadatakZaIzraduList.size() && i < studentList.size();i++)
        {
            PredatiZadatak p = new PredatiZadatak();
            p.setPoeni((i + 100) /2);
            p.setStudent(studentList.get(i));
            p.setZadatakZaIzradu(zadatakZaIzraduList.get(i));
            predatiZadatakList.add(predatiZadatakRepo.save(p));
        }

        List<Komentar> komentarList = new ArrayList<>();
        for(int i=0;i<predatiZadatakList.size();i++)
        {
            Komentar k = new Komentar();
            k.setKomentar("komentar " + i);
            k.setPath("/komentarpath " + i);
            k.setPocetniRed(i);
            k.setKrajnjiRed(i+i);
            k.setPredatiZadatak(predatiZadatakList.get(i));
            komentarList.add(komentarRepo.save(k));
        }
    }
}
