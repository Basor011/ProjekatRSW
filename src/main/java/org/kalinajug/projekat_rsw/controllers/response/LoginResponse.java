package org.kalinajug.projekat_rsw.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.Instant;

    @Data
    @AllArgsConstructor
    public class LoginResponse {

        private String token;      // salje se kao "Authorization: Bearer <token>"
        private Instant istice;
        private String uloga;
    }

