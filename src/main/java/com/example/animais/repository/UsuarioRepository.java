package com.example.animais.repository;

import com.example.animais.model.Usuarios;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuarios, String> {
    Optional<Usuarios> findByNome(String nome);
}
