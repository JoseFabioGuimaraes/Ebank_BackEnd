package br.com.Ebank.api.repository;

import br.com.Ebank.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.Ebank.api.model.StatusCliente;


import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByStatus(StatusCliente status);
}
