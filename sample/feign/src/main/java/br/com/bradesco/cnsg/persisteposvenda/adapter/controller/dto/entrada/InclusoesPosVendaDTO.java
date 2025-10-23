package br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record InclusoesPosVendaDTO(

        @NotNull(message = "O número do contrato é obrigatório")
        @JsonProperty("numero_contrato")
        String numeroContrato,

        @NotNull(message = "O cliente é obrigatório")
        @JsonProperty("cliente")
        ClienteDTO cliente,

        @NotNull(message = "O produto é obrigatório")
        @JsonProperty("produto")
        ProdutoDTO produto,

        @NotNull(message = "O CNPJ do empregador é obrigatório")
        @JsonProperty("cnpj_empregador")
        String cnpjEmpregador,

        @NotNull(message = "A data de celebração é obrigatória")
        @JsonProperty("data_celebracao")
        @JsonFormat(pattern = "dd.MM.yyyy")
        LocalDate dataCelebracao,

        @NotNull(message = "O código do tipo de encerramento é obrigatório")
        @JsonProperty("codigo_tipo_encerramento")
        String codigoTipoEncerramento,

        @JsonProperty("situacao_registro")
        @NotNull(message = "A situação do registro é obrigatória")
        String situacaoRegistro
) {}