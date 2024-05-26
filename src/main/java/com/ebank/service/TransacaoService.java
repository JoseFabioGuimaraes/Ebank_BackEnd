package com.ebank.service;

import com.ebank.model.ContaCorrente;
import com.ebank.model.NaturezaTransacao;
import com.ebank.model.TipoTransacao;
import com.ebank.model.Transacao;
import com.ebank.repository.ContaCorrenteRepository;
import com.ebank.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaCorrenteRepository contaCorrenteRepository;

    public List<Transacao> listarTransacoes() {
        return transacaoRepository.findAll();
    }

    public Optional<Transacao> listarTransacaoPorId(Long id) {
        return transacaoRepository.findById(id);
    }

    private Transacao realizarTransacao(Long contaId, BigDecimal valor, TipoTransacao tipo, NaturezaTransacao natureza, ContaCorrente contaDestino) {
        Optional<ContaCorrente> contaCorrenteOpt = contaCorrenteRepository.findById(contaId);
        if (contaCorrenteOpt.isPresent()) {
            ContaCorrente contaCorrente = contaCorrenteOpt.get();
            if (tipo == TipoTransacao.SAQUE || (tipo == TipoTransacao.TRANSFERENCIA && natureza == NaturezaTransacao.DEBITO)) {
                contaCorrente.setSaldo(contaCorrente.getSaldo().subtract(valor));
            } else if (tipo == TipoTransacao.DEPOSITO || (tipo == TipoTransacao.TRANSFERENCIA && natureza == NaturezaTransacao.CREDITO)) {
                contaCorrente.setSaldo(contaCorrente.getSaldo().add(valor));
            }
            contaCorrenteRepository.save(contaCorrente);
            Transacao transacao = new Transacao(contaCorrente, contaDestino, valor, LocalDateTime.now(), tipo, natureza);
            return transacaoRepository.save(transacao);
        }
        return null;
    }

    public Transacao depositar(Long contaId, BigDecimal valor) {
        return realizarTransacao(contaId, valor, TipoTransacao.DEPOSITO, NaturezaTransacao.CREDITO, null);
    }

    public Transacao sacar(Long contaId, BigDecimal valor) {
        return realizarTransacao(contaId, valor, TipoTransacao.SAQUE, NaturezaTransacao.DEBITO, null);
    }

    public Transacao transferir(Long contaOrigemId, Long contaDestinoId, BigDecimal valor) {
        Optional<ContaCorrente> contaOrigemOpt = contaCorrenteRepository.findById(contaOrigemId);
        Optional<ContaCorrente> contaDestinoOpt = contaCorrenteRepository.findById(contaDestinoId);

        if (contaOrigemOpt.isPresent() && contaDestinoOpt.isPresent()) {
            ContaCorrente contaOrigem = contaOrigemOpt.get();
            ContaCorrente contaDestino = contaDestinoOpt.get();

            // Criar transação de débito na conta de origem
            Transacao transacaoDebito = realizarTransacao(contaOrigemId, valor, TipoTransacao.TRANSFERENCIA, NaturezaTransacao.DEBITO, contaDestino);

            if (transacaoDebito != null) {
                // Criar transação de crédito na conta de destino
                return realizarTransacao(contaDestinoId, valor, TipoTransacao.TRANSFERENCIA, NaturezaTransacao.CREDITO, contaOrigem);
            }
        }
        return null;
    }
}
