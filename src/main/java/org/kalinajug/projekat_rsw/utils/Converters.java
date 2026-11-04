package org.kalinajug.projekat_rsw.utils;

import org.kalinajug.projekat_rsw.controllers.request.*;
import org.kalinajug.projekat_rsw.model.*;
import org.kalinajug.projekat_rsw.repositories.GrupaRepo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class Converters {

    public static Nastavnik toNastavnik(NastavnikRequest request) {
        Nastavnik nastavnik = new Nastavnik();
        nastavnik.setIme(request.getIme());
        nastavnik.setPrezime(request.getPrezime());
        nastavnik.setEmail(request.getEmail());
        nastavnik.setLozinka(request.getLozinka());
        return nastavnik;
    }



    public static ZadatakZaIzradu toZadatakZaIzradu(ZadatakZaIzraduRequest request, Nastavnik nastavnik, Grupa grupa) {
        ZadatakZaIzradu zadatak = new ZadatakZaIzradu();
        zadatak.setGrupa(grupa);
        zadatak.setNastavnik(nastavnik);
        return zadatak;
    }



    public static PredatiZadatak toPredatiZadatak(PredatiZadatakRequest request, ZadatakZaIzradu zadatakZaIzradu, Student student) {
        PredatiZadatak predatiZadatak = new PredatiZadatak();
        predatiZadatak.setZadatakZaIzradu(zadatakZaIzradu);
        predatiZadatak.setStudent(student);
        predatiZadatak.setPoeni(request.getPoeni());
        return predatiZadatak;
    }

    public static Predmet toPredmet(PredmetRequest r) {
        Predmet p = new Predmet();
        p.setNaziv(r.getNaziv());
        return p;
    }

    public static Rok toRok(RokRequest r, Predmet predmet) {
        Rok rok = new Rok();
        rok.setNaziv(r.getNaziv());
        rok.setTipRoka(r.getTipRoka());      // enum TipRoka
        rok.setPredmet(predmet);
        return rok;
    }

    public static Termin toTermin(TerminRequest r, Rok rok) {
        Termin t = new Termin();
        t.setPocetak(r.getPocetak());
        t.setKraj(r.getKraj());
        t.setRok(rok);
        return t;
    }

    public static Grupa toGrupa(GrupaRequest r, Termin termin) {
        Grupa g = new Grupa();
        g.setBrojGrupe(r.getBrojGrupe());
        g.setTermin(termin);
        return g;
    }

    public static ListaPrijavljenih toListaPrijavljenih(Student student, Termin termin) {
        ListaPrijavljenih l = new ListaPrijavljenih();
        l.setStudent(student);
        l.setTermin(termin);
        return l;
    }

    public static Komentar toKomentar(KomentarRequest r, PredatiZadatak predatiZadatak) {
        Komentar k = new Komentar();
        k.setPath(r.getPath());
        k.setPocetniRed(r.getPocetniRed());
        k.setKrajnjiRed(r.getKrajnjiRed());
        k.setKomentar(r.getKomentar());
        k.setPredatiZadatak(predatiZadatak);
        return k;
    }

    // lozinku ovde prosledi vec heširanu
    public static Student toStudent(StudentRequest r, String hesiranaLozinka) {
        Student s = new Student();
        s.setIme(r.getIme());
        s.setPrezime(r.getPrezime());
        s.setEmail(r.getEmail());
        s.setLozinka(hesiranaLozinka);
        s.setBrIndeksa(r.getBrIndeksa());
        s.setSmer(r.getSmer());
        s.setGodinaUpisa(r.getGodinaUpisa());
        return s;
    }


}
