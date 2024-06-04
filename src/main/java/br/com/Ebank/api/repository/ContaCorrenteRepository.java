package br.com.Ebank.api.repository;

import br.com.Ebank.api.model.ContaCorrente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaCorrenteRepository  extends JpaRepository<ContaCorrente, Long>{
}
