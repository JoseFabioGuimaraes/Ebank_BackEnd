package com.ebank.controller;

import com.ebank.model.Cliente;
import com.ebank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listarClientePorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.listarClientePorId(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        return clienteService.criarCliente(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        Cliente cliente = clienteService.atualizarCliente(id, clienteAtualizado);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> encerrarCliente(@PathVariable Long id) {
        clienteService.encerrarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/aprovar-visitante")
    public ResponseEntity<Cliente> aprovarVisitante(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.aprovarVisitante(cliente);
        return ResponseEntity.ok(novoCliente);
    }
}
