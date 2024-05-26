package com.ebank.controller;

import com.ebank.model.Gerente;
import com.ebank.service.GerenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gerentes")
public class GerenteController {

    @Autowired
    private GerenteService gerenteService;

    @GetMapping
    public List<Gerente> listarGerentes() {
        return gerenteService.listarGerentes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gerente> listarGerentePorId(@PathVariable Long id) {
        Optional<Gerente> gerente = gerenteService.listarGerentePorId(id);
        return gerente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Gerente criarGerente(@RequestBody Gerente gerente) {
        return gerenteService.criarGerente(gerente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gerente> atualizarGerente(@PathVariable Long id, @RequestBody Gerente gerenteAtualizado) {
        Gerente gerente = gerenteService.atualizarGerente(id, gerenteAtualizado);
        return gerente != null ? ResponseEntity.ok(gerente) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> encerrarGerente(@PathVariable Long id) {
        gerenteService.encerrarGerente(id);
        return ResponseEntity.noContent().build();
    }
}
