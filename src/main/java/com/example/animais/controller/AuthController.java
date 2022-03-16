package com.example.animais.controller;

import com.example.animais.model.Usuario;
import com.example.animais.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody Usuario usuario){
        UsernamePasswordAuthenticationToken dados = usuario.converter();
        try {
            Authentication authentication = authManager.authenticate(dados);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok().build();
        } catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
