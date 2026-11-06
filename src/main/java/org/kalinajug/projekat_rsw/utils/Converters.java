package org.kalinajug.projekat_rsw.utils;

import org.kalinajug.projekat_rsw.controllers.request.*;
import org.kalinajug.projekat_rsw.controllers.response.*;
import org.kalinajug.projekat_rsw.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public final class Converters {

    private Converters() {}


    public static <E, R> List<R> toList(Iterable<E> entities, Function<E, R> converter) {
        List<R> rez = new ArrayList<>();
        entities.forEach(e -> rez.add(converter.apply(e)));
        return rez;
    }
    /*
    public static LoginResponse toLoginResponse(Prijava prijava) {
        return new LoginResponse(prijava.token(), prijava.istice(), prijava.uloga());
    }

     */


    /// NASTAVNIK

    public static Nastavnik toNastavnik(NastavnikRequest r) {
        Nastavnik n = new Nastavnik();
        n.setIme(r.getIme());
        n.setPrezime(r.getPrezime());
        n.setEmail(r.getEmail());
        return n;
    }

    public static void updateNastavnik(Nastavnik n, NastavnikRequest r) {
        n.setIme(r.getIme());
        n.setPrezime(r.getPrezime());
        n.setEmail(r.getEmail());
        // lozinka se menja posebnom operacijom u servisu
    }

    public static NastavnikResponse toNastavnikResponse(Nastavnik n) {
        NastavnikResponse res = new NastavnikResponse();
        res.setId(n.getId());
        res.setIme(n.getIme());
        res.setPrezime(n.getPrezime());
        res.setEmail(n.getEmail());
        res.setZadaciIds(toList(n.getZadaciZaIzradu(), ZadatakZaIzradu::getId));
        return res;
    }

    public static List<NastavnikResponse> toNastavnikResponseList(Iterable<Nastavnik> nastavnikIterable) {
        return toList(nastavnikIterable, Converters::toNastavnikResponse);
    }

/// STUDENT 3

    public static Student toStudent(StudentRequest r) {
        Student s = new Student();
        s.setIme(r.getIme());
        s.setPrezime(r.getPrezime());
        s.setEmail(r.getEmail());
        s.setBrIndeksa(r.getBrIndeksa());
        s.setSmer(r.getSmer());
        s.setGodinaUpisa(r.getGodinaUpisa());
        return s;
    }

    public static void updateStudent(Student s, StudentRequest r) {
        s.setIme(r.getIme());
        s.setPrezime(r.getPrezime());
        s.setEmail(r.getEmail());
        s.setBrIndeksa(r.getBrIndeksa());
        s.setSmer(r.getSmer());
        s.setGodinaUpisa(r.getGodinaUpisa());
    }

    public static StudentResponse toStudentResponse(Student s) {
        StudentResponse res = new StudentResponse();
        res.setId(s.getId());
        res.setIme(s.getIme());
        res.setPrezime(s.getPrezime());
        res.setEmail(s.getEmail());
        res.setBrIndeksa(s.getBrIndeksa());
        res.setSmer(s.getSmer());
        res.setGodinaUpisa(s.getGodinaUpisa());
        return res;
    }



    public static Predmet toPredmet(PredmetRequest r) {
        Predmet p = new Predmet();
        p.setNaziv(r.getNaziv());
        return p;
    }

    public static void updatePredmet(Predmet p, PredmetRequest r) {
        p.setNaziv(r.getNaziv());
    }

    public static PredmetResponse toPredmetResponse(Predmet p) {
        PredmetResponse res = new PredmetResponse();
        res.setId(p.getId());
        res.setNaziv(p.getNaziv());
        res.setRokoviIds(toList(p.getRokovi(), Rok::getId));
        return res;
    }


    public static Rok toRok(RokRequest r, Predmet predmet) {
        Rok rok = new Rok();
        rok.setNaziv(r.getNaziv());
        rok.setTipRoka(r.getTipRoka());
        rok.setPredmet(predmet);
        return rok;
    }

    public static void updateRok(Rok rok, RokRequest r) {
        rok.setNaziv(r.getNaziv());
        rok.setTipRoka(r.getTipRoka());
    }

    public static RokResponse toRokResponse(Rok rok) {
        RokResponse res = new RokResponse();
        res.setId(rok.getId());
        res.setNaziv(rok.getNaziv());
        res.setTipRoka(rok.getTipRoka());
        res.setPredmetId(rok.getPredmet() != null ? rok.getPredmet().getId() : null);
        res.setTerminiIds(toList(rok.getTermini(), Termin::getId));
        return res;
    }



    public static Termin toTermin(TerminRequest r, Rok rok) {
        Termin t = new Termin();
        t.setPocetak(r.getPocetak());
        t.setKraj(r.getKraj());
        t.setRok(rok);
        return t;
    }

    public static void updateTermin(Termin t, TerminRequest r) {
        t.setPocetak(r.getPocetak());
        t.setKraj(r.getKraj());
    }

    public static TerminResponse toTerminResponse(Termin t) {
        TerminResponse res = new TerminResponse();
        res.setId(t.getId());
        res.setPocetak(t.getPocetak());
        res.setKraj(t.getKraj());
        res.setRokId(t.getRok() != null ? t.getRok().getId() : null);
        res.setGrupeIds(toList(t.getGrupe(), Grupa::getId));
        return res;
    }



    public static Grupa toGrupa(GrupaRequest r, Termin termin) {
        Grupa g = new Grupa();
        g.setBrojGrupe(r.getBrojGrupe());
        g.setTermin(termin);
        return g;
    }

    public static void updateGrupa(Grupa g, GrupaRequest r) {
        g.setBrojGrupe(r.getBrojGrupe());
    }

    public static GrupaResponse toGrupaResponse(Grupa g) {
        GrupaResponse res = new GrupaResponse();
        res.setId(g.getId());
        res.setBrojGrupe(g.getBrojGrupe());
        res.setTerminId(g.getTermin() != null ? g.getTermin().getId() : null);
        res.setZadaciIds(toList(g.getZadaciZaIzradu(), ZadatakZaIzradu::getId));
        return res;
    }



    public static ListaPrijavljenih toListaPrijavljenih(Student student, Termin termin) {
        ListaPrijavljenih l = new ListaPrijavljenih();
        l.setStudent(student);
        l.setTermin(termin);
        return l;
    }

    public static ListaPrijavljenihResponse tolistaPrijavljenihResponse(ListaPrijavljenih l) {
        ListaPrijavljenihResponse res = new ListaPrijavljenihResponse();
        res.setId(l.getListaPrijavljenihid());
        res.setStudentId(l.getStudent().getId());
        res.setTerminId(l.getTermin().getId());
        return res;
    }



    public static ZadatakZaIzradu toZadatakZaIzradu(ZadatakZaIzraduRequest r, Nastavnik nastavnik, Grupa grupa) {
        ZadatakZaIzradu z = new ZadatakZaIzradu();
        z.setNaziv(r.getNaziv());
        z.setOriginalniNaziv(r.getOriginalniNaziv());
        z.setOpis(z.getOpis());
        z.setZadatakPath(r.getZadatakPath());
        z.setNastavnik(nastavnik);
        z.setGrupa(grupa);
        return z;
    }

    public static ZadatakZaIzraduResponse toZadatakZaIzraduResponse(ZadatakZaIzradu z) {
        ZadatakZaIzraduResponse res = new ZadatakZaIzraduResponse();
        res.setId(z.getId());
        res.setGrupaId(z.getGrupa().getId());
        res.setNastavnikId(z.getNastavnik().getId());
        return res;
    }



    public static PredatiZadatak toPredatiZadatak(PredatiZadatakRequest r, ZadatakZaIzradu zadatak, Student student) {
        PredatiZadatak p = new PredatiZadatak();
        p.setZadatakZaIzradu(zadatak);
        p.setStudent(student);
        p.setPoeni(r.getPoeni());
        return p;
    }

    public static void updatePredatiZadatak(PredatiZadatak p, PredatiZadatakRequest r) {
        p.setPoeni(r.getPoeni());
    }

    public static PredatiZadatakResponse toPredatiZadatakResponse(PredatiZadatak p) {
        PredatiZadatakResponse res = new PredatiZadatakResponse();
        res.setId(p.getId());
        res.setPoeni(p.getPoeni());
        res.setStudentId(p.getStudent().getId());
        res.setZadatakZaIzraduId(p.getZadatakZaIzradu().getId());
        res.setKomentari(toList(p.getKomentari(), Converters::toKomentarResponse));
        return res;
    }



    public static Komentar toKomentar(KomentarRequest r, PredatiZadatak predatiZadatak) {
        Komentar k = new Komentar();
        k.setPath(r.getPath());
        k.setPocetniRed(r.getPocetniRed());
        k.setKrajnjiRed(r.getKrajnjiRed());
        k.setTekst(r.getKomentar());
        k.setPredatiZadatak(predatiZadatak);
        return k;
    }

    public static void updateKomentar(Komentar k, KomentarRequest r) {
        k.setPath(r.getPath());
        k.setPocetniRed(r.getPocetniRed());
        k.setKrajnjiRed(r.getKrajnjiRed());
        k.setTekst(r.getKomentar());
    }

    public static KomentarResponse toKomentarResponse(Komentar k) {
        KomentarResponse res = new KomentarResponse();
        res.setId(k.getId());
        res.setPath(k.getPath());
        res.setPocetniRed(k.getPocetniRed());
        res.setKrajnjiRed(k.getKrajnjiRed());
        res.setKomentar(k.getTekst());
        res.setPredatiZadatakId(k.getPredatiZadatak().getId());
        return res;
    }
}