package com.example.animais.repository;

import com.example.animais.model.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalRepository extends MongoRepository<Animal, Integer> {

    Optional<Animal> findById(Long id);
}
