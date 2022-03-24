package com.example.animais.dto;

import com.example.animais.model.Perfis;
import com.example.animais.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.security.access.method.P;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuariosDTOTest {
    private final UsuariosDTO usuariosDTO = new UsuariosDTO();

    @Test
    void when_SetName_GetNameShouldReturnName(){
        String name = "nome";
        usuariosDTO.setNome(name);
        assertEquals(usuariosDTO.getNome(), name);
    }

    @Test
    void when_SetPasswordGetPasswordShouldReturnPassword(){
        String password = "123456";
        usuariosDTO.setPassword(password);
        assertEquals(usuariosDTO.getPassword(), password);
    }

    @Test
    void when_SetPerfisGetPerfisShouldReturnPerfis(){
        Perfis perfil = new Perfis(1L,"Admin");
        usuariosDTO.setPerfis(List.of(perfil));
        assertEquals(usuariosDTO.getPerfis().get(0).getId(), perfil.getId());
    }

    @Test
    void when_Contruct_GetsShouldReturnSets(){
        String nome = "name";
        String password = "123456";
        Perfis perfil = new Perfis(1L,"Admin");
        UsuariosDTO usuariosDTO1 = new UsuariosDTO(nome,password,List.of(perfil));
        assertEquals(usuariosDTO1.getNome(), nome);
        assertEquals(usuariosDTO1.getPassword(), password);
        assertEquals(usuariosDTO1.getPerfis().get(0), perfil);
    }

}