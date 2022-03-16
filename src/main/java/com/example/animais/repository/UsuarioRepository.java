package com.example.animais.repository;

import com.example.animais.model.Animal;
import com.example.animais.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, Integer> {
    Optional<Usuario> findByNome(String nome);

    Optional<Usuario> findById(Long id);
}
