package com.ebank.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String cpf;

    private LocalDate dataNascimento;

    private String endereco;

    private String telefone;

    private String email;

    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;
}
