package br.com.Ebank.api.repository;

import br.com.Ebank.api.model.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GerenteRepository extends JpaRepository<Gerente, Long> {
}
