package br.com.Ebank.api.service;

import br.com.Ebank.api.dto.ContaCorrenteDTO;
import br.com.Ebank.api.dto.TransferenciasDTO;
import br.com.Ebank.api.model.Cliente;
import br.com.Ebank.api.model.ContaCorrente;
import br.com.Ebank.api.model.Transacao;
import br.com.Ebank.api.repository.ClienteRepository;
import br.com.Ebank.api.repository.ContaCorrenteRepository;
import br.com.Ebank.api.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

@Service
public class ContaCorrenteService {
    @Autowired
    private ContaCorrenteRepository contaCorrenteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    public ContaCorrente create(ContaCorrente contaCorrente){
        contaCorrente.setNumero(generateRandomNumber(4));
        contaCorrente.setNumero(generateRandomNumber(4));
        contaCorrente.setAgencia(generateRandomNumber(2));
        contaCorrente.setSaldo(new BigDecimal(0.00));
        contaCorrente.setDataAbertura(LocalDate.now());
        return contaCorrenteRepository.save(contaCorrente);
    }
    private String generateRandomNumber(int digits) {
        Random random = new Random();
        int limit = (int) Math.pow(10, digits);
        return String.format("%0" + digits + "d", random.nextInt(limit));
    }
    public BigDecimal getsaldo(Long clienteId){
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() ->
                new RuntimeException("Cliente não encontrado"));
        ContaCorrente contaCorrente = cliente.getContaCorrente();
        return contaCorrente.getSaldo();
    }
    public BigDecimal depositar(Long clienteId, ContaCorrenteDTO contaCorrenteDTO){
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() ->
                new RuntimeException("Cliente não encontrado"));
        ContaCorrente contaCorrente = cliente.getContaCorrente();
        BigDecimal novoSaldo = contaCorrente.getSaldo().add(contaCorrenteDTO.getValorDeposito());
        contaCorrente.setSaldo(novoSaldo);
        contaCorrenteRepository.save(contaCorrente);
        return novoSaldo;
    }
    public String gerarToken(){
        int token = new Random().nextInt(90000) + 10000;
        return String.valueOf(token);
    }
    public Transacao transferir(Long idRemetente, TransferenciasDTO transferenciasDTO) {
        // Obter as contas correntes do remetente e do destinatário
        Cliente clienteRemetente = clienteRepository.findById(idRemetente)
                .orElseThrow(() -> new RuntimeException("Cliente remetente não encontrado"));
        ContaCorrente contaCorrenteRemetente = clienteRemetente.getContaCorrente();

        Cliente clienteDestinatario = clienteRepository.findById(transferenciasDTO.getIdDestinatario())
                .orElseThrow(() -> new RuntimeException("Cliente destinatário não encontrado"));
        ContaCorrente contaCorrenteDestinatario = clienteDestinatario.getContaCorrente();

        // Verificar se o remetente tem saldo suficiente para a transferência
        BigDecimal valorTransferencia = transferenciasDTO.getValor();
        if (contaCorrenteRemetente.getSaldo().compareTo(valorTransferencia) < 0) {
            throw new RuntimeException("Saldo insuficiente para a transferência");
        }

        // Subtrair o valor da transferência do saldo do remetente
        contaCorrenteRemetente.setSaldo(contaCorrenteRemetente.getSaldo().subtract(valorTransferencia));

        // Adicionar o valor da transferência ao saldo do destinatário
        contaCorrenteDestinatario.setSaldo(contaCorrenteDestinatario.getSaldo().add(valorTransferencia));

        // Salvar as contas correntes atualizadas no banco de dados
        contaCorrenteRepository.save(contaCorrenteRemetente);
        contaCorrenteRepository.save(contaCorrenteDestinatario);

        // Criar uma nova transação com os detalhes da transferência e salvar no banco de dados
        Transacao transacao = new Transacao();
        transacao.setClienteRemetente(clienteRemetente);
        transacao.setClienteDestinatario(clienteDestinatario);
        transacao.setValor(valorTransferencia);
        transacao.setToken(gerarToken());
        return transacaoRepository.save(transacao);
    }


}
