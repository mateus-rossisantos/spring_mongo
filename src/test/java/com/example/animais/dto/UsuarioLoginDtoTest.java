package com.example.animais.dto;

import com.example.animais.model.Perfis;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioLoginDtoTest {
    private final UsuarioLoginDto usuarioLoginDto = new UsuarioLoginDto();

    @Test
    void when_SetName_GetNameShouldReturnName(){
        String name = "nome";
        usuarioLoginDto.setNome(name);
        assertEquals(usuarioLoginDto.getNome(), name);
    }

    @Test
    void when_SetPasswordGetPasswordShouldReturnPassword(){
        String password = "123456";
        usuarioLoginDto.setPassword(password);
        assertEquals(usuarioLoginDto.getPassword(), password);
    }

    @Test
    void when_CallConverterShouldReturnAuthenticationToken(){
        UsuarioLoginDto usuarioLoginDto1 = new UsuarioLoginDto("name", "123456");
        UsernamePasswordAuthenticationToken token = usuarioLoginDto1.converter();
        assertEquals(token.getName(), usuarioLoginDto1.getNome());
    }

    @Test
    void when_CallConverterNullShouldReturnNull(){
        UsuarioLoginDto usuarioLoginDto1 = new UsuarioLoginDto();
        UsernamePasswordAuthenticationToken token = usuarioLoginDto1.converter();
        assertFalse(token.isAuthenticated());
    }

    @Test
    void when_Contruct_GetsShouldReturnSets(){
        String nome = "name";
        String password = "123456";
        UsuarioLoginDto usuariosLoginDTO1 = new UsuarioLoginDto(nome,password);
        assertEquals(usuariosLoginDTO1.getNome(), nome);
        assertEquals(usuariosLoginDTO1.getPassword(), password);
    }
}