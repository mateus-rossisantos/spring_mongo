package com.example.animais.controller.api;


import com.example.animais.model.Animais;
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

@RequestMapping("/animais")
public interface AnimaisApi {

    @Operation(summary = "Adiciona um animal",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "nome", required = true, schema = @Schema(implementation = String.class, example = "Rex"), description = "Nome do animal."),
                    @Parameter(in = ParameterIn.QUERY, name = "raça", required = true, schema = @Schema(implementation = String.class, example = "Cachorro"), description = "Raça do animal.")
            })
    @PostMapping
    @Transactional
    ResponseEntity<Animais> postAnimal(@RequestBody Animais animais, UriComponentsBuilder uriBuilder);

    @Operation(summary = "Lista os animais")
    @GetMapping
    Page<Animais> getAnimal(Pageable pageable);

    @Operation(summary = "Mostra um animal",
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", required = true, schema = @Schema(implementation = String.class, example = "10"), description = "Id do animal.")
            })
    @GetMapping("/{id}")
    ResponseEntity<Animais> getAnimal(@PathVariable(required = false, value = "10", name = "page") String id);

    @Operation(summary = "Altera um animal",
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", required = true, schema = @Schema(implementation = String.class, example = "10"), description = "Id do animal."),
                    @Parameter(in = ParameterIn.QUERY, name = "nome", required = true, schema = @Schema(implementation = String.class, example = "rex"), description = "Nome do animal."),
                    @Parameter(in = ParameterIn.QUERY, name = "raça", required = true, schema = @Schema(implementation = String.class, example = "cachorro"), description = "raça do animal.")
            })
    @PutMapping("{id}")
    ResponseEntity<Animais> replaceAnimal(@RequestBody Animais animais, @PathVariable String id);

    @Operation(summary = "Deleta um animal",
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", required = true, schema = @Schema(implementation = String.class, example = "10"), description = "Id do anima.")
            })
    @DeleteMapping("{id}")
    @Transactional
    ResponseEntity<?> deleteAnimal(@PathVariable String id);

}
