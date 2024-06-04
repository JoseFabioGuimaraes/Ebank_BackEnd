package br.com.Ebank.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String cpf;

    private String endereco;

    private String telefone;

    private LocalDate dataNascimento;

    private String email;

    private BigDecimal rendaMensal;

    @Enumerated(EnumType.STRING)
    private StatusCliente status = StatusCliente.ATIVO;

    @OneToOne(mappedBy = "cliente",cascade = CascadeType.ALL)
    private ContaCorrente contaCorrente;

    @OneToMany(mappedBy = "clienteRemetente")
    @JsonIgnore
    private List<Transacao> transacoesRemetente;

    @OneToMany(mappedBy = "clienteDestinatario")
    @JsonIgnore
    private List<Transacao> transacoesDestinatario;

}
