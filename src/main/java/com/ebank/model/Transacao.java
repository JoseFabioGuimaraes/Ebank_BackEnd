package com.ebank.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@NoArgsConstructor
@Data
@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "conta_corrente_id")
    private ContaCorrente contaCorrente;

    @ManyToOne
    @JoinColumn(name = "conta_corrente_destino_id")
    private ContaCorrente contaCorrenteDestino;

    private BigDecimal valor;

    private LocalDateTime dataTransacao;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;

    @Enumerated(EnumType.STRING)
    private NaturezaTransacao natureza;


    public Transacao(ContaCorrente contaCorrente, ContaCorrente contaCorrenteDestino, BigDecimal valor, LocalDateTime dataTransacao, TipoTransacao tipo, NaturezaTransacao natureza) {
        this.contaCorrente = contaCorrente;
        this.contaCorrenteDestino = contaCorrenteDestino;
        this.valor = valor;
        this.dataTransacao = dataTransacao;
        this.tipo = tipo;
        this.natureza = natureza;
    }

}
