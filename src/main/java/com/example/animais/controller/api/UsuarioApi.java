package com.example.animais.controller.api;


import com.example.animais.dto.UsuarioRetornoDTO;
import com.example.animais.dto.UsuariosDTO;
import com.example.animais.model.Usuarios;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RequestMapping("/usuarios")
@EnableSwagger2
public interface UsuarioApi {
    @Operation(summary = "Adiciona um usuario",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "nome", required = true, schema = @Schema(implementation = String.class, example = "Fulano"), description = "Nome do usuário"),
                    @Parameter(in = ParameterIn.QUERY, name = "password", required = true, schema = @Schema(implementation = String.class, example = "123456"), description = "Senha do usuário")
            })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuario criado!"),
            @ApiResponse(code = 404, message = "Usuário não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping
    ResponseEntity<Usuarios> postUsuario(@RequestBody UsuariosDTO usuariosDTO, UriComponentsBuilder uriBuilder);

    @Operation(summary = "Mostra um usuario",
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", required = true, schema = @Schema(implementation = String.class, example = "12344"), description = "ID do usuário"),
            })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario acessado com sucesso!"),
            @ApiResponse(code = 403, message = "Você não possui permissão para isso"),
            @ApiResponse(code = 404, message = "Usuário não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/{id}")
    ResponseEntity<UsuarioRetornoDTO> getUsuario(@PathVariable String id);

    @Operation(summary = "Mostra todos Usuários")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuarios acessados com sucesso!"),
            @ApiResponse(code = 403, message = "Você não possui permissão para isso"),
            @ApiResponse(code = 404, message = "Usuários não encontrados"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping
    Page<UsuarioRetornoDTO> getUsuarios(Pageable pageable);

    @Operation(summary = "Deleta um usuário",
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", required = true, schema = @Schema(implementation = String.class, example = "12344"), description = "ID do usuário"),
            })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Usuario excluido com sucesso!"),
            @ApiResponse(code = 403, message = "Você não possui permissão para isso"),
            @ApiResponse(code = 404, message = "Usuário não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping("{id}")
    @Transactional
    ResponseEntity<?> deleteUsuario(@PathVariable String id);
}
