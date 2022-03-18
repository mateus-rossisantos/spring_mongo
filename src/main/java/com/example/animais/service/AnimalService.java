package com.example.animais.service;

import com.example.animais.controller.api.AnimaisApi;
import com.example.animais.dto.AnimaisDTO;
import com.example.animais.model.Animais;
import com.example.animais.repository.AnimalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<Animais> save(AnimaisDTO animalDto, UriComponentsBuilder uriBuilder) {
        Animais animal = modelMapper.map(animalDto, Animais.class);
        animalRepository.save(animal);
        URI uri = uriBuilder.path("animais/{id}").buildAndExpand(animal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    public Page<Animais> findAll(Pageable pageable) {
        return animalRepository.findAll(pageable);
    }


    public ResponseEntity<Animais> getOneAnimal(String id) {
        return animalRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Animais> replaceAnimal(AnimaisDTO animalDto, String id) {
        Optional<Animais> animalOptional = animalRepository.findById(id);
        if (animalOptional.isPresent()){
            Animais animal = modelMapper.map(animalDto, Animais.class);
            animal.setId(id);
            animalRepository.save(animal);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> delete(String id) {
        Optional<Animais> animalOptional = animalRepository.findById(id);
        if (animalOptional.isPresent()) {
            animalRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
