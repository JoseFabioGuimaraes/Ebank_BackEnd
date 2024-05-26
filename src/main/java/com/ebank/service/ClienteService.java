package com.ebank.service;

import com.ebank.model.Cliente;
import com.ebank.model.ContaCorrente;
import com.ebank.repository.ClienteRepository;
import com.ebank.repository.ContaCorrenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaCorrenteRepository contaCorrenteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> listarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setCpf(clienteAtualizado.getCpf());
            cliente.setDataNascimento(clienteAtualizado.getDataNascimento());
            cliente.setRendaMensal(clienteAtualizado.getRendaMensal());
            cliente.setEndereco(clienteAtualizado.getEndereco());
            cliente.setTelefone(clienteAtualizado.getTelefone());
            cliente.setEmail(clienteAtualizado.getEmail());
            return clienteRepository.save(cliente);
        }
        return null;
    }

    public void encerrarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente aprovarVisitante(Cliente cliente) {
        // Criação do cliente
        Cliente novoCliente = clienteRepository.save(cliente);

        // Criação da conta corrente associada
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setNumeroConta(generateUniqueAccountNumber());
        contaCorrente.setSaldo(BigDecimal.ZERO);
        contaCorrente.setLimiteEspecial(BigDecimal.valueOf(1000));
        contaCorrente.setDataCriacao(LocalDateTime.now());
        contaCorrente.setCliente(novoCliente);

        contaCorrenteRepository.save(contaCorrente);

        return novoCliente;
    }

    private String generateUniqueAccountNumber() {
        // Lógica para gerar um número de conta único
        // Isso deve ser implementado de acordo com suas necessidades
        return String.valueOf(System.currentTimeMillis());
    }
}
