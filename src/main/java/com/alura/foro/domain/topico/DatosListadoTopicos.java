package com.alura.foro.domain.topico;

import java.time.LocalDateTime;

public record DatosListadoTopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        Boolean status,
        String autor,
        String curso
) {
}
