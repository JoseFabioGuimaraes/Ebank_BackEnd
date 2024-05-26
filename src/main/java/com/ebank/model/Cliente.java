package com.ebank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Pessoa {

    private Double rendaMensal;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<ContaCorrente> contas;

    public Cliente() {
        this.setTipoPessoa(TipoPessoa.CLIENTE);
    }
}
