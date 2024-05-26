package com.ebank.service;

import com.ebank.model.Cliente;
import com.ebank.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitanteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente solicitarAberturaDeConta(Cliente cliente) {
        // Aqui você pode adicionar lógica adicional, como enviar uma notificação ao gerente
        return clienteRepository.save(cliente);
    }
}
