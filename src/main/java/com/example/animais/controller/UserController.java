package com.example.animais.controller;

import com.example.animais.controller.api.UsuarioApi;
import com.example.animais.model.Usuarios;
import com.example.animais.repository.UsuarioRepository;
import com.example.animais.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UserController implements UsuarioApi {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<Usuarios> postUsuario(@RequestBody Usuarios usuario, UriComponentsBuilder uriBuilder){

        return usuarioService.saveUsuario(usuario, uriBuilder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> getUsuario(@PathVariable String id){

        return usuarioService.getUsuario(id);
    }

    @GetMapping
    public Page<Usuarios> getUsuarios(Pageable pageable){
        return usuarioService.findAll(pageable);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> deleteUsuario(@PathVariable String id){
        return usuarioService.deleteUsuario(id);
    }


}
