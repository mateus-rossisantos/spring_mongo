package com.example.animais.controller;

import com.example.animais.dto.AnimaisDTO;
import com.example.animais.model.Animal;
import com.example.animais.service.AnimalService;
import com.example.animais.util.AnimalCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class AnimalControllerTest {

    private ModelMapper modelMapper;

    @Mock
    private AnimalService animalServiceMock;

    @InjectMocks
    private AnimalController animalController;

    @BeforeEach
    void setup() {
        modelMapper = new ModelMapper();
        PageImpl<Animal> animaisPage = new PageImpl<>(List.of(AnimalCreator.createAnimalValid()));

        Mockito.when(animalServiceMock.findAll(ArgumentMatchers.any())).thenReturn(animaisPage);

        Mockito.when(animalServiceMock.getOneAnimal(ArgumentMatchers.any()))
                .thenReturn(ResponseEntity.ok(AnimalCreator.createAnimalValid()));

        Mockito.when(animalServiceMock.save(ArgumentMatchers
                        .any(AnimaisDTO.class)))
                .thenReturn(new ResponseEntity<>(AnimalCreator.createAnimalValid(), HttpStatus.CREATED));

        Mockito.when(animalServiceMock
                        .replaceAnimal(ArgumentMatchers.any(AnimaisDTO.class), ArgumentMatchers.anyString()))
                .thenReturn(ResponseEntity.ok(AnimalCreator.createUpdatedAnimal()));

        Mockito.when(animalServiceMock.delete(ArgumentMatchers.anyString()))
                .thenReturn(ResponseEntity.noContent().build());
    }

    @Test
    void list_ReturnsListOfAnimalsInsidePageObject_WhenSuccessful(){
        String expectedName = AnimalCreator.createAnimalValid().getNome();
        Page<Animal> animaisPage = animalController.getAnimais(null);

        assertNotNull(animaisPage);
        assertFalse(animaisPage.toList().isEmpty());
        assertEquals(expectedName, animaisPage.toList().get(0).getNome());
    }

    @Test
    void findById_Returns200andAnimal_WhenSuccessful(){
        Animal animalTobeSaved = AnimalCreator.createAnimalValid();
        Animal animal = animalController.getAnimal(animalTobeSaved.getId()).getBody();

        HttpStatus response = animalController.getAnimal(animalTobeSaved.getId()).getStatusCode();

        assertEquals(HttpStatus.OK, response);
        assertNotNull(animal);
        assertNotNull(animal.getId());
        assertEquals(animalTobeSaved.getId(), animal.getId());
    }

    @Test
    void findById_ReturnsEmpty_WhenNotSuccessful(){

        Mockito.when(animalServiceMock.getOneAnimal(ArgumentMatchers.any()))
                .thenReturn(ResponseEntity.notFound().build());
        String id = "3454578";

        Animal animal = animalController.getAnimal(id).getBody();
        HttpStatus response = animalController.getAnimal(id).getStatusCode();

        assertEquals(HttpStatus.NOT_FOUND, response);
        assertNull(animal);
    }

    @Test
    void save_Returns201anAnimal_WhenSuccessful(){
        AnimaisDTO animalTobeSaved = modelMapper.map(AnimalCreator.createAnimalValid(), AnimaisDTO.class);
        Animal animal = animalController.postAnimal(animalTobeSaved).getBody();

        HttpStatus response = animalController.postAnimal(animalTobeSaved).getStatusCode();

        assertEquals(HttpStatus.CREATED, response);
        assertNotNull(animal);
        assertNotNull(animal.getId());
        assertEquals(animalTobeSaved.getNome(), animal.getNome());
    }

    @Test
    void put_ReplaceAnimalReturn200_WhenSuccessful(){
        AnimaisDTO animaisDTO = modelMapper.map(AnimalCreator.createUpdatedAnimal(), AnimaisDTO.class);
        Animal animalAlterado = animalController.
                replaceAnimal(animaisDTO, AnimalCreator.createAnimalValid().getId()).getBody();
        HttpStatus response = animalController.
                replaceAnimal(animaisDTO, AnimalCreator.createAnimalValid().getId()).getStatusCode();
        assertEquals(HttpStatus.OK, response);
        assertNotNull(animalAlterado);
        assertNotNull(animalAlterado.getId());
        assertEquals(animaisDTO.getNome(), animalAlterado.getNome());
    }

    @Test
    void put_ReplaceAnimal_Returns404_WhenNotSuccessful(){
        Mockito.when(animalServiceMock
                        .replaceAnimal(ArgumentMatchers.any(AnimaisDTO.class), ArgumentMatchers.anyString()))
                .thenReturn(ResponseEntity.notFound().build());
        String id = "3454578";
        AnimaisDTO animalDto = new AnimaisDTO("Teste", "Tester");
        Animal animal = animalController.replaceAnimal(animalDto,id).getBody();
        HttpStatus response = animalController.replaceAnimal(animalDto,id).getStatusCode();

        assertEquals(HttpStatus.NOT_FOUND, response);
        assertNull(animal);
    }

    @Test
    void delete_Animal_WhenSuccessful(){
        AnimaisDTO animalTobeSaved = modelMapper.map(AnimalCreator.createAnimalValid(), AnimaisDTO.class);
        Animal animal = animalController.postAnimal(animalTobeSaved).getBody();
        Animal animalDeletado = (Animal) animalController.deleteAnimal(animal.getId()).getBody();
        HttpStatus response = animalController.deleteAnimal(animal.getId()).getStatusCode();

        assertEquals(HttpStatus.NO_CONTENT, response);
        assertNull(animalDeletado);
    }

    @Test
    void delete_return_404_WhenNotSuccessful(){
        Mockito.when(animalServiceMock.delete(ArgumentMatchers.any())).thenReturn(ResponseEntity.notFound().build());
        String id = "1";
        HttpStatus response = animalController.deleteAnimal(id).getStatusCode();
        assertEquals(HttpStatus.NOT_FOUND, response);
    }
}