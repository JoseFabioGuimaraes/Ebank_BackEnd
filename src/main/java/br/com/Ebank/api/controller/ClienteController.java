package br.com.Ebank.api.controller;

import br.com.Ebank.api.dto.ClienteDTO;
import br.com.Ebank.api.model.Cliente;
import br.com.Ebank.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
        Cliente clienteCreated = clienteService.create(cliente);
        return ResponseEntity.status(201).body(clienteCreated);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Optional<Cliente> cliente = Optional.ofNullable(clienteService.findById(id));
        if (cliente.isPresent()) {
            return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody ClienteDTO cliente) {
        Cliente clienteUpdated = clienteService.update(id, cliente);
        return ResponseEntity.status(200).body(clienteUpdated);
        }

    @DeleteMapping("/{id}/apagar-cliente")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Cliente> delete(@PathVariable Long id) {
        Cliente clienteUpdated = clienteService.delete(id);
        return ResponseEntity.status(200).body(clienteUpdated);
    }
}

