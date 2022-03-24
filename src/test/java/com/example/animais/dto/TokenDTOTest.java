package com.example.animais.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenDTOTest {

    @Test
    void when_Construct_GetTokenAndTipoShouldReturnTokenAndTipo(){
        String token = "token";
        String tipo = "bearer";
        TokenDTO tokenDTO = new TokenDTO(token, tipo);
        assertEquals(tokenDTO.getToken(), token);
        assertEquals(tokenDTO.getTipo(), tipo);
    }
}