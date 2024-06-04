package br.com.Ebank.api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContaCorrenteDTO {
    private BigDecimal valorDeposito;
}
