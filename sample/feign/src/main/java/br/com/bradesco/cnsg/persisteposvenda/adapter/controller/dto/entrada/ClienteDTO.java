package br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record ClienteDTO(

        @NotNull(message = "O CPF do cliente é obrigatório")
        @JsonProperty("cpf_cliente")
        String cpfCliente,

        @NotNull(message = "A matrícula do cliente é obrigatória")
        @JsonProperty("matricula_cliente")
        String matriculaCliente,

        @NotNull(message = "O número do convênio é obrigatório")
        @JsonProperty("numero_convenio")
        String numeroConvenio
) {}
