package com.ebank.service;

import com.ebank.model.Pessoa;
import com.ebank.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Optional<Pessoa> encontrarPorCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf);
    }
}
