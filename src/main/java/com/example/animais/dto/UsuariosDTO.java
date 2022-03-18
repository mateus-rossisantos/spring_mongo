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
public class UsuariosDTO {
    private String nome;
    private String password;
    private List<Perfis> perfis;
}
