package br.com.Ebank.api.dto;

import br.com.Ebank.api.model.StatusCliente;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClienteDTO {
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private BigDecimal rendaMensal;


}
