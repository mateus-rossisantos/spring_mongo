package com.example.animais.dto;

import com.example.animais.model.Usuarios;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.function.Function;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRetornoDTO {
    private String id;
    private String nome;

    public Page<UsuarioRetornoDTO> conversorPage(Page<Usuarios> pageUsuarios) {
        return pageUsuarios.map(usuarios -> {
            UsuarioRetornoDTO dto = new UsuarioRetornoDTO();
            dto.setId(usuarios.getId());
            dto.setNome(usuarios.getNome());
            return dto;
        });
    }
}


