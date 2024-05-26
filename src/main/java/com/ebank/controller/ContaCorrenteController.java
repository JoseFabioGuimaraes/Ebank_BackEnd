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
    public ContaCorrente criarContaCorrente(@RequestBody ContaCorrente contaCorrente) {
        return contaCorrenteService.criarContaCorrente(contaCorrente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaCorrente> atualizarContaCorrente(@PathVariable Long id, @RequestBody ContaCorrente contaCorrenteAtualizada) {
        ContaCorrente contaAtualizada = contaCorrenteService.atualizarContaCorrente(id, contaCorrenteAtualizada);
        return contaAtualizada != null ? ResponseEntity.ok(contaAtualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> encerrarContaCorrente(@PathVariable Long id) {
        contaCorrenteService.encerrarContaCorrente(id);
        return ResponseEntity.noContent().build();
    }
}
