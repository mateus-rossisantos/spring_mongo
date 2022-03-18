package com.example.animais.dto;

import com.example.animais.model.Perfis;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLoginDto {
    private String nome;
    private String password;

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(nome, password);
    }
}
