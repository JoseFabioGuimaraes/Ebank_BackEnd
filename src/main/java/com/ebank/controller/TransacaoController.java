package com.ebank.controller;

import com.ebank.model.ContaCorrente;
import com.ebank.model.Transacao;
import com.ebank.service.ContaCorrenteService;
import com.ebank.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private ContaCorrenteService contaCorrenteService;

    @GetMapping
    public List<Transacao> listarTransacoes() {
        return transacaoService.listarTransacoes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transacao> listarTransacaoPorId(@PathVariable Long id) {
        Optional<Transacao> transacao = transacaoService.listarTransacaoPorId(id);
        return transacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/depositar")
    public ResponseEntity<Transacao> depositar(@RequestParam Long contaId, @RequestParam BigDecimal valor) {
        Transacao transacao = transacaoService.depositar(contaId, valor);
        return transacao != null ? ResponseEntity.ok(transacao) : ResponseEntity.notFound().build();
    }

    @PostMapping("/sacar")
    public ResponseEntity<Transacao> sacar(@RequestParam Long contaId, @RequestParam BigDecimal valor) {
        Transacao transacao = transacaoService.sacar(contaId, valor);
        return transacao != null ? ResponseEntity.ok(transacao) : ResponseEntity.notFound().build();
    }

    @PostMapping("/transferir")
    public ResponseEntity<?> transferir(@RequestParam Long contaOrigemId, @RequestParam Long contaDestinoId, @RequestParam BigDecimal valor) {
        Optional<ContaCorrente> contaDestinoOpt = contaCorrenteService.listarContaCorrentePorId(contaDestinoId);
        if (contaDestinoOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Conta de destino inválida.");
        }

        ContaCorrente contaDestino = contaDestinoOpt.get();

        // Suponha que você tenha um mecanismo de confirmação aqui
        // Confirmação poderia ser implementada por outro endpoint ou mecanismo

        Transacao transacao = transacaoService.transferir(contaOrigemId, contaDestinoId, valor);
        return transacao != null ? ResponseEntity.ok(transacao) : ResponseEntity.notFound().build();
    }
}
