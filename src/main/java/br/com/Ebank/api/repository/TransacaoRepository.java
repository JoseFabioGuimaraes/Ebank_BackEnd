package br.com.Ebank.api.repository;

import br.com.Ebank.api.model.Cliente;
import br.com.Ebank.api.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByClienteRemetenteOrClienteDestinatario(Cliente clienteRemetente, Cliente clienteDestinatario);
}
