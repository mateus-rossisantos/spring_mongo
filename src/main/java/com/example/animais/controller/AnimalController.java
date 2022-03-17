package com.example.animais.controller;

import com.example.animais.controller.api.AnimaisApi;
import com.example.animais.model.Animais;
import com.example.animais.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/animais")
public class AnimalController implements AnimaisApi {

    @Autowired
    private AnimalService animalService;

    @PostMapping
    @Transactional
    public ResponseEntity<Animais> postAnimal(@RequestBody Animais animal, UriComponentsBuilder uriBuilder){
            return animalService.save(animal, uriBuilder);
    }

    @GetMapping
    public Page<Animais> getAnimal(Pageable pageable){
        return animalService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animais> getAnimal(String id){
        return animalService.getOneAnimal(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Animais> replaceAnimal(@RequestBody Animais animal, @PathVariable String id) {
        return animalService.replaceAnimal(animal, id);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> deleteAnimal(@PathVariable String id){
        return animalService.delete(id);
    }


}
