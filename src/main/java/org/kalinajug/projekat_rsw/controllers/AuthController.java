package org.kalinajug.projekat_rsw.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.kalinajug.projekat_rsw.controllers.request.LoginRequest;
import org.kalinajug.projekat_rsw.controllers.response.LoginResponse;
import org.kalinajug.projekat_rsw.services.AuthService;
import org.kalinajug.projekat_rsw.utils.Converters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/auth")
public class AuthController {
    final AuthService authService;


    @PostMapping(path = "/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return Converters.toLoginResponse(authService.login(request.getEmail(), request.getLozinka()));
    }


}
