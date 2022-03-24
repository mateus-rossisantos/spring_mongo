package com.example.animais.controller.api;

import com.example.animais.dto.TokenDTO;
import com.example.animais.dto.UsuarioLoginDto;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RequestMapping("/auth")
@EnableSwagger2
public interface AuthApi {

    @Operation(summary = "Loga com um usuário cadastrado!",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "nome", required = true, schema = @Schema(implementation = String.class, example = "Fulano"), description = "Nome do Usuário."),
                    @Parameter(in = ParameterIn.QUERY, name = "senha", required = true, schema = @Schema(implementation = String.class, example = "123456"), description = "Senha do Usuário.")
            })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login realizado com sucesso!"),
            @ApiResponse(code = 404, message = "Usuário não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping
    ResponseEntity<TokenDTO> autenticar(@RequestBody UsuarioLoginDto usuarioLoginDto);
}
