package com.ebank.controller;

import com.ebank.model.ContaCorrente;
import com.ebank.service.ContaCorrenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
public class ContaCorrenteController {

    @Autowired
    private ContaCorrenteService contaCorrenteService;

    @GetMapping
    public List<ContaCorrente> listarContasCorrentes() {
        return contaCorrenteService.listarContasCorrentes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaCorrente> listarContaCorrentePorId(@PathVariable Long id) {
        Optional<ContaCorrente> contaCorrente = contaCorrenteService.listarContaCorrentePorId(id);
        return contaCorrente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ContaCorrente criarConta(@RequestBody ContaCorrente contaCorrente) {
        return contaCorrenteService.criarConta(contaCorrente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaCorrente> atualizarConta(@PathVariable Long id, @RequestBody ContaCorrente contaCorrenteAtualizada) {
        ContaCorrente contaAtualizada = contaCorrenteService.atualizarConta(id, contaCorrenteAtualizada);
        return contaAtualizada != null ? ResponseEntity.ok(contaAtualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> encerrarConta(@PathVariable Long id) {
        contaCorrenteService.encerrarConta(id);
        return ResponseEntity.noContent().build();
    }
}
