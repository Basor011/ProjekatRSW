package org.kalinajug.projekat_rsw.utils;

import org.kalinajug.projekat_rsw.controllers.request.*;
import org.kalinajug.projekat_rsw.controllers.response.*;
import org.kalinajug.projekat_rsw.model.*;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;


public final class Converters {

    private Converters() {}


    public static <T, R> List<R> toList(List<T> list, Function<T, R> mapper) {
        if (list == null) return Collections.emptyList();
        return list.stream().map(mapper).toList();
    }



    public static Nastavnik toNastavnik(NastavnikRequest r, String hesiranaLozinka) {
        Nastavnik n = new Nastavnik();
        n.setIme(r.getIme());
        n.setPrezime(r.getPrezime());
        n.setEmail(r.getEmail());
        n.setLozinka(hesiranaLozinka);
        return n;
    }

    public static void updateNastavnik(Nastavnik n, NastavnikRequest r) {
        n.setIme(r.getIme());
        n.setPrezime(r.getPrezime());
        n.setEmail(r.getEmail());
        // lozinka se menja posebnom operacijom u servisu
    }

    public static NastavnikResponse toResponse(Nastavnik n) {
        NastavnikResponse res = new NastavnikResponse();
        res.setId(n.getKorisnikid());
        res.setIme(n.getIme());
        res.setPrezime(n.getPrezime());
        res.setEmail(n.getEmail());
        res.setZadaciIds(toList(n.getZadaciZaIzradu(), ZadatakZaIzradu::getZadatakzaIzraduid));
        return res;
    }



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

    public static void updateStudent(Student s, StudentRequest r) {
        s.setIme(r.getIme());
        s.setPrezime(r.getPrezime());
        s.setEmail(r.getEmail());
        s.setBrIndeksa(r.getBrIndeksa());
        s.setSmer(r.getSmer());
        s.setGodinaUpisa(r.getGodinaUpisa());
    }

    public static StudentResponse toResponse(Student s) {
        StudentResponse res = new StudentResponse();
        res.setId(s.getKorisnikid());
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

    public static PredmetResponse toResponse(Predmet p) {
        PredmetResponse res = new PredmetResponse();
        res.setId(p.getPredmetid());
        res.setNaziv(p.getNaziv());
        res.setRokoviIds(toList(p.getRokovi(), Rok::getRokid));
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

    public static RokResponse toResponse(Rok rok) {
        RokResponse res = new RokResponse();
        res.setId(rok.getRokid());
        res.setNaziv(rok.getNaziv());
        res.setTipRoka(rok.getTipRoka());
        res.setPredmetId(rok.getPredmet() != null ? rok.getPredmet().getPredmetid() : null);
        res.setTerminiIds(toList(rok.getTermini(), Termin::getTerminid));
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

    public static TerminResponse toResponse(Termin t) {
        TerminResponse res = new TerminResponse();
        res.setId(t.getTerminid());
        res.setPocetak(t.getPocetak());
        res.setKraj(t.getKraj());
        res.setRokId(t.getRok() != null ? t.getRok().getRokid() : null);
        res.setGrupeIds(toList(t.getGrupe(), Grupa::getGroupid));
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

    public static GrupaResponse toResponse(Grupa g) {
        GrupaResponse res = new GrupaResponse();
        res.setId(g.getGroupid());
        res.setBrojGrupe(g.getBrojGrupe());
        res.setTerminId(g.getTermin() != null ? g.getTermin().getTerminid() : null);
        res.setZadaciIds(toList(g.getZadaciZaIzradu(), ZadatakZaIzradu::getZadatakzaIzraduid));
        return res;
    }



    public static ListaPrijavljenih toListaPrijavljenih(Student student, Termin termin) {
        ListaPrijavljenih l = new ListaPrijavljenih();
        l.setStudent(student);
        l.setTermin(termin);
        return l;
    }

    public static ListaPrijavljenihResponse toResponse(ListaPrijavljenih l) {
        ListaPrijavljenihResponse res = new ListaPrijavljenihResponse();
        res.setId(l.getListaPrijavljenihid());
        res.setStudentId(l.getStudent().getKorisnikid());
        res.setTerminId(l.getTermin().getTerminid());
        return res;
    }



    public static ZadatakZaIzradu toZadatakZaIzradu(ZadatakZaIzraduRequest r, Nastavnik nastavnik, Grupa grupa) {
        ZadatakZaIzradu z = new ZadatakZaIzradu();
        z.setGrupa(grupa);
        z.setNastavnik(nastavnik);
        return z;
    }

    public static ZadatakZaIzraduResponse toResponse(ZadatakZaIzradu z) {
        ZadatakZaIzraduResponse res = new ZadatakZaIzraduResponse();
        res.setId(z.getZadatakzaIzraduid());
        res.setGrupaId(z.getGrupa().getGroupid());
        res.setNastavnikId(z.getNastavnik().getKorisnikid());
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

    public static PredatiZadatakResponse toResponse(PredatiZadatak p) {
        PredatiZadatakResponse res = new PredatiZadatakResponse();
        res.setId(p.getPredatiZadatakid());
        res.setPoeni(p.getPoeni());
        res.setStudentId(p.getStudent().getKorisnikid());
        res.setZadatakZaIzraduId(p.getZadatakZaIzradu().getZadatakzaIzraduid());
        res.setKomentari(toList(p.getKomentari(), Converters::toResponse));
        return res;
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

    public static void updateKomentar(Komentar k, KomentarRequest r) {
        k.setPath(r.getPath());
        k.setPocetniRed(r.getPocetniRed());
        k.setKrajnjiRed(r.getKrajnjiRed());
        k.setKomentar(r.getKomentar());
    }

    public static KomentarResponse toResponse(Komentar k) {
        KomentarResponse res = new KomentarResponse();
        res.setId(k.getKomentarid());
        res.setPath(k.getPath());
        res.setPocetniRed(k.getPocetniRed());
        res.setKrajnjiRed(k.getKrajnjiRed());
        res.setKomentar(k.getKomentar());
        res.setPredatiZadatakId(k.getPredatiZadatak().getPredatiZadatakid());
        return res;
    }
}