package br.com.Ebank.api.service;

import br.com.Ebank.api.model.Gerente;
import br.com.Ebank.api.model.Transacao;
import br.com.Ebank.api.repository.GerenteRepository;
import br.com.Ebank.api.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;


    public Gerente create(Gerente gerente){
        return gerenteRepository.save(gerente);
    }

    public Gerente findById(Long id){
        return gerenteRepository.findById(id).orElse(null);
    }

    public List<Gerente> findAll(){
        return gerenteRepository.findAll();
    }

    public List<Transacao> findAllTransacoes() {
        return transacaoRepository.findAll();
    }

}
