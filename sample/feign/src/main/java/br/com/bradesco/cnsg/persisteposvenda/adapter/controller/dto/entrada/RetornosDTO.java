package br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record RetornosDTO(

        @NotNull(message = "O código de retorno do negócio é obrigatório")
        @JsonProperty("codigoRetornoNegocio")
        String codigoRetornoNegocio,

        @JsonProperty("mensagemRetorno")
        String mensagemRetorno
) {
}
