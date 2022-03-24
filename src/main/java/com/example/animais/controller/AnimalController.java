package com.example.animais.controller;

import com.example.animais.controller.api.AnimaisApi;
import com.example.animais.dto.AnimaisDTO;
import com.example.animais.model.Animal;
import com.example.animais.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animais")
public class AnimalController implements AnimaisApi {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService){
        this.animalService = animalService;
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Animal> postAnimal(@RequestBody AnimaisDTO animalDto){return animalService.save(animalDto);}

    @GetMapping
    public Page<Animal> getAnimais(Pageable pageable){
        return animalService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimal(String id){
        return animalService.getOneAnimal(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Animal> replaceAnimal(@RequestBody AnimaisDTO animalDto, @PathVariable String id) {
        return animalService.replaceAnimal(animalDto, id);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> deleteAnimal(@PathVariable String id){
        return animalService.delete(id);
    }


}
