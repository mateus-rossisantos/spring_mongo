package com.example.animais.controller.api;


import com.example.animais.model.Usuarios;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/usuarios")
public interface UsuarioApi {
    @Operation(summary = "Adiciona um usuario",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "nome", required = true, schema = @Schema(implementation = String.class, example = "Fulano"), description = "Nome do usuário"),
                    @Parameter(in = ParameterIn.QUERY, name = "password", required = true, schema = @Schema(implementation = String.class, example = "123456"), description = "Senha do usuário")
            })
    @PostMapping
    @Transactional
    ResponseEntity<Usuarios> postUsuario(@RequestBody Usuarios usuario, UriComponentsBuilder uriBuilder);

    @Operation(summary = "Mostra um usuario",
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", required = true, schema = @Schema(implementation = String.class, example = "12344"), description = "ID do usuário"),
            })
    @GetMapping("/{id}")
    ResponseEntity<Usuarios> getUsuario(@PathVariable String id);

    @Operation(summary = "Mostra todos Usuários")
    @GetMapping
    Page<Usuarios> getUsuarios(Pageable pageable);

    @Operation(summary = "Deleta um usuário",
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", required = true, schema = @Schema(implementation = String.class, example = "12344"), description = "ID do usuário"),
            })
    @DeleteMapping("{id}")
    @Transactional
    ResponseEntity<?> deleteUsuario(@PathVariable String id);
}
