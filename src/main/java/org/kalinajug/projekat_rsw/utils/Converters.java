package org.kalinajug.projekat_rsw.utils;

import org.kalinajug.projekat_rsw.controllers.request.NastavnikRequest;
import org.kalinajug.projekat_rsw.model.Nastavnik;
import org.kalinajug.projekat_rsw.model.ZadatakZaIzradu;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Converters {

    public static <E, R> List<R> toList(Iterable<E> entities, Function<E, R> converter) {
        List<R> rez = new ArrayList<>();
        entities.forEach(e -> rez.add(converter.apply(e)));
        return rez;
    }


}
