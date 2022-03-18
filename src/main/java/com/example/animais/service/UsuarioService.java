package com.example.animais.service;

import com.example.animais.dto.UsuarioRetornoDTO;
import com.example.animais.dto.UsuariosDTO;
import com.example.animais.model.Usuarios;
import com.example.animais.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;


    public ResponseEntity<Usuarios> saveUsuario(UsuariosDTO usuariosDTO, UriComponentsBuilder uriBuilder) {
        Usuarios usuario = modelMapper.map(usuariosDTO, Usuarios.class);
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        URI uri = uriBuilder.path("usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<UsuarioRetornoDTO> getUsuario(String id) {
        Optional<Usuarios> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()){
            UsuarioRetornoDTO usuarioRetornoDTO = modelMapper.map(usuario, UsuarioRetornoDTO.class);
            return ResponseEntity.ok(usuarioRetornoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    public Page<UsuarioRetornoDTO> findAll(Pageable pageable) {
        return new UsuarioRetornoDTO().conversorPage(usuarioRepository.findAll(pageable));
    }

    public ResponseEntity<?> deleteUsuario(String id) {
        Optional<Usuarios> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
