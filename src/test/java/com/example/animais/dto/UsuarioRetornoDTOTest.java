package com.example.animais.dto;

import com.example.animais.model.Perfis;
import com.example.animais.model.Usuario;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioRetornoDTOTest {
    private final UsuarioRetornoDTO usuarioRetornoDTO = new UsuarioRetornoDTO();

    @Test
    void when_setId_GetIdShouldReturnId(){
        String id = "23304e";
        usuarioRetornoDTO.setId(id);
        assertEquals(usuarioRetornoDTO.getId(), id);
    }

    @Test
    void when_setName_GetNameShouldReturnName(){
        String name = "Teste";
        usuarioRetornoDTO.setNome(name);
        assertEquals(usuarioRetornoDTO.getNome(), name);
    }

    @Test
    void when_Construct_GetsShouldReturnIdAndName(){
        String id = "1234";
        String nome = "Nome";
        UsuarioRetornoDTO usuarioRetornoDTO1 = new UsuarioRetornoDTO(id,nome);
        assertEquals(usuarioRetornoDTO1.getId(), id);
        assertEquals(usuarioRetornoDTO1.getNome(), nome);
    }

    @Test
    void when_conversorPage_ShouldReturnPageUsuarioRetornoDtoWhenSuccessfull(){
        Usuario usuario = new Usuario("111","name","password", List.of(new Perfis()));
        PageImpl<Usuario> usuariosPage = new PageImpl<>(List.of(usuario));
        Page<UsuarioRetornoDTO> usuarioRetornoDTOPage = usuarioRetornoDTO.conversorPage(usuariosPage);
        assertNotNull(usuarioRetornoDTOPage);
        assertEquals(usuario.getNome(), usuarioRetornoDTOPage.toList().get(0).getNome());
    }
}