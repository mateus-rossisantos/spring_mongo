package com.example.animais.controller;

import com.example.animais.model.Animal;
import com.example.animais.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Animal> postAnimal(@RequestBody Animal animal, UriComponentsBuilder uriBuilder){
        Optional<Animal> animalOptional = animalRepository.findById(animal.getId());
        if (!animalOptional.isPresent()){
            animalRepository.save(animal);
            URI uri = uriBuilder.path("animais/{id}").buildAndExpand(animal.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.badRequest().build();


    }

    @GetMapping
    public List<Animal> getAnimal(){
        return animalRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimal(@PathVariable int id){
        Optional<Animal> animalOptional = animalRepository.findById(id);
        return animalOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Animal> putAnimal(@RequestBody Animal animal, @PathVariable int id) {
        Optional<Animal> animalOptional = animalRepository.findById(id);
        if (animalOptional.isPresent()){
            animalRepository.save(animal);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> deleteAnimal(@PathVariable int id){
        Optional<Animal> animalOptional = animalRepository.findById(id);
        if (animalOptional.isPresent()) {
            animalRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}
