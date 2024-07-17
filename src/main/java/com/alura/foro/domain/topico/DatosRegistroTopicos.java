package com.alura.foro.domain.topico;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DatosRegistroTopicos(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        LocalDateTime fecha,
        @NotBlank
        String autor,
        @NotBlank
        String curso,
        boolean status
) {
}
