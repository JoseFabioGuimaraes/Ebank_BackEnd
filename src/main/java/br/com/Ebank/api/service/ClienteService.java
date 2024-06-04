package br.com.Ebank.api.service;

import br.com.Ebank.api.dto.ClienteDTO;
import br.com.Ebank.api.model.Cliente;
import br.com.Ebank.api.model.ContaCorrente;
import br.com.Ebank.api.model.StatusCliente;
import br.com.Ebank.api.model.Transacao;
import br.com.Ebank.api.repository.ClienteRepository;
import br.com.Ebank.api.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaCorrenteService contaCorrenteService;

    @Autowired
    TransacaoRepository transacaoRepository;

    public Cliente create(Cliente cliente) {
        Cliente savedCliente = clienteRepository.save(cliente);
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setCliente(savedCliente);
        contaCorrenteService.create(contaCorrente);

        return savedCliente;
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente update(Long id, ClienteDTO dto) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            cliente.setNome(dto.getNome());
            cliente.setEndereco(dto.getEndereco());
            cliente.setTelefone(dto.getTelefone());
            cliente.setEmail(dto.getEmail());
            cliente.setRendaMensal(dto.getRendaMensal());
            return clienteRepository.save(cliente);
        }
        return null;
    }

    public Cliente delete(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setStatus(StatusCliente.INATIVO);
        return clienteRepository.save(cliente);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public List<Cliente> findByStatus() {
        return clienteRepository.findByStatus(StatusCliente.ATIVO);
    }

    public List<Transacao> findAllTransacoes(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Cliente não encontrado"));
        return transacaoRepository.findByClienteRemetenteOrClienteDestinatario(cliente, cliente);
    }
}