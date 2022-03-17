package com.example.animais.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "animal")
public class Animais {
    @Id
    private String id;
    private String nome;
    private String raca;

    public Animais(String nome, String raca) {
        this.nome = nome;
        this.raca = raca;
    }


}
