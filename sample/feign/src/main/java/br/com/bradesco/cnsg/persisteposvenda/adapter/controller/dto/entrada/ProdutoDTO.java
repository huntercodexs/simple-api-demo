package br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record ProdutoDTO(

        @NotNull(message = "O código do produto é obrigatório")
        @JsonProperty("codigo_produto")
        String codigoProduto,

        @NotNull(message = "O código da família do produto é obrigatório")
        @JsonProperty("codigo_familia_produto")
        String codigoFamiliaProduto
) {}