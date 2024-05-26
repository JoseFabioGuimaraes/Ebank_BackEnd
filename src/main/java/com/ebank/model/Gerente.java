package com.ebank.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Gerente extends Pessoa {

    private String matricula;

    public Gerente() {
        this.setTipoPessoa(TipoPessoa.GERENTE);
    }
}
