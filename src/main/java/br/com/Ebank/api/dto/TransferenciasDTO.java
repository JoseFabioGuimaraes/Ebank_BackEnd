package br.com.Ebank.api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferenciasDTO {
    private Long IdDestinatario;
    private BigDecimal valor;
}
