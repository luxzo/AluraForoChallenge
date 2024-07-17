package com.alura.foro.domain.topico;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topicos")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;
    private String autor;
    private String curso;
    private Boolean status;



    public Topicos(DatosRegistroTopicos datosRegistroTopicos) {
        this.status = true;
        this.autor = datosRegistroTopicos.autor();
        this.curso = datosRegistroTopicos.curso();
        this.mensaje = datosRegistroTopicos.mensaje();
        this.titulo = datosRegistroTopicos.titulo();
        this.fecha = LocalDateTime.now(); // Establecer la fecha de creación

    }

    public void actualizarDatos(DatosActualizarTopicos datosActualizarTopicos) {
        if (datosActualizarTopicos.titulo() != null) {
            this.titulo = datosActualizarTopicos.titulo();
        }
        if (datosActualizarTopicos.mensaje() != null) {
            this.mensaje = datosActualizarTopicos.mensaje();
        }
        if (datosActualizarTopicos.autor() != null) {
            this.autor = datosActualizarTopicos.autor();
        }

    }

    public void desactivarTopico() {
        this.status = false;
    }
}
