package com.example.animais.util;

import com.example.animais.model.Animal;

public class AnimalCreator {

    public static Animal createAnimalToBeSaved(){
        return new Animal("Rex", "Cachorro");
    }

    public static Animal createAnimalValid(){
        return new Animal("384719e349","Rex", "Cachorro");
    }

    public static Animal createUpdatedAnimal(){
        return new Animal("384719e349","Doguinho", "Cachorro");
    }


}
