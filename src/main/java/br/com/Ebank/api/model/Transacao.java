package br.com.Ebank.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente_remetente")
    private Cliente clienteRemetente;

    @ManyToOne
    @JoinColumn(name = "id_cliente_destinatario")
    private Cliente clienteDestinatario;

    private BigDecimal valor;
    private String token;

}
