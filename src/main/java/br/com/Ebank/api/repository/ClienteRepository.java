package br.com.Ebank.api.repository;

import br.com.Ebank.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Cliente, Long> {
}