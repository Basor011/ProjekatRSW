package org.kalinajug.projekat_rsw.services;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.config.SecurityProperties;
import org.kalinajug.projekat_rsw.exceptions.UnAuthorizedException;
import org.kalinajug.projekat_rsw.model.Korisnik;
import org.kalinajug.projekat_rsw.model.TipKorisnika;
import org.kalinajug.projekat_rsw.repositories.KorisnikRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {
    public static final String CLAIM_ULOGA = "uloga";
    public static final String CLAIM_KORISNIK_ID = "korisnikId";
    private final KorisnikRepo korisnikRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;
    private final SecurityProperties securityProperties;

    public record Prijava(String token, Instant istice, TipKorisnika uloga){}

    @Transactional(readOnly = true)
    public Prijava login(String email, String password) {
        Korisnik korisnik = korisnikRepo.findByEmailIgnoreCase(email.trim()).filter(k->passwordEncoder.matches(password, k.getLozinka())).orElseThrow(()->new UnAuthorizedException("Pogresan email ili password"));
        Instant now = Instant.now();
        Instant istice = now.plus(securityProperties.jwtTrajanje());
        JwtClaimsSet claims = JwtClaimsSet.builder().issuer("projekat-rsw").subject(korisnik.getEmail()).issuedAt(now).expiresAt(istice).claim(CLAIM_ULOGA, korisnik.getTipKorisnika().name()).claim(CLAIM_KORISNIK_ID, korisnik.getId()).build();
        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
        String token = jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
        return new Prijava(token, istice, korisnik.getTipKorisnika());
    }
}
