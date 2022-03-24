package com.example.animais.service;

import com.example.animais.dto.AnimaisDTO;
import com.example.animais.model.Animal;
import com.example.animais.repository.AnimalRepository;
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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class AnimalServiceTest {

    private ModelMapper modelMapper;

    @Mock
    private ModelMapper modelMapperMock;

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalService animalService;

    @BeforeEach
    void setup() {
        modelMapper = new ModelMapper();
        PageImpl<Animal> animaisPage = new PageImpl<>(List.of(AnimalCreator.createAnimalValid()));

        Mockito.when(animalRepository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(animaisPage);

        Mockito.when(animalRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(AnimalCreator.createAnimalValid()));

        Mockito.when(animalRepository.save(ArgumentMatchers.any(Animal.class)))
                .thenReturn(AnimalCreator.createAnimalValid());

        Mockito.doNothing().when(animalRepository).deleteById(ArgumentMatchers.anyString());

    }

    @Test
    void list_ReturnsListOfAnimalsInsidePageObject_WhenSuccessful(){

        String expectedName = AnimalCreator.createAnimalValid().getNome();
        Page<Animal> animaisPage2 = animalService.findAll(null);

        assertNotNull(animaisPage2);
        assertFalse(animaisPage2.toList().isEmpty());
        assertEquals(expectedName, animaisPage2.toList().get(0).getNome());
    }

    @Test
    void findById_Returns200andAnimal_WhenSuccessful(){
        Animal animalTobeSaved = AnimalCreator.createAnimalValid();
        Animal animal = animalService.getOneAnimal(animalTobeSaved.getId()).getBody();

        HttpStatus response = animalService.getOneAnimal(animalTobeSaved.getId()).getStatusCode();

        assertEquals(HttpStatus.OK, response);
        assertNotNull(animal);
        assertNotNull(animal.getId());
        assertEquals(animalTobeSaved.getId(), animal.getId());
    }

    @Test
    void findById_ReturnsEmpty_WhenNotSuccessful(){

        Mockito.when(animalRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());
        String id = "3454578";
        Animal animal = animalService.getOneAnimal(id).getBody();
        HttpStatus response = animalService.getOneAnimal(id).getStatusCode();

        assertEquals(HttpStatus.NOT_FOUND, response);
        assertNull(animal);
    }

    @Test
    void save_Returns201anAnimal_WhenSuccessful(){
        Animal animal = AnimalCreator.createAnimalValid();
        AnimaisDTO animalTobeSaved = modelMapper.map(animal, AnimaisDTO.class);
        Mockito.when(modelMapperMock.map(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(animal);
        Animal animalSaved = animalService.save(animalTobeSaved).getBody();
        HttpStatus response = animalService.save(animalTobeSaved).getStatusCode();

        assertEquals(HttpStatus.CREATED, response);
        assertEquals(animal.getNome(), animalSaved.getNome());
    }


    @Test
    void put_ReplaceAnimalReturn200_WhenSuccessful(){
        Animal animal = AnimalCreator.createAnimalValid();
        AnimaisDTO animaisDTO = modelMapper.map(animal, AnimaisDTO.class);
        Mockito.when(animalRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(animal));
        Mockito.when(modelMapperMock.map(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(animal);
        Animal animalAlterado = animalService.
                replaceAnimal(animaisDTO, AnimalCreator.createAnimalValid().getId()).getBody();
        HttpStatus response = animalService.
                replaceAnimal(animaisDTO, AnimalCreator.createAnimalValid().getId()).getStatusCode();
        assertEquals(HttpStatus.OK, response);
        assertNotNull(animalAlterado);
        assertNotNull(animalAlterado.getId());
        assertEquals(animaisDTO.getNome(), animalAlterado.getNome());
    }


    @Test
    void put_ReplaceAnimal_Returns404_WhenNotSuccessful(){
        Animal animal = AnimalCreator.createAnimalValid();
        AnimaisDTO animaisDTO = modelMapper.map(animal, AnimaisDTO.class);
        Mockito.when(animalRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.empty());
        Animal animalAlterado = animalService.
                replaceAnimal(animaisDTO, AnimalCreator.createAnimalValid().getId()).getBody();
        HttpStatus response = animalService.
                replaceAnimal(animaisDTO, AnimalCreator.createAnimalValid().getId()).getStatusCode();
        assertEquals(HttpStatus.NOT_FOUND, response);
        assertNull(animalAlterado);
    }

    @Test
    void delete_Animal_WhenSuccessful(){
        Animal animal = AnimalCreator.createAnimalValid();
        Mockito.when(animalRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(animal));
        HttpStatus response = animalService.delete("1").getStatusCode();
        assertEquals(HttpStatus.NO_CONTENT, response);
    }

    @Test
    void delete_return_404_WhenNotSuccessful(){
        Mockito.when(animalRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.empty());
        HttpStatus response = animalService.delete("1").getStatusCode();
        assertEquals(HttpStatus.NOT_FOUND, response);
    }

}