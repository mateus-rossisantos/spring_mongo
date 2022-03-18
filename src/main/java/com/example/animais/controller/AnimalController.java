package com.example.animais.controller;

import com.example.animais.controller.api.AnimaisApi;
import com.example.animais.dto.AnimaisDTO;
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
    public ResponseEntity<Animais> postAnimal(@RequestBody AnimaisDTO animalDto, UriComponentsBuilder uriBuilder){
            return animalService.save(animalDto, uriBuilder);
    }

    @GetMapping
    public Page<Animais> getAnimais(Pageable pageable){
        return animalService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animais> getAnimal(String id){
        return animalService.getOneAnimal(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Animais> replaceAnimal(@RequestBody AnimaisDTO animalDto, @PathVariable String id) {
        return animalService.replaceAnimal(animalDto, id);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> deleteAnimal(@PathVariable String id){
        return animalService.delete(id);
    }


}
