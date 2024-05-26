package com.ebank.controller;

import com.ebank.model.Cliente;
import com.ebank.service.VisitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visitantes")
public class VisitanteController {

    @Autowired
    private VisitanteService visitanteService;

    @PostMapping("/solicitar-abertura-conta")
    public ResponseEntity<Cliente> solicitarAberturaDeConta(@RequestBody Cliente cliente) {
        Cliente novoCliente = visitanteService.solicitarAberturaDeConta(cliente);
        return ResponseEntity.ok(novoCliente);
    }
}
