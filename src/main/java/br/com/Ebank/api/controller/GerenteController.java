package br.com.Ebank.api.controller;

import br.com.Ebank.api.model.Cliente;
import br.com.Ebank.api.model.Gerente;
import br.com.Ebank.api.model.Transacao;
import br.com.Ebank.api.service.ClienteService;
import br.com.Ebank.api.service.GerenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gerente")
public class GerenteController {

    @Autowired
    GerenteService gerenteService;

    @Autowired
    ClienteService clienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Gerente> create(@RequestBody Gerente gerente) {
        Gerente gerenteCreated = gerenteService.create(gerente);
        return ResponseEntity.status(201).body(gerenteCreated);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Gerente> findById(@PathVariable Long id) {
        Optional<Gerente> gerente = Optional.ofNullable(gerenteService.findById(id));
        if (gerente.isPresent()) {
            return new ResponseEntity<>(gerente.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Iterable<Gerente>> findAll() {
        Iterable<Gerente> gerentes = gerenteService.findAll();
        return ResponseEntity.status(200).body(gerentes);
    }

    @GetMapping("/clientes")
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> findAllClientes(){
        return clienteService.findAll();
    }

    @GetMapping("clientes-ativos")
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> findClientesAtivos(){
        return clienteService.findByStatus();
    }

    @GetMapping("/cliente/{id}/transacoes")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Transacao>> findAllTransacoes(@PathVariable Long id) {
        List<Transacao> transacoes = clienteService.findAllTransacoes(id);
        return ResponseEntity.ok(transacoes);
    }

    @GetMapping("/todas-transacoes")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Transacao>> findAllTransacoes() {
        List<Transacao> transacoes = gerenteService.findAllTransacoes();
        return ResponseEntity.ok(transacoes);
    }
}