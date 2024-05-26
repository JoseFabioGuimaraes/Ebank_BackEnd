package com.ebank.repository;

import com.ebank.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui, se necessário
}
