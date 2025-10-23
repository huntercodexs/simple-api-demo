package br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record IncluiRetornoAverbDTO(

        @NotNull(message = "O id da transacao é obrigatório")
        @JsonProperty("idTransacao")
        String idTransacao,

        @NotNull(message = "O nome da averbadora é obrigatório")
        @JsonProperty("nomeAverbadora")
        String nomeAverbadora,

        @NotNull(message = "O nome do serviço é obrigatório")
        @JsonProperty("nomeServico")
        String nomeServico,

        @NotNull(message = "O código do retorno http é obrigatório")
        @JsonProperty("codigoRetornoHttp")
        String codigoRetornoHttp,

        @JsonProperty("hashOperacao")
        Long hashOperacao,

        @JsonProperty("retornos")
        List<RetornosDTO> retornos
) {
}
