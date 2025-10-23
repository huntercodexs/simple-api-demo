package br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AtualizacoesPosVendaDTO(
        @JsonProperty("situacao_registro")
        String situacaoRegistro
) {
}

