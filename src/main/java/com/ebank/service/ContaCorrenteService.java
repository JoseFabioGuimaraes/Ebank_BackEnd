package com.ebank.service;

import com.ebank.model.ContaCorrente;
import com.ebank.repository.ContaCorrenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContaCorrenteService {

    @Autowired
    private ContaCorrenteRepository contaCorrenteRepository;

    public List<ContaCorrente> listarContasCorrentes() {
        return contaCorrenteRepository.findAll();
    }

    public Optional<ContaCorrente> listarContaCorrentePorId(Long id) {
        return contaCorrenteRepository.findById(id);
    }

    public ContaCorrente criarConta(ContaCorrente contaCorrente) {
        contaCorrente.setDataCriacao(LocalDateTime.now());
        return contaCorrenteRepository.save(contaCorrente);
    }

    public ContaCorrente atualizarConta(Long id, ContaCorrente contaCorrenteAtualizada) {
        Optional<ContaCorrente> contaExistente = contaCorrenteRepository.findById(id);
        if (contaExistente.isPresent()) {
            ContaCorrente conta = contaExistente.get();
            conta.setNumeroConta(contaCorrenteAtualizada.getNumeroConta());
            conta.setSaldo(contaCorrenteAtualizada.getSaldo());
            conta.setLimiteEspecial(contaCorrenteAtualizada.getLimiteEspecial());
            conta.setCliente(contaCorrenteAtualizada.getCliente());
            return contaCorrenteRepository.save(conta);
        }
        return null;
    }

    public void encerrarConta(Long id) {
        contaCorrenteRepository.deleteById(id);
    }
}
