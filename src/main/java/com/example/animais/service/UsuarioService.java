package com.example.animais.service;

import com.example.animais.dto.UsuarioRetornoDTO;
import com.example.animais.dto.UsuariosDTO;
import com.example.animais.model.Usuario;
import com.example.animais.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;


    public ResponseEntity<Usuario> saveUsuario(UsuariosDTO usuariosDTO) {
        Usuario usuario = modelMapper.map(usuariosDTO, Usuario.class);
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    public ResponseEntity<UsuarioRetornoDTO> getUsuario(String id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
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
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
