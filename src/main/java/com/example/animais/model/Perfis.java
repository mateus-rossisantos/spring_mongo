package com.example.animais.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "perfis")
public class Perfis implements GrantedAuthority {

    @Id
    private Long id;
    private String nome;

    @Override
    public String getAuthority() {
        return nome;
    }
}
