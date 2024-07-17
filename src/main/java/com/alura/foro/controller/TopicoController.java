package com.alura.foro.controller;

import com.alura.foro.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicosRepository topicosRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopicos> registroTopico(@RequestBody @Valid DatosRegistroTopicos datosRegistroTopicos,
                                                                UriComponentsBuilder uriComponentsBuilder){
        Topicos topicos = topicosRepository.save(new Topicos(datosRegistroTopicos));
        DatosRespuestaTopicos datosRespuestaTopicos = new DatosRespuestaTopicos(topicos.getId(), topicos.getTitulo(),
                topicos.getMensaje(),topicos.getFecha(),topicos.getAutor(),topicos.getCurso(),topicos.getStatus());
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicos.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopicos);
    }
    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopicos>> listarTopicos(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "fecha"));

        Page<Topicos> topicosPage = topicosRepository.findByStatusTrue(pageRequest);

        Page<DatosRespuestaTopicos> dtoPage = topicosPage.map(topico -> new DatosRespuestaTopicos(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), topico.getFecha(), topico.getAutor(), topico.getCurso(), topico.getStatus()));

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopicos> retornaDatosTopicos(@PathVariable Long id){
        Topicos topicos = topicosRepository.getReferenceById(id);
        var datosTopicos = new DatosRespuestaTopicos(topicos.getId(),
                topicos.getTitulo(),
                topicos.getMensaje(),
                topicos.getFecha(),
                topicos.getAutor(),
                topicos.getCurso(),
                topicos.getStatus());
        return ResponseEntity.ok(datosTopicos);

    }
    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopicos(@RequestBody @Valid DatosActualizarTopicos datosActualizarTopicos){
        Topicos topicos = topicosRepository.getReferenceById(datosActualizarTopicos.id());
        topicos.actualizarDatos(datosActualizarTopicos);

        return ResponseEntity.ok(new DatosRespuestaTopicos(topicos.getId(),
                topicos.getTitulo(),
                topicos.getMensaje(),
                topicos.getFecha(),
                topicos.getAutor(),
                topicos.getCurso(),
                topicos.getStatus()));

    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Topicos topico = topicosRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }
}
