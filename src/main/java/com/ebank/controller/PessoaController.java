package com.ebank.controller;

import com.ebank.model.Pessoa;
import com.ebank.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/login")
    public ResponseEntity<Pessoa> login(@RequestParam String cpf) {
        Optional<Pessoa> pessoa = pessoaService.encontrarPorCpf(cpf);
        return pessoa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
