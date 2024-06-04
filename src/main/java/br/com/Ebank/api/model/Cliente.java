package br.com.Ebank.api.model;

import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    //@OneToMany(mappedBy = "cliente")
   // private List<ContaCorrente> contasCorrentes;

}
