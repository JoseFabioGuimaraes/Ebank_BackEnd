package com.ebank.service;

import com.ebank.model.Gerente;
import com.ebank.repository.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    public List<Gerente> listarGerentes() {
        return gerenteRepository.findAll();
    }

    public Optional<Gerente> listarGerentePorId(Long id) {
        return gerenteRepository.findById(id);
    }

    public Gerente criarGerente(Gerente gerente) {
        return gerenteRepository.save(gerente);
    }

    public Gerente atualizarGerente(Long id, Gerente gerenteAtualizado) {
        Optional<Gerente> gerenteExistente = gerenteRepository.findById(id);
        if (gerenteExistente.isPresent()) {
            Gerente gerente = gerenteExistente.get();
            gerente.setNome(gerenteAtualizado.getNome());
            gerente.setCpf(gerenteAtualizado.getCpf());
            gerente.setDataNascimento(gerenteAtualizado.getDataNascimento());
            gerente.setEndereco(gerenteAtualizado.getEndereco());
            gerente.setMatricula(gerenteAtualizado.getMatricula());
            return gerenteRepository.save(gerente);
        }
        return null;
    }

    public void encerrarGerente(Long id) {
        gerenteRepository.deleteById(id);
    }
}
