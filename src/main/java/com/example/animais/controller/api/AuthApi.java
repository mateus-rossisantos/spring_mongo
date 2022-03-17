package com.example.animais.controller.api;

import com.example.animais.dto.TokenDto;
import com.example.animais.model.Usuarios;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthApi {

    @Operation(summary = "Loga com um usuário cadastrado",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "nome", required = true, schema = @Schema(implementation = String.class, example = "Fulano"), description = "Nome do Usuário."),
                    @Parameter(in = ParameterIn.QUERY, name = "senha", required = true, schema = @Schema(implementation = String.class, example = "123456"), description = "Senha do Usuário.")
            })
    @PostMapping
    ResponseEntity<TokenDto> autenticar(@RequestBody Usuarios usuario);
}
